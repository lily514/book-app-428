package com.cs428.app.bookapp.networking;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerCommunicator {

    private Serializer serializer;

    public ServerCommunicator(Serializer serializer) {
        this.serializer = serializer;
    }

    /** Method to fetch a list of all users from the server
     * @return the list of all users. Null if no users exist
     */
    public List<User> getUsers(){
        return null;
    }

    /** Method to return user info for a specific user given an id.
     * TODO: Change return type to User when model supports it.
     * @param id the id of the user to be fetched
     * @return the information for the given user, null if does not exist.
     */
    public User getUser(int id) {
        return null;
    }

    /** Method to return the friends list of a given user.
     * @param id the id of the user associated with the desired friends list
     * @return the list of friends for a given user, null if does not exist.
     */
    public List<User> getFriends(int id) {
        return null;
    }

    /** Method to return user's friend's reading list.
     * @param id id of user with friend list to search.
     * @return list of books read by all the given user's friends.
     */
    public List<Object> getUsersFriendsReadingList(int id) {
        return null;
    }

    /** Method to log a user in
     * @param username user's username
     * @param password user's password
     * @return a user if successful, null otherwise.
     */
    public User login(String username, String password) {
        return null;
    }

    /** Method to register a user with the server
     * @param user the new User object to be registered
     * @return a boolean indicating if the action was successful
     */
    public boolean registerUser(User user) {
        return false;
    }

    /** Method to add a book to a given user's recommendation list
     * @param id the id of the user recommending the book.
     * @param book the book to be added to the recommendation list
     * @return boolean indicating success.
     */
    public boolean addRecommendation(int id, Book book) {
        return false;
    }

    /** Method to add a book to a given user's reading list
     * @param id the id of the user associated with the reading list.
     * @param book the book to be added to the reading list.
     * @return boolean indicating success
     */
    public boolean addToReadingList(int id, Book book) {
        return false;
    }

    /** Method to add another user to a certain user's friend's list.
     * @param myId the id of the person who is adding the friend
     * @param otherId the id of the friend that they will be adding.
     * @return boolean indicating success
     */
    public boolean followUser(int myId, int otherId) {
        return false;
    }

    /** Method to search for a book with a given search term.
     * @param searchString
     * @return a list of books (or ids) associated with the search term. Null if none exist.
     */
    public List<Book> searchForBook(String searchString) {
        return null;
    }

    /** Method to get a book by id.
     * @param id id of book to be fetched
     * @return the book associated with the input id. Null if none found.
     */
    public Book getBookById(int id) {
        return null;
    }

    /**
     * Method to rate a book
     * @param userId the id of the user rating the book
     * @param bookId the id of the book being rated
     * @param rating the rating given to the book
     * @return a value indicating success.
     */
    public boolean rateBook(int userId, int bookId, int rating) {
        return false;
    }

}
