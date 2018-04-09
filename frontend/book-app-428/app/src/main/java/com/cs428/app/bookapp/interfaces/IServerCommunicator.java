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

    List<Book> getUsersFriendsReadingList(String id);

    void updateUser(String username);

    boolean addRecommendation(String id, Book book);

    boolean addToReadingList(String id, Book book);

    boolean followUser(String myId, String otherId);

    boolean rateBook(String userId, String bookId, int rating);

    void setUserToken(String token);

    String getUserToken();

    void searchBookByAuthor(String searchTerm, OnSearchTaskComplete listener);

    void searchBookByTitle(String searchTerm, OnSearchTaskComplete listener);

    void searchUserByName(String searchTerm, OnSearchTaskComplete listener);

    void getRecommendations(String id, OnHomeBooksTaskComplete listener);

    void getReviewedBookById(String book_id, OnReviewedBooksTaskComplete listener);

    void getReadingBookById(String book_id, OnReadingBooksTaskComplete listener);
}
