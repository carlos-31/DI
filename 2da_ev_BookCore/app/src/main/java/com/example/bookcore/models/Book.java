package com.example.bookcore.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String cover_url;
    private String synopsis;
    private int page_count;
    private int publication;

    public Book() {}


    public Book(String id, int publication, int page_count, String synopsis, String cover_url, String genre, String author, String title) {
        this.id = id;
        this.publication = publication;
        this.page_count = page_count;
        this.synopsis = synopsis;
        this.cover_url = cover_url;
        this.genre = genre;
        this.author = author;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public int getPublication() {
        return publication;
    }

    public int getPage_count() {
        return page_count;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }
}
