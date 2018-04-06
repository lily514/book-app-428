package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by Lily on 3/21/18.
 * Serializable gives the HomeFragment access to methods on the ClientFacade
 */

public interface Serializable extends java.io.Serializable {
    List<Book> getHomePageBooks();
    User getCurrentUser();
    List<Book> searchBooks(String searchString);
}
