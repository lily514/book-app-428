package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;
import com.cs428.app.bookapp.networking.ServerCommunicator;

import java.util.List;

/**
 * Created by mgard on 3/8/2018.
 */

public interface IServerProxy {

    /**
     * Method to log a user in given a username and password and return that user's info
     * @param username the given username
     * @param password the login password
     * @return a user associated with the credentials. Null if incorrect/none exists.
     */
    public User login(String username, String password);

    /**
     * Method to register a new user
     * @param user the new user object representing the user to be registered
     * @return a boolean indicating success
     */
    public boolean registerUser(User user);

    /**
     * Method for a given user to follow another user
     * @param user the user who is adding the friend
     * @param followUsername username of friend to be added to user's friend's list
     * @return a boolean indicating success
     */
    public boolean followFriend(User user, String followUsername);

    /**
     * Method to search for a book by a given search term
     * @param searchTerm the term to search for a book by
     * @return a list of books associated with the given term
     */
    public List<Book> searchBook(String searchTerm);

    /**
     * Method for a user to recommend a book.
     * @param user the user making the recommendation
     * @param bookId the book being recommended
     * @return a boolean indicating success
     */
    public boolean recommendBook(User user, String bookId);

    /**
     * Method to submit a rating for a given book by a given user
     * Note: This could be a binary 1/0 representing up/down vote or a scale (1-10 for example)
     *  depending on what we want to support in the application.
     *
     * @param user the user submitting the rating
     * @param book the book being rated
     * @param rating the rating to be submitted
     * @return a boolean indicating success
     */
    public boolean rateBook(User user, Book book, int rating);

    /**
     * Mehtod to get the recommendations for a given user
     * @param user the user for which the recommendations are for
     * @return a list of books recommended for that user
     */
    public List<Book> getRecommendationFor(User user);


}
