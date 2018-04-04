package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by mgard on 3/10/2018.
 */

public interface IServerCommunicator {
    void getUsers();

    void loadUser(String name);

    void getFriends(String id);

    void getUsersFriendsReadingList();

    void updateUser(String username);

    void addRecommendation(Book book);

    void addToReadingList(Book book);

    void followUser(String otherUsername);

    void searchForBook(String searchString);

    void getBookById(String id);

    void searchBookByTitle(String title);

    void rateBook(String userId, String bookId, int rating);

    void setUserToken(String token);

    String getUserToken();
}
