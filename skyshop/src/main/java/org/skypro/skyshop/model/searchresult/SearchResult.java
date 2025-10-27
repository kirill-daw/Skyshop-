package org.skypro.skyshop.model.searchresult;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.skypro.skyshop.model.search.Searchable;

public class SearchResult {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String contentType;
    @JsonProperty
    private final String id;

    public SearchResult(String name,String contentType,String id) {
        this.name = name;
        this.contentType = contentType;
        this.id = id;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getName(),
                searchable.getContentType(),
                searchable.getId().toString());
    }

    public String getName() {
        return name;
    }
}