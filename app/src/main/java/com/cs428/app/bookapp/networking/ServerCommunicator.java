package com.cs428.app.bookapp.networking;

import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by mgard on 3/2/2018.
 */

public class ServerCommunicator {

    private Serializer serializer;
    private final String BASE_URL = "http://www.test.com/";

    public ServerCommunicator(Serializer serializer) {
        this.serializer = serializer;
    }

    /** Method to fetch a list of all users from the server
     * @return the list of all users. Null if no users exist
     */
    public List<User> getUsers(){
        String usersUrl = "/users";
        String requestBody = "";
        String jsonResponse = null;
        try {
            jsonResponse = this.sendGetRequest(requestBody, usersUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return this.serializer.deserializeListOfUsers(jsonResponse);
    }

    /** Method to return user info for a specific user given an id.
     * TODO: Change return type to User when model supports it.
     * @param id the id of the user to be fetched
     * @return the information for the given user, null if does not exist.
     */
    public User getUser(int id) {
        String userUrl = "/users/" + id;
        String jsonResponse = null;
        try {
            jsonResponse = this.sendGetRequest("", userUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return this.serializer.deserializeUser(jsonResponse);
    }

    /** Method to return the friends list of a given user.
     * @param id the id of the user associated with the desired friends list
     * @return the list of friends for a given user, null if does not exist.
     */
    public List<User> getFriends(int id) {
        String friendsUrl = "/users/" + id + "/friends";
        String jsonResponse = null;
        try {
            jsonResponse = this.sendGetRequest("", friendsUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return this.serializer.deserializeListOfUsers(jsonResponse);
    }

    /** Method to return user's friend's reading list.
     * @param id id of user with friend list to search.
     * @return list of books read by all the given user's friends.
     */
    public List<Book> getUsersFriendsReadingList(int id) {
        String listUrl = "/users/" + id + "/friends/books";
        String jsonResponse = null;
        try {
            jsonResponse = this.sendGetRequest("", listUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return this.serializer.deserializeListOfBooks(jsonResponse);
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
    public boolean addRecommendation(String id, Book book) {
        String recUrl = "/users/" + id + "/recommendation";
        String reqeustBody = this.serializer.serializeBook(book);
        try {
            this.sendPostRequest(reqeustBody, recUrl);
            //TODO: Change exception type
        } catch (Exception e) {
            System.out.println("Error****");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** Method to add a book to a given user's reading list
     * @param id the id of the user associated with the reading list.
     * @param book the book to be added to the reading list.
     * @return boolean indicating success
     */
    public boolean addToReadingList(int id, Book book) {
        String listUrl = "/users/" + id + "/readingList";
        String requestBody = this.serializer.serializeBook(book);
        try {
            this.sendPostRequest(requestBody, listUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** Method to add another user to a certain user's friend's list.
     * @param myId the id of the person who is adding the friend
     * @param otherId the id of the friend that they will be adding.
     * @return boolean indicating success
     */
    public boolean followUser(String myId, String otherId) {
        String followUrl = "/users/" + myId + "/follow";
        String requestBody = otherId;
        try {
            this.sendPostRequest(requestBody, followUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
    public Book getBookById(String id) {
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

    private String sendGetRequest(String requestBody, String uri) throws IOException {
        URL url = new URL(BASE_URL + uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            return readResponse(connection);
        } else {
            return null;
        }
    }

    private String sendPostRequest(String requestBody, String uri) throws IOException {
        URL url = new URL(BASE_URL + uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(requestBody);
        out.flush();
        out.close();

        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) {
            return readResponse(connection);
        } else {
            return null;
        }
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while((inputLine = reader.readLine()) != null) {
            builder.append(inputLine);
        }

        return builder.toString();
    }

}
