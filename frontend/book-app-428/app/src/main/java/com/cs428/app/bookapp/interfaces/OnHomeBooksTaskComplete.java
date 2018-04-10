package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;

import java.util.List;

/**
 * Created by Lily on 4/9/18.
 */

public interface OnHomeBooksTaskComplete {

    void onHomeBooksTaskComplete(List<Book> books);
    void addHomeBook(Book book);
}
