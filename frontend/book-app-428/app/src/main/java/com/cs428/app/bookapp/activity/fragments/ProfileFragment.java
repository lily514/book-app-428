package com.cs428.app.bookapp.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.interfaces.IProfilePresenter;
import com.cs428.app.bookapp.interfaces.OnReadingBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.OnReviewedBooksTaskComplete;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;

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
    private IProfilePresenter presenter;

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

    public void setPerson(Person person){
           presenter.setPerson(person);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (IProfilePresenter) getArguments().getSerializable(
                "PRESENTER");

        reviewList = new ArrayList<Book> ();
        readingList = new ArrayList<Book>();
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
        readingList = books;
        readingListAdapter = new BookCardListAdapter(reviewList);
        readingListRecyclerView.setLayoutManager(readingLayoutManager);
        readingListRecyclerView.setAdapter(readingListAdapter);

    }

    @Override
    public void addReadingBook(Book book) {
        readingList.add(book);
        readingListAdapter = new BookCardListAdapter(readingList);
        readingListRecyclerView.setLayoutManager(readingLayoutManager);
        readingListRecyclerView.setAdapter(readingListAdapter);
    }

    @Override
    public void onReviewedBooksTaskComplete(List<Book> books) {
        reviewList = books;
        reviewedListAdapter = new BookCardListAdapter(reviewList);
        reviewedListRecyclerView.setLayoutManager(reviewedLayoutManager);
        reviewedListRecyclerView.setAdapter(reviewedListAdapter);
    }

    @Override
    public void addReviewedBook(Book book) {
        reviewList.add(book);
        reviewedListAdapter = new BookCardListAdapter(reviewList);
        reviewedListRecyclerView.setLayoutManager(reviewedLayoutManager);
        reviewedListRecyclerView.setAdapter(reviewedListAdapter);
    }
}
