package com.cs428.app.bookapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chees on 3/8/2018.
 */

public class User extends Person {
    private List<String> following;

    public User(String name, String id) {
        super(name, id);
        following = new ArrayList<>();
        isUser = true;
    }

    public void addToReadingList(Book book) {
        if (readingList != null) readingList.add(book);
    }

    public void addToReviewedBooks(Book book) {
        if (reviewedBooks != null) reviewedBooks.add(book);
    }

    public void removeFromReadingList(Book book) {
        if (readingList != null) readingList.remove(book);
    }

    public void removeFromReviewedBooks(Book book) {
        if (reviewedBooks != null) reviewedBooks.remove(book);
    }

    public void followUser(String id) {
        following.add(id);
    }

    public void unfollowUser(String id) {
        following.remove(id);
    }

    public List<String> getFollowing() { return following; }

    public boolean isFollowing(String id) {
        return following.contains(id);
    }
}
