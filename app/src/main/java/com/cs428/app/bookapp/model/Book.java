package com.cs428.app.bookapp.model;

import android.graphics.Bitmap;

/**
 * Created by chees on 3/8/2018.
 */

public class Book {
    private String name;
    private String author;
    private String isbn;
    private Bitmap cover;

    public Book(String name, String author, String isbn, Bitmap cover) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }
}
