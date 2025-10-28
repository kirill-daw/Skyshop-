package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {
    private BasketService basketService;
    private ProductBasket mockProductBasket;
    private StorageService mockStorageService;

    @BeforeEach
    void setUp() {
        mockProductBasket = mock(ProductBasket.class);
        mockStorageService = mock(StorageService.class);
        basketService = new BasketService(mockProductBasket, mockStorageService);
    }

    @Test
    void testAddNonExistingProductThrowsException() {
        UUID nonExistentId = UUID.randomUUID();
        when(mockStorageService.getProductById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(nonExistentId),
                "Adding a non-existing product should throw an exception.");
    }

    @Test
    void testAddExistingProductCallsMethodOnProductBasket() {
        UUID existingId = UUID.randomUUID();
        Product validProduct = new Product("Valid Product", existingId) {
            @Override
            public int getPrice() {
                return 0;
            }
        };
        when(mockStorageService.getProductById(existingId)).thenReturn(Optional.of(validProduct));

        basketService.addProduct(existingId);

        verify(mockProductBasket).addProduct(existingId);
    }

    @Test
    void testGetUserBasketReturnsEmptyIfProductBasketIsEmpty() {
        when(mockProductBasket.getProductBasket()).thenReturn(Map.of());

        UserBasket userBasket = basketService.getUserBasket();

        assertNotNull(userBasket);
        assertEquals(List.of(), userBasket.getBasketItems(), "Expected empty basket.");
    }

    @Test
    void testGetUserBasketReturnsCorrectBasketForExistingProducts() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        Product product1 = new SimpleProduct("Product One", 150, productId1);
        Product product2 = new FixPriceProduct("Product Two", productId2);

        Map<UUID, Integer> productQuantities = new HashMap<>();
        productQuantities.put(productId1, 2);
        productQuantities.put(productId2, 5);

        when(mockProductBasket.getProductBasket()).thenReturn(productQuantities);
        when(mockStorageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(mockStorageService.getProductById(productId2)).thenReturn(Optional.of(product2));

        UserBasket userBasket = basketService.getUserBasket();

        assertNotNull(userBasket);
        assertEquals(2, userBasket.getBasketItems().size(), "There should be two items in the basket.");

        BasketItem actualItem1 = userBasket.getBasketItems().get(0);
        BasketItem actualItem2 = userBasket.getBasketItems().get(1);

        assertEquals(product1, actualItem1.getProduct(), "Mismatch in product for first item.");
        assertEquals(2, actualItem1.getValue(), "Incorrect quantity for first item.");

        assertEquals(product2, actualItem2.getProduct(), "Mismatch in product for second item.");
        assertEquals(5, actualItem2.getValue(), "Incorrect quantity for second item.");
    }
}