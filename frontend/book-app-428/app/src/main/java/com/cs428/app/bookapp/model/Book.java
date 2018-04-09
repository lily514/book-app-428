package com.cs428.app.bookapp.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cs428.app.bookapp.interfaces.IBookPresenter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by chees on 3/8/2018.
 */

public class Book implements IBookPresenter{
   // private static final long serialVersionUID = ThreadLocalRandom.current().nextLong();

    private String title;
    private String author;
    private String isbn;
    private String coverURL;
    private float rating;
    private String summary;
    private String date;
    private List<BookReview> reviews;

    public Book(String title, String author, String isbn, String coverURL) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.coverURL = coverURL;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getMeta() {return isbn;}


    public Bitmap getCover() {
        return null;
//        try {
//            //TODO: THIS HAS TO BE DONE IN AN ASYNC TASK
//            URL url = new URL(this.coverURL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap cover = BitmapFactory.decodeStream(input);
//            return cover;
//        } catch (IOException e) {
//            return null;
//        }
    }


    public float getRating(){return rating;}
    public  void setRating(float rating){this.rating = rating;}


    public String getSummary(){return summary;}
    public void setSummary(String summary){this.summary = summary;}


    public String getDate() {return date;}
    public void setDate(String date){this.date = date;}

    public void setReviews(List<BookReview> reviews) {
        this.reviews = reviews;
    }
    public List<BookReview> getReviews() {
        return reviews;
    }
}
