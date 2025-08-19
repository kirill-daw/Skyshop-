package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String name;
    private final UUID id;

    protected Product(String name, UUID id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name of product is empty string.");
        }
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String searchTerm() {
        return name;
    }

    @Override
    public UUID getId() {
        return id;
    }
}