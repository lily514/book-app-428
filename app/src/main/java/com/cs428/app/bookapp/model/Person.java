package com.cs428.app.bookapp.model;

import java.util.List;

/**
 * Created by chees on 3/8/2018.
 */

public class Person {
    protected String name;
    protected String id;
    protected List<Book> readingList = null;
    protected List<Book> reviewedBooks = null;
    protected boolean isUser = false;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public boolean isUser() { return isUser; }

    public String getName() { return name; }

    public String getId() { return id; }

    public List<Book> getReadingList() {
        return readingList;
    }

    public List<Book> getReviewedBooks() {
        return reviewedBooks;
    }

    public void setReadingList(List<Book> readingList) {
        this.readingList = readingList;
    }

    public void setReviewedBooks(List<Book> reviewedBooks) {
        this.reviewedBooks = reviewedBooks;
    }
}
