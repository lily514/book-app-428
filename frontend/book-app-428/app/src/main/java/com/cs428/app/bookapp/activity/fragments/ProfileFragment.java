package com.cs428.app.bookapp.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.interfaces.IProfilePresenter;
import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor on 2/10/2018.
 */

public class ProfileFragment extends Fragment implements OnReadingBooksTaskComplete, OnReviewedBooksTaskComplete {

    private RecyclerView readingListRecyclerView;
    private RecyclerView reviewedListRecyclerView;
    private RecyclerView.Adapter readingListAdapter;
    private RecyclerView.Adapter reviewedListAdapter;
    private RecyclerView.LayoutManager readingLayoutManager;
    private RecyclerView.LayoutManager reviewedLayoutManager;
    private Button actionButton;
    private TextView nameText;
    private IProfilePresenter presenter;
    private Person person;

    private List<Book> readingList;
    private List<Book> reviewList;

    // Necessary empty constructor
    public ProfileFragment() {}

    public static ProfileFragment newInstance(Serializable presenter) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESENTER", presenter);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (IProfilePresenter) getArguments().getSerializable(
                "PRESENTER");
        person = presenter.getPerson();
//        reviewList = new ArrayList<Book> ();
//        readingList = new ArrayList<Book>();
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity)getActivity()).setBannerTitle("Profile");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        readingLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        reviewedLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);

        readingListRecyclerView = (RecyclerView) v.findViewById(R.id.reading_list);
        reviewedListRecyclerView = (RecyclerView) v.findViewById(R.id.reviewed_list);

        readingListAdapter = new BookCardListAdapter(readingList);
        reviewedListAdapter = new BookCardListAdapter(reviewList);

        readingListRecyclerView.setLayoutManager(readingLayoutManager);
        readingListRecyclerView.setAdapter(readingListAdapter);

        reviewedListRecyclerView.setLayoutManager(reviewedLayoutManager);
        reviewedListRecyclerView.setAdapter(reviewedListAdapter);

        actionButton = (Button) v.findViewById(R.id.person_action);

        nameText = (TextView) v.findViewById(R.id.person_name);
        nameText.setText(presenter.getPerson().getName());

        // You can't follow yourself, silly!
        if (presenter.getPerson() != null && presenter.getPerson().isUser()) {
            actionButton.setVisibility(View.INVISIBLE);
        }

        // Give the option to unfollow someone
        else if (presenter.getPerson() != null && !presenter.getPerson().isUser()
                && Model.getSINGLETON().getCurrentUser().isFollowing(presenter.getPerson().getId())) {
            actionButton.setText(R.string.unfollow);
        }

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onReadingBooksTaskComplete(List<Book> books) {
        if (books == null){
            Log.d("DEBUG LISTENERS", "onReadingBooksTaskComplete: list of books was null");
            return;
        }
        readingList = new ArrayList<Book>(books);
        readingListAdapter = new BookCardListAdapter(reviewList);
        readingListRecyclerView.setLayoutManager(readingLayoutManager);
        readingListRecyclerView.setAdapter(readingListAdapter);

    }

    @Override
    public void addReadingBook(Book book) {
        if (book == null){
            Log.d("DEBUG LISTENERS", "addReadingBook: book was null");
            return;
        }
        if (readingList == null){
            readingList = new ArrayList<Book>();
        }
        readingList.add(book);
        readingListAdapter = new BookCardListAdapter(readingList);
        readingListRecyclerView.setLayoutManager(readingLayoutManager);
        readingListRecyclerView.setAdapter(readingListAdapter);
    }

    @Override
    public void onReviewedBooksTaskComplete(List<Book> books) {
        if (books == null){
            Log.d("DEBUG LISTENERS", "onReviewedBooksTaskComplete: list of books was null");
            return;
        }
        reviewList = new ArrayList<Book>(books);
        reviewedListAdapter = new BookCardListAdapter(reviewList);
        reviewedListRecyclerView.setLayoutManager(reviewedLayoutManager);
        reviewedListRecyclerView.setAdapter(reviewedListAdapter);
    }

    @Override
    public void addReviewedBook(Book book) {
        if (book == null){
            Log.d("DEBUG LISTENERS", "addReadingBook: book was null");
            return;
        }
        if (reviewList == null){
            reviewList = new ArrayList<Book>();
        }
        reviewList.add(book);
        reviewedListAdapter = new BookCardListAdapter(reviewList);
        reviewedListRecyclerView.setLayoutManager(reviewedLayoutManager);
        reviewedListRecyclerView.setAdapter(reviewedListAdapter);
    }
}
