package com.cs428.app.bookapp.model;

import com.cs428.app.bookapp.interfaces.IClientFacade;
import com.cs428.app.bookapp.interfaces.IServerCommunicator;
import com.cs428.app.bookapp.interfaces.IServerProxy;
import com.cs428.app.bookapp.networking.Serializer;
import com.cs428.app.bookapp.networking.ServerCommunicator;
import com.cs428.app.bookapp.networking.ServerProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rredd on 3/15/2018.
 */

public class ClientFacade implements IClientFacade {

    private Model model = Model.getSINGLETON();
    IServerCommunicator serverCom = new ServerCommunicator(new Serializer());
    private ServerProxy serverProxy = new ServerProxy(serverCom);


    //register will automatically login the user
    public boolean register(User user, String password){
        boolean successful = serverProxy.registerUser(user);
        if(successful)
        {
            login(user.getName(), password);
        }
        return successful;
    }

    public void login(String username, String password) {
        User currentUser = serverProxy.login(username, password);
        model.setCurrentUser(currentUser);
        //should we call get homepageBooks here?
    }

    public User getCurrentUser(){ return model.currentUser;}

    public List<Book> getHomePageBooks(){
        if(model.currentUser != null) {
            return serverProxy.getRecommendationFor(model.currentUser);
        }
        else
        {return null;}
    }

    public List<Book> searchBooks(String searchString){
        return serverProxy.searchBook(searchString);
    }

    public Book get_book(String bookId){return serverProxy.getBookById(bookId);}

    public boolean rateBook(String book_id, int rating){return serverProxy.rateBook(model.currentUser, book_id, rating);}

    public boolean reccomendBook(String book_id){
        model.currentUser.addToReviewedBooks(book_id);
        return serverProxy.recommendBook(model.currentUser, book_id);
    }

    public List<Book>getPersonsReadingList(Person person){
        List<String> bookIds = person.getReadingList();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < bookIds.size(); i++)
        {
           Book book = serverProxy.getBookById(bookIds.get(i));
           books.add(book);
        }
        return books;
    }

    public List<Book> getPersonsReviewedList(Person person){
        List<String> bookIds = person.getReviewedBooks();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < bookIds.size(); i++)
        {
            Book book = serverProxy.getBookById(bookIds.get(i));
            books.add(book);
        }
        return books;
    }

}
