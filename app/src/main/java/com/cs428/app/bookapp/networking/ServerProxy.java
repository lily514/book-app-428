package com.cs428.app.bookapp.networking;

import com.cs428.app.bookapp.interfaces.IServerProxy;

import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerProxy implements IServerProxy{

    private ServerCommunicator serverCommunicator;

    public ServerProxy(ServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

    @Override
    public boolean login(String username, String password){
        return false;
    }

    @Override
    public boolean followFriend(String followUsername){
        return false;
    }

    @Override
    public String searchBook(String searchTerm){
        return "book";
    }

    @Override
    public boolean reccommendBook(String book){
        return false;
    }

    @Override
    public boolean rateBook(String book, int rating){
        return false;
    }

    @Override
    public List<String> getRecommendation(String user){
        return null;
    }

    //TODO: Finish stubbing these out based on API
}
