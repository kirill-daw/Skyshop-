package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.searchresult.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService = new StorageService();
    private final SearchService searchService = new SearchService(storageService);

    @GetMapping("/products")
    public Map<UUID, Product> getAllProducts() {
        return storageService.getProducts();
    }

    @GetMapping("/articles")
    public  Map<UUID, Article> getAllArticles() {
        return storageService.getArticles();
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String pattern) {
        System.out.println("Received pattern: " + pattern);
        return searchService.search(pattern);
    }
}
