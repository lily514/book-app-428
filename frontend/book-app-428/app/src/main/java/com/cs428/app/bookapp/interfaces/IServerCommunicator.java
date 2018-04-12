package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by mgard on 3/10/2018.
 */

public interface IServerCommunicator {
    List<Person> getUsers();

    void loadUser(String name);

    List<Person> getFriends(String id);

    void updateUser(String username);

    void addRecommendation(Book book);

    void addToReadingList(Book book);

    void followUser(String otherUsername);

    List<Book> getUsersFriendsReadingList(String id);

    void upvoteBook(String bookId);

    void downvoteBook(String bookId);

    void setUserToken(String token);

    String getUserToken();

    void searchBookByAuthor(String searchTerm, OnSearchTaskComplete listener);

    void searchBookByTitle(String searchTerm, OnSearchTaskComplete listener);

    void searchPersonByName(String searchTerm, OnSearchTaskComplete listener);

    void getRecommendations(String id, OnHomeBooksTaskComplete listener);

    void getReviewedBookById(String book_id, OnReviewedBooksTaskComplete listener);

    void getReadingBookById(String book_id, OnReadingBooksTaskComplete listener);
}
