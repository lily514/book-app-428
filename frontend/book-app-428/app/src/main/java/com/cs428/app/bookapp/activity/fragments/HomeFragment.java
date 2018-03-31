package com.cs428.app.bookapp.activity.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.interfaces.IHomePresenter;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2018.
 */

public class HomeFragment extends Fragment {
    public RecyclerView recommendedList;
    public ImageButton profileNavButton, homeNavButton, booksNavButton;
    private IHomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Serializable presenter) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESENTER", presenter);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (IHomePresenter) getArguments().getSerializable(
                "PRESENTER");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        recommendedList = (RecyclerView) rootView.findViewById(R.id.recommended_list);
        profileNavButton = (ImageButton) rootView.findViewById(R.id.profile_nav_button);
        homeNavButton = (ImageButton) rootView.findViewById(R.id.home_nav_button);
        booksNavButton = (ImageButton) rootView.findViewById(R.id.books_nav_button);

        BookCardListAdapter adapter = new BookCardListAdapter(presenter.getHomePageBooks());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recommendedList.setLayoutManager(layoutManager);
        recommendedList.setAdapter(adapter);

        profileNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity parentActivity = (MainActivity) getActivity();
                parentActivity.doProfileNavButtonAction();
            }
        });

        homeNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Do nothing
            }
        });

        booksNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // TODO: navigate to books reading list (?)
            }
        });

        Toolbar myToolbar = (Toolbar) rootView.findViewById(R.id.banner);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        setHasOptionsMenu(true);

        return rootView;
    }
}
