package com.cs428.app.bookapp.model;

/**
 * Created by chees on 3/8/2018.
 */

public class Model {
    private static final Model SINGLETON = new Model();
    protected User currentUser = null;

    private Model() {}

    public static Model getSINGLETON() {return SINGLETON;}

    public void setCurrentUser(User user){currentUser = user;}
}
