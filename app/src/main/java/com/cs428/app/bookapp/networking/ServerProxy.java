package com.cs428.app.bookapp.networking;

import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerProxy {

    private ServerCommunicator serverCommunicator;

    public ServerProxy(ServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

    public boolean login(String username, String password){
        return false;
    }

    public boolean followFriend(String followUsername){
        return false;
    }

    public String searchBook(String searchTerm){
        return "book";
    }

    public boolean reccommendBook(String book){
        return false;
    }

    public boolean rateBook(String book, int rating){
        return false;
    }

    public List<String> getRecommendation(String user){
        return null;
    }

    //TODO: Finish stubbing these out based on API
}
