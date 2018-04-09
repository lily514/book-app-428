package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;

import java.util.List;

/**
 * Created by Lily on 4/9/18.
 */

public interface OnReviewedBooksTaskComplete {

    void onReviewedBooksTaskComplete(List<Book> books);
    void addReviewedBook(Book book);
}
