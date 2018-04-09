package com.cs428.app.bookapp.activity;

import android.util.Log;

import com.cs428.app.bookapp.interfaces.IClientFacade;
import com.cs428.app.bookapp.interfaces.IHomePresenter;
import com.cs428.app.bookapp.interfaces.IProfilePresenter;
import com.cs428.app.bookapp.interfaces.ISearchPresenter;
import com.cs428.app.bookapp.interfaces.OnHomeBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.ClientFacade;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Lily on 3/21/18.
 */



public class MainPresenter implements IProfilePresenter, ISearchPresenter, IHomePresenter, Observer {

    private IClientFacade modelFacade;
    private Person person = null;
    private User currentUser = null;
    private MainActivity mainActivity;


    public MainPresenter(MainActivity mainActivity) {

        this.modelFacade = new ClientFacade();
        this.modelFacade.ObserveModel(this);
        this.mainActivity = mainActivity;
    }

    @Override
    public User getCurrentUser() {
        currentUser = modelFacade.getCurrentUser();
        setPerson(currentUser);
        return modelFacade.getCurrentUser();
    }


    /*profile page*/

    @Override
    public void getPersonsReadingList(OnReadingBooksTaskComplete listener) {
        modelFacade.getPersonsReadingList(person, listener);
    }

    @Override
    public void getPersonsReviewedList(OnReviewedBooksTaskComplete listener) {
        modelFacade.getPersonsReviewedList(person, listener);
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof User)
        {
            currentUser  = (User)arg;
            setPerson(currentUser);
        }
    }

    @Override
    public void getPersonsRecommendedList(OnHomeBooksTaskComplete listener) {
        modelFacade.getHomePageBooks(person, listener);
    }

    /** search page **/
    @Override
    public boolean doSearch(String searchString, OnSearchTaskComplete listener) {
        modelFacade.doSearch(searchString, listener);
        return false;
    }
}
