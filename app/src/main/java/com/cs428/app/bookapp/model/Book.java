package com.cs428.app.bookapp.model;


import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by chees on 3/8/2018.
 */

public class Book{// implements Serializable{
   // private static final long serialVersionUID = ThreadLocalRandom.current().nextLong();

    private String name;
    private String author;
    private String isbn;
    private String coverURL;
    private float rating;
    private String summary;
    private String date;
    private List<String> reviews;

    public Book(String name, String author, String isbn, String URL) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.coverURL = URL;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }


    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getCover() {
        return coverURL;
    }
    public void setCover(String URL) {
        this.coverURL = URL;
    }


    public float getRating(){return rating;}
    public  void setRating(float rating){this.rating = rating;}


    public String getSummary(){return summary;}
    public void setSummary(String summary){this.summary = summary;}


    public String getDate() {return date;}
    public void setDate(String date){this.date = date;}
}
