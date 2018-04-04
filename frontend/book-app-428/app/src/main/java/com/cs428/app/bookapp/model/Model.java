package com.cs428.app.bookapp.model;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by chees on 3/8/2018.
 */

public class Model extends Observable {
    private static final Model SINGLETON = new Model();
    protected User currentUser = null;
    protected CognitoUserPool userPool;

    private List<Book> bookSearchResults;
    private List<User> userSearchResults;

    private Model() {}

    public static Model getSINGLETON() {return SINGLETON;}

    public void setCurrentUser(User user){
        currentUser = user;
        setChanged();
        notifyObservers(currentUser);
        clearChanged();
    }

    public User getCurrentUser() {return currentUser;}

    public void setUserPool(CognitoUserPool userPool) {
        this.userPool = userPool;
    }

    public CognitoUserPool getUserPool() {
        return this.userPool;
    }

    public List<User> getUserSearchResults() {
        return userSearchResults;
    }

    public void setUserSearchResults(List<User> userSearchResults) {
        this.userSearchResults = userSearchResults;
    }

    public List<Book> getBookSearchResults() {
        return bookSearchResults;
    }

    public void setBookSearchResults(List<Book> bookSearchResults) {
        this.bookSearchResults = bookSearchResults;
    }
}
