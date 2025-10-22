package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
public class ProductBasket {
    private final Map<UUID, Integer> totalProducts;

    public ProductBasket() {
        this.totalProducts = new HashMap<>();
    }

    public void addProduct(UUID id) {
        int productCount = 1;

        if (totalProducts.containsKey(id)) {
            productCount = productCount + totalProducts.get(id);
        }
        totalProducts.put(id, productCount);
    }

    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(totalProducts);
    }
}