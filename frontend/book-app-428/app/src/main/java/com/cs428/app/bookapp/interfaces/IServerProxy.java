package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;
//import com.cs428.app.bookapp.networking.ServerCommunicator;

import java.util.List;

/**
 * Created by mgard on 3/8/2018.
 */

public interface IServerProxy {

    /**
     * Method for a given user to follow another user
     * @param user the user who is adding the friend
     * @param followUsername username of friend to be added to user's friend's list
     * @return a boolean indicating success
     */
    public void followFriend(User user, String followUsername);

    /**
     * Method to search for a book by a given search term
     * @param searchTerm the term to search for a book by
     */
    public void searchBook(String searchTerm);

    /**
     * Method for a user to recommend a book.
     * @param user the user making the recommendation
     * @param bookId the book being recommended
     * @return a boolean indicating success
     */
    public void recommendBook(User user, String bookId);

    /**
     * Method to submit a rating for a given book by a given user
     * Note: This could be a binary 1/0 representing up/down vote or a scale (1-10 for example)
     *  depending on what we want to support in the application.
     *
     * @param user the user submitting the rating
     * @param bookID the id of the book being rated
     * @param rating the rating to be submitted
     * @return a boolean indicating success
     */
    public void rateBook(User user, String bookID, int rating);

    /**
     * Mehtod to get the recommendations for a given user
     * @param user the user for which the recommendations are for
     * @return a list of books recommended for that user
     */
    public void getRecommendationFor(User user);

    /**
     * Method to get a book object by the bookID
     * @param bookId the id of the book in the database
     * @return a book object
     */
    public void getBookById(String bookId);

    /**
     * Method called when app first loads. Leads to call to servercommunicator to get information from backend
     * for the current cognito user. This will then set the correct info in the model which should trigger
     * the observers.
     */
    public void initialize();
}
