package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by rredd on 3/15/2018.
 */

public interface IClientFacade {

    /*login view*/
    boolean register(User user, String password);
    void login(String username, String password);

    /*homepage view*/
    User getCurrentUser();
    List<Book> getHomePageBooks();
    List<Book> searchBooks(String searchString);

    /*book page*/
    Book get_book(String bookId);
    boolean rateBook(String book_id, int rating);
    boolean recomendBook(String book_id);

    /*profile page*/
    List<Book> getPersonsReadingList(Person person);
    List<Book> getPersonsReviewedList(Person person);
}