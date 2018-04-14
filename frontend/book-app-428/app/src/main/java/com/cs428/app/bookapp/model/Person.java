package com.cs428.app.bookapp.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.amazonaws.auth.policy.Resource;
import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.interfaces.OnBitmapComplete;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by chees on 3/8/2018.
 */

public class Person {
    protected String name;
    protected String id;
    protected List<String> readingList = null;
    protected List<String> reviewedBooks = null;
    protected boolean isUser = false;
    protected String photoURL;
    protected Bitmap photoBitmap;
    protected String bio;

    public Person(){
    }

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
        this.photoBitmap = null;
        this.photoURL = null;
    }

    public boolean isUser() { return isUser; }

    public String getName() { return name; }

    public String getId() { return id; }

    public List<String> getReadingList() {
        return readingList;
    }

    public List<String> getReviewedBooks() {
        return reviewedBooks;
    }

    public void setReadingList(List<String> readingList) {
        this.readingList = readingList;
    }

    public void setReviewedBooks(List<String> reviewedBooks) {
        this.reviewedBooks = reviewedBooks;
    }

    public void setURL(String URL) {
        this.photoURL = URL;

    }

    public Bitmap getCover(OnBitmapComplete listener) {
        // may return null if there is exception when fetching from given URL
        if (photoBitmap == null) {
            if (photoURL != null){
                Person.FetchCoverFromURL fetchCoverFromURL = new Person.FetchCoverFromURL(listener);
                fetchCoverFromURL.execute(photoURL);
            }

            return BitmapFactory.decodeResource(Resources.getSystem(),
                    R.drawable.profile_pic_frame);
        }
        return photoBitmap;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public class FetchCoverFromURL extends AsyncTask<String, Void, Bitmap> {
        OnBitmapComplete listener;
        public FetchCoverFromURL(OnBitmapComplete listener){
            this.listener = listener;
        }

        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap cover = BitmapFactory.decodeStream(input);
                return cover;
            } catch (IOException e) {
                return BitmapFactory.decodeResource(Resources.getSystem(),
                        R.drawable.profile_pic_frame);    // if exception occurs, return a null bitmap
            }
        }

        protected void onPostExecute(Bitmap cover) {

            photoBitmap = cover;
            listener.updateImages();

        }
    }
}
