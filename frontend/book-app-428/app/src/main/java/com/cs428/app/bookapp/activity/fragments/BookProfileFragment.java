package com.cs428.app.bookapp.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.FragmentDrawer;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.interfaces.IBookPresenter;
import com.cs428.app.bookapp.interfaces.IHomePresenter;
import com.cs428.app.bookapp.model.Book;

import java.io.Serializable;

/**
 * Created by chees on 3/31/2018.
 */

public class BookProfileFragment extends Fragment {
    private IBookPresenter book;
    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookMeta;
    private TextView bookSummary;
    private RecyclerView bookReviews;
    private Button rateButton;
    private Button reviewButton;
    private Button recommendButton;
    private IHomePresenter presenter;

    public BookProfileFragment() {}

    public static BookProfileFragment newInstance(Serializable presenter, IBookPresenter book) {
        BookProfileFragment fragment = new BookProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BOOK", book);
        bundle.putSerializable("PRESENTER", presenter);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        book = (IBookPresenter) getArguments().getSerializable("BOOK");
        presenter = (IHomePresenter) getArguments().getSerializable(
                "PRESENTER");
    }

    public void attachLayoutElements(View v){
        bookCover = (ImageView) v.findViewById(R.id.book_cover);
        bookTitle = (TextView) v.findViewById(R.id.book_title);
        bookAuthor = (TextView) v.findViewById(R.id.book_author);
        bookMeta = (TextView) v.findViewById(R.id.book_meta);
        bookSummary = (TextView) v.findViewById(R.id.book_summary);
        bookReviews = (RecyclerView) v.findViewById(R.id.book_reviews);
        rateButton = (Button) v.findViewById(R.id.rate_button);
        reviewButton = (Button) v.findViewById(R.id.review_button);
        recommendButton = (Button) v.findViewById(R.id.recommend_button);

        bookCover.setImageBitmap(book.getCover());
        bookTitle.setText(book.getTitle());
        bookAuthor.setText(book.getAuthor());
        bookMeta.setText(book.getMeta());
        bookSummary.setText(book.getSummary());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_page_layout, container, false);

        attachLayoutElements(v);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.banner);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        FragmentDrawer fragmentDrawer = (FragmentDrawer)
                getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) v.findViewById(R.id.drawer_layout), toolbar);

        return v;
    }
}
