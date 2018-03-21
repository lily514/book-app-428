package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lily on 3/21/18.
 */

public interface IHomePresenter extends Serializable {
    List<Book> getHomePageBooks();
}
