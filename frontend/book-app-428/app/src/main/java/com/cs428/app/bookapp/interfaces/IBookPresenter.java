package com.cs428.app.bookapp.interfaces;

import android.graphics.Bitmap;

import com.cs428.app.bookapp.model.BookReview;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chees on 3/31/2018.
 */

public interface IBookPresenter extends Serializable {
    Bitmap getCover(OnBitmapComplete listener);
    String getTitle();
    String getAuthor();
    String getMeta();
    String getSummary();
    List<BookReview> getReviews();
}
