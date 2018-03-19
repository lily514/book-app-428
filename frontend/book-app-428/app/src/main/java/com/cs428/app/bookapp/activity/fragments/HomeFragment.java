package com.cs428.app.bookapp.activity.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.model.Model;

/**
 * Created by Trevor on 2/10/2018.
 */

public class HomeFragment extends Fragment {
    public RecyclerView recommendedList;
    public ImageButton friendsNavButton, homeNavButton, booksNavButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        recommendedList = (RecyclerView) rootView.findViewById(R.id.recommended_list);
        friendsNavButton = (ImageButton) rootView.findViewById(R.id.friends_nav_button);
        homeNavButton = (ImageButton) rootView.findViewById(R.id.home_nav_button);
        booksNavButton = (ImageButton) rootView.findViewById(R.id.books_nav_button);

        BookCardListAdapter adapter = new BookCardListAdapter(Model.getSINGLETON()
                .getCurrentUser().getRecommendedBooks()); // TODO: make method in user class
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recommendedList.setLayoutManager(layoutManager);
        recommendedList.setAdapter(adapter);

        friendsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: navigate to user's friends list
            }
        });

        homeNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: navigate to home fragment (this)
                // not sure if we need to define each of these button actions in each fragment or what...
            }
        });

        booksNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: navigate to books reading list (?)
            }
        });

        return rootView;
    }
}
