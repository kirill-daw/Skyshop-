package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketItems;
    private final int total;

    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
        this.total = calculateTotalCost();
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int calculateTotalCost() {
        return basketItems.stream()
                .mapToInt(BasketItem::getTotalPrice)
                .sum();
    }

    public int getTotal() {
        return total;
    }
}