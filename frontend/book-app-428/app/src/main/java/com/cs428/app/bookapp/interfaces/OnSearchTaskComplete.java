package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;

import java.util.List;

/**
 * Created by Lily on 4/9/18.
 * Methods for the async tasks to update the model
 */

public interface OnSearchTaskComplete {

    void onSearchTaskComplete(List<Book> book_results, List<Person> person_results);
    void addBook(Book book);
    void addPerson(Person person);

    void addBooks(List<Book> books);
}
