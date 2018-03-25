package com.cs428.app.bookapp.activity.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.adapter.BookSummaryListAdapter;
import com.cs428.app.bookapp.model.Book;

import java.io.Serializable;


/**
 * Created by emilyprigmore on 3/11/18.
 */

public class BookProfileFragment extends Fragment {

    private Book book;

    private ImageView bookCover;
    private TextView title;
    private TextView author;
    private TextView metadata;
    private RatingBar ratingBar;
    private TextView summary;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView bookSummaryList;
    private RecyclerView.Adapter bookSummaryListAdapter;

    public static BookProfileFragment newInstance(Serializable book) {
        BookProfileFragment fragment = new BookProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BOOK", book);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (Book) getArguments().getSerializable(
                "BOOK");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.book_page_layout, container, false);

        layoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL,
                false);

        View coverView = rootView.findViewById(R.layout.book_cover_view);
        bookCover = (ImageView) coverView.findViewById(R.id.book_cover);

        title = (TextView) rootView.findViewById(R.id.book_title);
        author = (TextView) rootView.findViewById(R.id.book_author);
        metadata = (TextView) rootView.findViewById(R.id.book_meta);

        ratingBar = (RatingBar) rootView.findViewById(R.id.book_rating_bar);

        summary = (TextView) rootView.findViewById(R.id.book_summary);

        bookSummaryList = (RecyclerView) rootView.findViewById(R.id.book_reviews);
        bookSummaryListAdapter = new BookSummaryListAdapter(book.getReviews());
        bookSummaryList.setLayoutManager(layoutManager);
        bookSummaryList.setAdapter(bookSummaryListAdapter);

        initializeBookInfo();

        return rootView;
    }

    private void initializeBookInfo() {
        title.setText(book.getName());
        author.setText(book.getAuthor());
        author.setText(book.getAuthor());
        String isbn = book.getIsbn();
        String publishDate = book.getDate();
        metadata.setText(publishDate + "|" + isbn);
        ratingBar.setRating(book.getRating());
        bookCover.setImageBitmap(book.getCover());
    }
}
