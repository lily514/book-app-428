package com.cs428.app.bookapp.model;

/**
 * Created by chees on 3/8/2018.
 */

public class Model {
    public static final Model SINGLETON = new Model();
    public User currentUser = null;

    private Model() {}
}
