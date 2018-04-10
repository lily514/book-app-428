package com.cs428.app.bookapp.model;

/**
 * Created by chees on 3/31/2018.
 */

public class BookReview {
    private String reviewer;
    private String review;
    private String rating;

    public BookReview(String review){
        this.review = review;
    }

    public BookReview(String reviewer, String review, String rating){
        this.reviewer = reviewer;
        this.review = review;
        this.rating = rating;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getReview() {
        return review;
    }

    public String getRating() {
        return rating;
    }
}
