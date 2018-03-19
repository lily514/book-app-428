package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by mgard on 3/10/2018.
 */

public interface IServerCommunicator {
    List<User> getUsers();

    User getUser(String id);

    List<User> getFriends(String id);

    List<Book> getUsersFriendsReadingList(String id);

    User login(String username, String password);

    boolean registerUser(User user);

    boolean addRecommendation(String id, Book book);

    boolean addToReadingList(String id, Book book);

    boolean followUser(String myId, String otherId);

    List<Book> searchForBook(String searchString);

    Book getBookById(String id);

    boolean rateBook(String userId, String bookId, int rating);
}