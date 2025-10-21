package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storage;

    @Autowired
    public BasketService(ProductBasket basket, StorageService storage) {
        this.basket = basket;
        this.storage = storage;
    }

    public void addProduct(UUID id) {
        Optional<Product> product = storage.getProductById(id);
        if (!product.isPresent()) {
            throw new IllegalArgumentException("This product is not found: " + id);
        } else {
            basket.addProduct(id);
        }
    }

    public UserBasket getUserBasket() {
        return
                new UserBasket(basket.getProductBasket()
                        .entrySet()
                        .stream()
                        .map(entry -> {
                            Product product = storage.getProductById(entry.getKey()).orElseThrow(() ->
                                    new IllegalArgumentException("This product is not found: " + entry.getKey()));
                            return new BasketItem(
                                    product,
                                    entry.getValue());
                        })
                        .toList());
    }
}
