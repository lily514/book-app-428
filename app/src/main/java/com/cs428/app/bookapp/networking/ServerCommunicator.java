package com.cs428.app.bookapp.networking;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerCommunicator {

    private Serializer serializer;

    public ServerCommunicator(Serializer serializer) {
        this.serializer = serializer;
    }
}
