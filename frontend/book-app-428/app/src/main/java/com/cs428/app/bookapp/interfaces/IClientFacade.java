package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;

import java.util.List;
import java.util.Observer;

/**
 * Created by rredd on 3/15/2018.
 */

public interface IClientFacade {
    /*homepage view*/
    User getCurrentUser();
    void getHomePageBooks(Person person, OnHomeBooksTaskComplete listener);

    /*book page*/
    boolean rateBook(String book_id, int rating);
    boolean recommendBook(String book_id);

    /*profile page*/
    void getPersonsReadingList(Person person, OnReadingBooksTaskComplete listener);
    void getPersonsReviewedList(Person person, OnReviewedBooksTaskComplete listener);

    /* search page */
    void doSearch(String searchString, OnSearchTaskComplete listener);

    void ObserveModel (Observer observer);
}
