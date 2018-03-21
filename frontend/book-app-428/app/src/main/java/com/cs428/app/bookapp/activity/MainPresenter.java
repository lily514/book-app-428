package com.cs428.app.bookapp.activity;

import com.cs428.app.bookapp.interfaces.IClientFacade;
import com.cs428.app.bookapp.interfaces.IHomePresenter;
import com.cs428.app.bookapp.interfaces.IProfilePresenter;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.ClientFacade;
import com.cs428.app.bookapp.model.Person;

import java.util.List;

/**
 * Created by Lily on 3/21/18.
 */

public class MainPresenter implements IHomePresenter, IProfilePresenter {

    private IClientFacade modelFacade;
    private Person person;

    public MainPresenter() {
        this.modelFacade = new ClientFacade();
    }

    @Override
    public List<Book> getHomePageBooks() {
        return modelFacade.getHomePageBooks();
    }

    @Override
    public List<Book> getPersonsReadingList() {
        return modelFacade.getPersonsReadingList(person);
    }

    @Override
    public List<Book> getPersonsReviewedList() {
        return modelFacade.getPersonsReadingList(person);
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }
}
