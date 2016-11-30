package io.unicorn.domain;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class FileMetadata {

    private String author;
    private String theme;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
