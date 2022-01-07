package com.example.Task1.models;

public class Author {
    public Long Id;
    public String authorName;
    public String path;

    public Author() {
    }

    public Author(Long id, String authorName, String path) {
        Id = id;
        this.authorName = authorName;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
