package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("The price of product is null or a negative number: " + this.getName() + " price = " + price);
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "<" + this.getName() + ">: " + "<" + this.price + ">";
    }
}
