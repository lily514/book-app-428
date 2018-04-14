package com.cs428.app.bookapp.interfaces;

import com.cs428.app.bookapp.model.User;
//import com.cs428.app.bookapp.networking.ServerCommunicator;


/**
 * Created by mgard on 3/8/2018.
 */

public interface IServerProxy {

    /**
     * Method called when app first loads. Leads to call to servercommunicator to get information from backend
     * for the current cognito user. This will then set the correct info in the model which should trigger
     * the observers.
     */
    public void initialize();

    /**
     * Method for a given user to follow another user
     * @param user the user who is adding the friend
     * @param followUsername username of friend to be added to user's friend's list
     * @return a boolean indicating success
     */
    public boolean followFriend(User user, String followUsername);

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
     * @param bookID the id of the book being rated
     */
    public void upvoteBook(String bookID);


    public void downvoteBook(String bookID);


    public void getRecommendationFor(String userID, OnHomeBooksTaskComplete listener);

    public void searchBook(String searchTerm, OnSearchTaskComplete listener);

    void searchPerson(String searchString, OnSearchTaskComplete listener);

    void getReadingBookById(String book_id, OnReadingBooksTaskComplete listener);

    void getReviewedBookById(String book_id, OnReviewedBooksTaskComplete listener);
}
