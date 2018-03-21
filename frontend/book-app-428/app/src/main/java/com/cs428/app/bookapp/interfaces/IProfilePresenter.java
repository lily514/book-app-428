package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lily on 3/21/18.
 */

public interface IProfilePresenter extends Serializable {

    /*profile page*/
    List<Book> getPersonsReadingList();
    List<Book> getPersonsReviewedList();
    Person getPerson();
    void setPerson(Person person);
}
