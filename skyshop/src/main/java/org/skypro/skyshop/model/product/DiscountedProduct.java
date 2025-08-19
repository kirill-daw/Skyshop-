package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basicPrice;
    private final int discountPercent;

    public DiscountedProduct(String name, int price, int discountPercent, UUID id) {
        super(name, id);
        if (price <= 0) {
            throw new IllegalArgumentException("The price of product is null or a negative number: " + this.getName() + " = " + price);
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("The percent of discount is wrong (right numbers 0-100): " + this.getName() + " (" + discountPercent + ")");
        }
        this.basicPrice = price;
        this.discountPercent = discountPercent;
    }

    @Override
    public int getPrice() {
        return (basicPrice - (int) (basicPrice * (discountPercent / 100f)));
    }

    @Override
    public String toString() {
        return "<" + this.getName() + ">: " + "<" + this.basicPrice + " - " + (int) (basicPrice * (discountPercent / 100f)) + "> " + "(<" + this.discountPercent + ">%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
