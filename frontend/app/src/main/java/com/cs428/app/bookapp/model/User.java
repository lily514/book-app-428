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

    public void addToReadingList(String bookID) {
        if (readingList != null) readingList.add(bookID);
    }

    public void addToReviewedBooks(String bookID) {
        if (reviewedBooks != null) reviewedBooks.add(bookID);
    }

    public void removeFromReadingList(String bookID) {
        if (readingList != null) readingList.remove(bookID);
    }

    public void removeFromReviewedBooks(String bookID) {
        if (reviewedBooks != null) reviewedBooks.remove(bookID);
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
