package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by mgard on 3/10/2018.
 */

public interface IServerCommunicator {
    List<User> getUsers();

    void loadUser(String name);

    List<User> getFriends(String id);

    void updateUser(String username);

    void addRecommendation(Book book);

    void addToReadingList(Book book);

    void followUser(String otherUsername);

    List<Book> getUsersFriendsReadingList(String id);

    List<Book> searchForBook(String searchString);

    Book getBookById(String id);

    Book searchBookByTitle(String title);

    boolean rateBook(String userId, String bookId, int rating);

    void setUserToken(String token);

    String getUserToken();
}
