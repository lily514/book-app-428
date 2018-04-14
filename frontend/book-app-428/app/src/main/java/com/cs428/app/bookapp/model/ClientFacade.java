package com.cs428.app.bookapp.model;

import android.util.Log;

import com.cs428.app.bookapp.interfaces.IClientFacade;
import com.cs428.app.bookapp.interfaces.IServerCommunicator;
import com.cs428.app.bookapp.interfaces.OnHomeBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.networking.Serializer;
import com.cs428.app.bookapp.networking.ServerCommunicator;
import com.cs428.app.bookapp.networking.ServerProxy;

import java.util.List;
import java.util.Observer;

/**
 * Created by rredd on 3/15/2018.
 */

public class ClientFacade implements IClientFacade{

    private Model model;
    private ServerProxy serverProxy = null;

    public ClientFacade(){
        model = Model.getSINGLETON();
    }

    public void setServerProxy(){
        this.serverProxy = model.getServerProxy();
    }

    public User getCurrentUser(){ return model.currentUser;}

    /** PATTERN : every get request should ask the serverProxy for the latest data,
     * but should return whatever is currently in the model.
     * The observer pattern will return the latest data after the async tasks complete
     * @param user
     * @param listener*/


    @Override
    public void getHomePageBooks(User user, OnHomeBooksTaskComplete listener){
        if(model.currentUser != null) {
            Log.d("DEBUG", "getHomePageBooks: ");
            for (String userID: user.getFollowing()){
                Log.d("Following", "getHomePageBooks: "+ userID);
                serverProxy.getRecommendationFor(userID, listener);
            }

        }
    }

    //TODO: how does this work
    public boolean rateBook(String book_id, int rating) {
        //return serverProxy.rateBook(model.currentUser, book_id, rating);}
        return false;
    }

    public boolean recommendBook(String book_id){
        model.currentUser.addToReviewedBooks(book_id);
        return serverProxy.recommendBook(model.currentUser, book_id);
    }


    public void getPersonsReadingList(Person person, OnReadingBooksTaskComplete listener){

        List<String> bookIds = person.getReadingList();
        for (int i = 0; i < bookIds.size(); i++)
        {
           serverProxy.getReadingBookById(bookIds.get(i), listener);
        }
    }

    public void getPersonsReviewedList(Person person, OnReviewedBooksTaskComplete listener){

        List<String> bookIds = person.getReviewedBooks();
        for (int i = 0; i < bookIds.size(); i++)
        {
            serverProxy.getReviewedBookById(bookIds.get(i), listener);
        }

        //return null;
    }

    @Override
    public void doSearch(String searchString, OnSearchTaskComplete listener) {
        serverProxy.searchBook(searchString, listener);
        serverProxy.searchPerson(searchString, listener);
    }


    public void ObserveModel(Observer o) {
        model.addObserver(o);
    }


}
