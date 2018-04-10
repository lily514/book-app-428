package com.cs428.app.bookapp.model;

import android.util.Log;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.cs428.app.bookapp.interfaces.IServerCommunicator;
import com.cs428.app.bookapp.networking.Serializer;
import com.cs428.app.bookapp.networking.ServerCommunicator;
import com.cs428.app.bookapp.networking.ServerProxy;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by chees on 3/8/2018.
 */

public class Model extends Observable {
    private static final Model SINGLETON = new Model();
    private IServerCommunicator serverCom;
    private ServerProxy serverProxy;

    protected User currentUser = null;
    protected CognitoUserPool userPool;

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

    public void initializeServer() {
        serverCom = new ServerCommunicator(new Serializer());
        serverProxy = new ServerProxy(serverCom);
        serverProxy.initialize();
        Log.d("DEBUG", "initializeServer: created ServerProxy");
    }

    public ServerProxy getServerProxy(){
        return serverProxy;
    }
}
