package com.cs428.app.bookapp.networking;

import android.os.AsyncTask;
import android.util.Log;

import com.cs428.app.bookapp.interfaces.OnHomeBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.User;
import com.cs428.app.bookapp.interfaces.IServerCommunicator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
    public List<Person> getUsers(){
        return null;
    }


    /** Method to return the friends list of a given user.
     * @param id the id of the user associated with the desired friends list
     * @return the list of friends for a given user, null if does not exist.
     */
    @Override
    public List<Person> getFriends(String id) {
        return null;
    }

    /** Method to return user's friend's reading list.
     * @return list of books read by all the given user's friends.
     */
    @Override
    public List<Book> getUsersFriendsReadingList(String id) {
        return null;
    }

    /** Method to add a book to a given user's recommendation list
     * @param book the book to be added to the recommendation list
     * @return boolean indicating success.
     */
    @Override
    public void addRecommendation(Book book) {
        //TODO: create and call an addReccommendation async task
    }

    /** Method to add a book to a given user's reading list
     * @param book the book to be added to the reading list.
     * @return boolean indicating success
     */
    @Override
    public void addToReadingList(Book book) {
        //TODO: create and call an addToReadingList async task
    }

    /** Method to add another user to a certain user's friend's list.
     * @param otherId the id of the friend that they will be adding.
     * @return boolean indicating success
     */
    @Override
    public void followUser(String otherId) {
        //TODO: Create and call a follow user async task
    }

    /** Method to return user info for a specific user given an id.
     * @param name the id of the user to be fetched
     * @return the information for the given user, null if does not exist.
     */
    @Override
    public void loadUser(String name) {
        new GetCurrentUserTask().execute("/users/" + name + "/");
    }

    @Override
    public void getReviewedBookById(String id, OnReviewedBooksTaskComplete listener) {
        String bookUrl = "/book/" + id + "/";
        new GetReviewedBookTask(listener).execute(bookUrl);

    }

    @Override
    public void getReadingBookById(String book_id, OnReadingBooksTaskComplete listener) {
        //TODO
        String bookUrl = "/book/" + book_id + "/";
        new GetReadingBookTask(listener).execute(bookUrl);
    }

    /**
     *  Method to get a book by title (i.e. search for books by title). Fetches list of books
     * @param title the title of the book to be fetched.
     * @param listener
     * @return
     */
    @Override
    public void searchBookByTitle(String title, OnSearchTaskComplete listener) {
        String bookUrl = "/books/" + title + "/";
        new SearchBooksTask(listener).execute(bookUrl);
    }

    /**
     * Method to rate a book
     * @param bookId the id of the book being rated
     */
    @Override
    public void upvoteBook(String bookId) {
        String url = "/book/" +  bookId + "/upvote/";
        new RateBookTask().execute(url);
    }

    /**
     * Method to rate a book
     * @param bookId the id of the book being rated
     */
    @Override
    public void downvoteBook(String bookId) {
        String url = "/book/" +  bookId + "/downvote/";
        new RateBookTask().execute(url);
    }

    @Override
    public void updateUser(String username) {
        //TODO: Call async task to get the user with this username using the usertoken which should already be set.
    }

    @Override
    public void setUserToken(String userToken) {
        Log.d("DEBUG", "setUserToken: " + userToken);
        this.userToken = userToken;
    }

    @Override
    public String getUserToken() {

        return userToken;
    }

    @Override
    public void searchBookByAuthor(String searchTerm, OnSearchTaskComplete listener) {
        //TODO
    }

    @Override
    public void searchPersonByName(String searchTerm, OnSearchTaskComplete listener) {
        //TODO
    }

    @Override
    public void getRecommendations(String userid, OnHomeBooksTaskComplete listener) {
        String userUrl = "/book/" + userid + "/";
        new GetFollowing(listener).execute(userUrl);
        //new GetFollowing(listener).execute(userUrl);
    }

    // Don't delete, need for reference when implementing post requests.

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

    private class GetAllUsersTask extends AsyncTask<String, Void, List<Person>> {
        private OnHomeBooksTaskComplete listener;

        public GetAllUsersTask(OnHomeBooksTaskComplete listener) {
            this.listener = listener;
        }

        @Override
        protected List<Person> doInBackground(String... strings) {
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
                    Log.d("get all users", "doInBackground: "+response);
                    return serializer.deserializeListOfPersons(response);
                } else {
                    //Error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Person> persons){
            //TODO
            //Model.getSINGLETON().setUserSearchResults(users);
        }
    }

    private class GetReviewedBookTask extends AsyncTask<String, Void, Book> {
        private OnReviewedBooksTaskComplete listener;

        public GetReviewedBookTask(OnReviewedBooksTaskComplete listener){
            this.listener = listener;
        }
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
                    Log.d("DEBUG", "doInBackground: Book with that id could not be found.");
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
            listener.addReviewedBook(book);
        }
    }

    private class SearchBooksTask extends AsyncTask<String, Void, List<Book>> {
        OnSearchTaskComplete listener;

        public SearchBooksTask(OnSearchTaskComplete listener) {
            this.listener = listener;
        }

        @Override
        protected List<Book> doInBackground(String... strings) {
            try {
                URL url = new URL(BASE_URL + strings[0]);
                Log.d("DEBUG", "doInBackground: Search Books URL "+url.toString());

                // Make http connections and requests.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", userToken);
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readResponse(connection);
                    Log.d("DEBUG", "doInBackground: Search Books Task OK");
                    return serializer.deserializeListOfBooks(response);
                } else {
                    Log.d("DEBUG", "doInBackground: Search Books Task NOT OK " + responseCode);
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            this.listener.addBooks(books);
        }
    }

    private class GetReadingBookTask extends AsyncTask<String, Void, Book>{
        private OnReadingBooksTaskComplete listener;

        public GetReadingBookTask(OnReadingBooksTaskComplete listener){
            this.listener = listener;
        }

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
                    Log.d("DEBUG", "doInBackground: Book with that id could not be found.");
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
            listener.addReadingBook(book);
        }

    }

    private class RateBookTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(BASE_URL + strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                // Below code is for reference when writing to post request
                /*connection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(requestBody);
                out.flush();
                out.close();
                */

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //TODO:
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetFollowing extends AsyncTask<String, Void,Book> {
        private OnHomeBooksTaskComplete listener;

        public GetFollowing(OnHomeBooksTaskComplete listener) {
            this.listener = listener;
        }

        @Override
        protected Book doInBackground(String... strings) {
            try {
                URL url = new URL(BASE_URL + strings[0]);

                // Make http connections and requests.
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", userToken);
                int responseCode = connection.getResponseCode();
                Log.d("GET FOLLOWING", "doInBackground: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String response = readResponse(connection);
                    Log.d("GET FOLLOWING", "doInBackground: "+ response);
                    return serializer.deserializeBook(response);

                } else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    // Book not found
                    Log.d("DEBUG", "doInBackground: Book with that id could not be found.");
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
            Log.d("DEBUG", "onPostExecute: "+ book.getTitle());
            listener.addHomeBook(book);
        }
    }

    /********************************************************************************************/

}
