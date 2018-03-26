package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by emilyprigmore on 3/25/18.
 */

public interface ISearchPresenter extends Serializable {
    List<Book> searchBooks(String searchString);
}
