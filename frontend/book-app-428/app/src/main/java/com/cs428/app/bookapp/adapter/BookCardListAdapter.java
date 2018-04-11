package com.cs428.app.bookapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.Book;

import java.util.List;

/**
 * Created by chees on 2/26/2018.
 */
// http://android.xsoftlab.net/training/tv/playback/card.html
// https://www.learnhowtoprogram.com/android/web-service-backends-and-custom-fragments/custom-adapters-with-recyclerview
public class BookCardListAdapter extends RecyclerView.Adapter<BookCardListAdapter.CardViewHolder> {
    private List<Book> book_ids;
    private ViewGroup parent;

    public BookCardListAdapter(List<Book> recommendedBooks) {

        this.book_ids = recommendedBooks;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View bookCardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        return new CardViewHolder(bookCardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Book book = book_ids.get(position);
        holder.bookTitle.setText(book.getTitle());
        holder.bookCover.setImageBitmap(book.getCover());

        holder.upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(parent.getContext(), "Up clicked", Toast.LENGTH_SHORT).show();
                // TODO: go to rate page or pull up dialog rate box

            }
        });

        holder.downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(parent.getContext(), "Down clicked", Toast.LENGTH_SHORT).show();
                // TODO: bring up recommend page
            }
        });

    }

    @Override
    public int getItemCount() {
        if (book_ids != null)
            return book_ids.size();
        else
            return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView bookTitle;
        public ImageButton upButton, downButton;
        public ImageView bookCover;

        public CardViewHolder(View v) {
            super(v);
            bookCover = (ImageView) v.findViewById(R.id.book_cover);
            bookTitle = (TextView) v.findViewById(R.id.book_card_title);
            upButton = (ImageButton) v.findViewById(R.id.upvote_button);
            downButton = (ImageButton) v.findViewById(R.id.downvote_button);
        }
    }



}
