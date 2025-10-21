package org.skypro.skyshop.service;

import org.skypro.skyshop.model.searchresult.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String pattern) {
        return storageService.searchables().stream().
                filter(searchable ->
                        searchable
                                .searchTerm()
                                .toLowerCase()
                                .contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors
                        .toCollection(ArrayList::new));
    }
}