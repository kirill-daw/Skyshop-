package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.searchresult.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SearchServiceTest {
    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    void testSearchWhenStorageEmpty() {
        when(storageService.searchables()).thenReturn(new ArrayList<>());

        List<SearchResult> results = searchService.search("pen");

        assertTrue(results.isEmpty(), "The results should be empty if there are no objects.");
        verify(storageService).searchables();
    }

    @Test
    void testSearchWhenNoMatches() {
        Searchable item1 = mock(Searchable.class);
        Searchable item2 = mock(Searchable.class);

        when(item1.searchTerm()).thenReturn("Pen");
        when(item2.searchTerm()).thenReturn("Lamp");

        List<Searchable> data = new ArrayList<>(List.of(item1, item2));

        StorageService storageService = mock(StorageService.class);
        when(storageService.searchables()).thenReturn((ArrayList<Searchable>) data);

        SearchService searchService = new SearchService(storageService);

        List<SearchResult> results = searchService.search("apple");

        assertTrue(results.isEmpty(), "The results should be empty if there are no matches.");
    }

    @Test
    void testSearchWhenOneMatch() {
        Searchable item1 = mock(Searchable.class);
        Searchable item2 = mock(Searchable.class);

        when(item1.searchTerm()).thenReturn("Pen");
        when(item2.searchTerm()).thenReturn("Apple");
        when(item1.getName()).thenReturn("Pen");
        when(item2.getName()).thenReturn("Apple");
        when(item1.getId()).thenReturn(UUID.randomUUID());
        when(item2.getId()).thenReturn(UUID.randomUUID());

        List<Searchable> data = new ArrayList<>(List.of(item1, item2));

        StorageService storageService = mock(StorageService.class);
        when(storageService.searchables()).thenReturn((ArrayList<Searchable>) data);

        SearchService searchService = new SearchService(storageService);

        List<SearchResult> results = searchService.search("apple");

        assertEquals(1, results.size(), "One object must be found.");

        assertEquals(
                "Apple",
                results.get(0).getName(), "First result's name should match the searched term."
        );
    }
}
