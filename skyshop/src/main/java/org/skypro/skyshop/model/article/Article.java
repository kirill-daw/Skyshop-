package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleTitle;
    private final String articleText;
    private final UUID id;

    public Article(String articleTitle, String articleText, UUID id) {
        if (articleTitle != null && articleText != null && !articleTitle.isBlank() && !articleText.isBlank()) {
            this.articleTitle = articleTitle;
            this.articleText = articleText;
            this.id = id;
        } else {
            throw new IllegalArgumentException("The article is incomplete. ");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle) && Objects.equals(articleText, article.articleText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleTitle, articleText);
    }

    @Override
    public String toString() {
        return articleTitle + "\n" + articleText;
    }

    @Override
    public String searchTerm() {
        return toString();
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return articleTitle;
    }

    @Override
    public UUID getId() {
        return id;
    }
}