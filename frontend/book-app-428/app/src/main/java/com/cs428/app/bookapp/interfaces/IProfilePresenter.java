package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;

import java.util.List;

/**
 * Created by Lily on 3/21/18.
 * IProfilePresenter gives the ProfileFragment access to methods on the ClientFacade
 */

public interface IProfilePresenter extends Serializable {

    /*profile page*/
    List<Book> getPersonsReadingList();
    List<Book> getPersonsReviewedList();
    Person getPerson();
    void setPerson(Person person);

}
