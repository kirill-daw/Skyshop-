package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        fillStorage();
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    private void fillStorage() {
        Product apple = new SimpleProduct("Apple",
                50,
                UUID.randomUUID());
        Article articleApple = new Article(apple.getName() + " article",
                "delicious apple",
                UUID.randomUUID());
        products.put(apple.getId(), apple);
        articles.put(articleApple.getId(), articleApple);
        Product pen = new FixPriceProduct("Pen", UUID.randomUUID());
        Article articlePen = new Article(pen.getName() + " article",
                "beautiful pen",
                UUID.randomUUID());
        products.put(pen.getId(), pen);
        articles.put(articlePen.getId(), articlePen);
        Product orange = new SimpleProduct("Orange",
                70,
                UUID.randomUUID());
        Article articleOrange = new Article(orange.getName() + " article",
                "delicious orange",
                UUID.randomUUID());
        products.put(orange.getId(), orange);
        articles.put(articleOrange.getId(), articleOrange);
        Product lamp = new DiscountedProduct("Lamp",
                520,
                25,
                UUID.randomUUID());
        Article articleLamp = new Article(lamp.getName() + " article",
                "light lamp",
                UUID.randomUUID());
        products.put(lamp.getId(), lamp);
        articles.put(articleLamp.getId(), articleLamp);
        Product fish = new DiscountedProduct("Fish",
                120,
                20,
                UUID.randomUUID());
        Article articleFish = new Article(fish.getName() + " article",
                "delicious fish",
                UUID.randomUUID());
        products.put(fish.getId(), fish);
        articles.put(articleFish.getId(), articleFish);
        Product meat = new SimpleProduct("Meat",
                220,
                UUID.randomUUID());
        Article articleMeat = new Article(meat.getName() + " article",
                "delicious meat",
                UUID.randomUUID());
        products.put(meat.getId(), meat);
        articles.put(articleMeat.getId(), articleMeat);
    }

    public ArrayList<Searchable> searchables() {
        return Stream.concat(products.values().stream(),
                articles.values().stream()).collect(Collectors.toCollection(ArrayList<Searchable>::new));
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}