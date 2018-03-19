package com.cs428.app.bookapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.Book;

import java.util.ArrayList;

/**
 * Created by chees on 2/26/2018.
 */
// http://android.xsoftlab.net/training/tv/playback/card.html
// https://www.learnhowtoprogram.com/android/web-service-backends-and-custom-fragments/custom-adapters-with-recyclerview
public class BookCardListAdapter extends RecyclerView.Adapter<BookCardListAdapter.CardViewHolder> {
    private ArrayList<Book> recommendedBooks;

    public BookCardListAdapter(ArrayList<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View bookCardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        return new CardViewHolder(bookCardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Book book = recommendedBooks.get(position);
        holder.bookTitle.setText(book.getName());
        holder.bookDescription.setText(book.getSummary());
        holder.bookCover.setImageBitmap(book.getCover());

        holder.rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: go to rate page or pull up dialog rate box
            }
        });

        holder.recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: bring up recommend page
            }
        });

        holder.reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: go to book review page
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendedBooks.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView bookDescription, bookTitle;
        public Button rateButton, recommendButton, reviewButton;
        public ImageView bookCover;

        public CardViewHolder(View v) {
            super(v);
            bookCover = (ImageView) v.findViewById(R.id.book_cover);
            bookTitle = (TextView) v.findViewById(R.id.book_card_title);
            bookDescription = (TextView) v.findViewById(R.id.book_description);
            rateButton = (Button) v.findViewById(R.id.rate_button);
            recommendButton = (Button) v.findViewById(R.id.recommend_button);
            reviewButton = (Button) v.findViewById(R.id.review_button);
        }
    }

}
