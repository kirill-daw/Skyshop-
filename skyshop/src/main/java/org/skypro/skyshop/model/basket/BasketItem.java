package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public final class BasketItem {
    private final Product product;
    private final int value;


    public BasketItem(Product product, int value) {
        this.product = product;
        this.value = value;
    }

    public int getTotalPrice() {
        return product.getPrice() * value;
    }
}
