package com.cs428.app.bookapp.networking;

import android.media.midi.MidiOutputPort;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;
import com.cs428.app.bookapp.interfaces.IServerCommunicator;
import com.cs428.clientsdk.model.Empty;

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

public class ServerCommunicator implements IServerCommunicator {

    private Serializer serializer;
    private final String BASE_URL = "https://qu2ui0yvcd.execute-api.us-west-2.amazonaws.com/PROD";
    private String userToken;

    public ServerCommunicator(Serializer serializer) {
        this.serializer = serializer;
    }

    /** Method to fetch a list of all users from the server
     * @return the list of all users. Null if no users exist
     */
    @Override
    public List<User> getUsers(){
        return null;
    }

    /** Method to return user info for a specific user given an id.
     * TODO: Change return type to User when model supports it.
     * @param name the id of the user to be fetched
     * @return the information for the given user, null if does not exist.
     */
    @Override
    public void loadUser(String name) {
        new GetCurrentUserTask().execute("/users/" + name + "/");
    }

    /** Method to return the friends list of a given user.
     * @param id the id of the user associated with the desired friends list
     * @return the list of friends for a given user, null if does not exist.
     */
    @Override
    public List<User> getFriends(String id) {
        return null;
    }

    /** Method to return user's friend's reading list.
     * @param id id of user with friend list to search.
     * @return list of books read by all the given user's friends.
     */
    @Override
    public List<Book> getUsersFriendsReadingList(String id) {
        return null;
    }

    /** Method to add a book to a given user's recommendation list
     * @param id the id of the user recommending the book.
     * @param book the book to be added to the recommendation list
     * @return boolean indicating success.
     */
    @Override
    public boolean addRecommendation(String id, Book book) {
        //TODO: create and call an addReccommendation async task
        return false;
    }

    /** Method to add a book to a given user's reading list
     * @param id the id of the user associated with the reading list.
     * @param book the book to be added to the reading list.
     * @return boolean indicating success
     */
    @Override
    public boolean addToReadingList(String id, Book book) {
        //TODO: create and call an addToReadingList async task
        return false;
    }

    /** Method to add another user to a certain user's friend's list.
     * @param myId the id of the person who is adding the friend
     * @param otherId the id of the friend that they will be adding.
     * @return boolean indicating success
     */
    @Override
    public boolean followUser(String myId, String otherId) {
        //TODO: Create and call a follow user async task
        return false;
    }


    /** Method to search for a book with a given search term.
     * @param searchString
     * @return a list of books (or ids) associated with the search term. Null if none exist.
     */
    @Override
    public List<Book> searchForBook(String searchString) {
        return null;
    }

    /** Method to get a book by id NOT ISBN. Fetches single book
     * @param id id of book to be fetched
     * @return the book associated with the input id. Null if none found.
     */
    @Override
    public Book getBookById(String id) {
        String bookUrl = "/book/" + id + "/";
        String response;
        new GetBookTask().execute(bookUrl);
        return null; // TODO: Return book asynchronously
    }

    /**
     *  Method to get a book by title (i.e. search for books by title). Fetches list of books
     * @param title the title of the book to be fetched.
     * @return
     */
    @Override
    public Book searchBookByTitle(String title) {
        String bookUrl = "/books/" + title + "/";
        String response;
        new SearchBooksTask().execute(bookUrl);
        return null; // TODO: Return book asynchronously
    }

    /**
     * Method to rate a book
     * @param userId the id of the user rating the book
     * @param bookId the id of the book being rated
     * @param rating the rating given to the book
     * @return a value indicating success.
     */
    @Override
    public boolean rateBook(String userId, String bookId, int rating) {
        return false;
    }

    @Override
    public void updateUser(String username) {
        //TODO: Call async task to get the user with this username using the usertoken which should already be set.
    }

    @Override
    public String getUserToken() {
        return userToken;
    }

    @Override
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    // Don't delete, need for reference when implementing post requests.

   /* private String sendPostRequest(String requestBody, String uri) throws IOException {
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
    } */

    private String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while((inputLine = reader.readLine()) != null) {
            builder.append(inputLine);
        }

        return builder.toString();
    }

    /**************************     Start AsyncTask Classes     *********************************/

    /**
     * This class makes an async call to the backend and then updates the model on the front end.
     * It is only valid for that use, and returns nothing.
     */
    private class GetCurrentUserTask extends AsyncTask<String, Void, User> {

        /**
         * Method called by thisclass.execute(...). RUNS ON OWN THREAD
         * @param strings the parameters
         * @return value returned to onPostExecute method.
         */
        @Override
        protected User doInBackground(String... strings) {
            try {
                // Get the correct url by joining base url with the parameter passed to object on execute call.
                URL url = new URL(BASE_URL + strings[0]);

                // Make http connections and requests.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", userToken);
                int responseCode = connection.getResponseCode();
                // Ensure successful connection with backend
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readResponse(connection);
                    return serializer.deserializeUser(response);
                } else {
                    //Error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * Method automatically called after completion of background task. RUNS ON UI THREAD
         * @param result the value returned from the above method.
         */
        @Override
        protected void onPostExecute(User result) {
            Log.d("DEBUG_COMMS", "onPostExecute: got user result");
            Model.getSINGLETON().setCurrentUser(result);
        }
    }

    private class GetAllUsersTask extends AsyncTask<String, Void, List<User>> {

        @Override
        protected List<User> doInBackground(String... strings) {
            try {
                // Get the correct url by joining base url with the parameter passed to object on execute call.
                URL url = new URL(BASE_URL + "/users/");

                // Make http connections and requests.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", userToken);
                int responseCode = connection.getResponseCode();
                // Ensure successful connection with backend
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readResponse(connection);
                    return serializer.deserializeListOfUsers(response);
                } else {
                    //Error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<User> users){
            Model.getSINGLETON().setUserSearchResults(users);
        }
    }

    private class GetBookTask extends AsyncTask<String, Void, Book> {

        @Override
        protected Book doInBackground(String... strings) {
            try {
                URL url = new URL(BASE_URL + strings[0]);

                // Make http connections and requests.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", userToken);
                int responseCode = connection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readResponse(connection);
                    return serializer.deserializeBook(response);

                } else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    // Book not found
                    System.out.println("Book with that id could not be found.");
                    return null;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Book book) {
            Model.getSINGLETON().setFetchedBook(book);
        }
    }

    private class SearchBooksTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... strings) {
            try {
                URL url = new URL(BASE_URL + strings[0]);

                // Make http connections and requests.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", userToken);
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readResponse(connection);
                    return serializer.deserializeListOfBooks(response);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            Model.getSINGLETON().setBookSearchResults(books);
        }
    }

    /********************************************************************************************/

}
