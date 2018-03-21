package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lily on 3/21/18.
 * IHomePresenter gives the HomeFragment access to methods on the ClientFacade
 */

public interface IHomePresenter extends Serializable {
    List<Book> getHomePageBooks();
    User getCurrentUser();
    List<Book> searchBooks(String searchString);
}
