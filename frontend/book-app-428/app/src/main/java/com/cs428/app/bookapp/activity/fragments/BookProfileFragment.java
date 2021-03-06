package com.cs428.app.bookapp.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.adapter.BookReviewsListAdapter;
import com.cs428.app.bookapp.interfaces.IBookPresenter;
import com.cs428.app.bookapp.interfaces.OnBitmapComplete;
import com.cs428.app.bookapp.interfaces.Serializable;

public class BookProfileFragment extends Fragment {
    private IBookPresenter bookPresenter;
    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private TextView bookMeta;
    private TextView bookSummary;
    private RecyclerView bookReviews;
    private Button rateButton;
    private Button reviewButton;
    private Button recommendButton;

    public BookProfileFragment() {}
  
    public static BookProfileFragment newInstance(Serializable book) {
        BookProfileFragment fragment = new BookProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESENTER", book);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookPresenter = (IBookPresenter) getArguments().getSerializable("PRESENTER");
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity)getActivity()).setBannerTitle("Book");
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

        bookCover.setImageBitmap(bookPresenter.getCover((OnBitmapComplete) this));
        bookTitle.setText(bookPresenter.getTitle());
        bookAuthor.setText(bookPresenter.getAuthor());
        bookMeta.setText(bookPresenter.getMeta());
        bookSummary.setText(bookPresenter.getSummary());

        BookReviewsListAdapter adapter = new BookReviewsListAdapter(bookPresenter.getReviews());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        bookReviews.setLayoutManager(layoutManager);
        bookReviews.setAdapter(adapter);

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: route to new fragment
            }
        });

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: route to new fragment
            }
        });

        recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: route to new fragment
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_page_layout, container, false);

        attachLayoutElements(v);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.banner);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        return v;
    }
}
