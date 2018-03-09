package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.networking.ServerCommunicator;

import java.util.List;

/**
 * Created by mgard on 3/8/2018.
 */

public interface IServerProxy {

    /** TODO: Change return type to be of type User when model supports it
     * Method to log a user in given a username and password and return that user's info
     * @param username the given username
     * @param password the login password
     * @return a user associated with the credentials. Null if incorrect/none exists.
     */
    public Object login(String username, String password);

    /** TODO: Change param user to be of type User when model supports it.
     * Method to register a new user
     * @param user the new user object representing the user to be registered
     * @return a boolean indicating success
     */
    public boolean registerUser(Object user);

    /** TODO: Change param type to be of type User when model supports it.
     * Method for a given user to follow another user
     * @param user the user who is adding the friend
     * @param followUsername username of friend to be added to user's friend's list
     * @return a boolean indicating success
     */
    public boolean followFriend(Object user, String followUsername);

    /** TODO: Change return type to List<Book> when model supports it.
     * Method to search for a book by a given search term
     * @param searchTerm the term to search for a book by
     * @return a list of books associated with the given term
     */
    public List<Object> searchBook(String searchTerm);

    /** TODO: Change param user to be of type User when model supports it.
     * Method for a user to recommend a book.
     * @param user the user making the recommendation
     * @param book the book being recommended
     * @return a boolean indicating success
     */
    public boolean recommendBook(Object user, String book);

    /** TODO: Change param user, book to be corresponding model types when model supports it.
     * Method to submit a rating for a given book by a given user
     * Note: This could be a binary 1/0 representing up/down vote or a scale (1-10 for example)
     *  depending on what we want to support in the application.
     *
     * @param user the user submitting the rating
     * @param book the book being rated
     * @param rating the rating to be submitted
     * @return a boolean indicating success
     */
    public boolean rateBook(Object user, Object book, int rating);

    /** TODO: Change return type to List<Book> and param user to type User when model supports it.
     * Mehtod to get the recommendations for a given user
     * @param user the user for which the recommendations are for
     * @return a list of books recommended for that user
     */
    public List<Object> getRecommendationFor(Object user);


}
