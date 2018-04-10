package com.cs428.app.bookapp.activity.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.interfaces.OnHomeBooksTaskComplete;
import com.cs428.app.bookapp.interfaces.Serializable;
import com.cs428.app.bookapp.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trevor on 2/10/2018.
 */

public class HomeFragment extends Fragment implements OnHomeBooksTaskComplete {
    public RecyclerView recommendedList;
    private Serializable presenter;
    private BookCardListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Book> homePageBooks;


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
        presenter = (Serializable) getArguments().getSerializable(
                "PRESENTER");

        homePageBooks = new ArrayList<Book>();
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity)getActivity()).setBannerTitle("Book App");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recommendedList = (RecyclerView) rootView.findViewById(R.id.recommended_list);

        adapter = new BookCardListAdapter(homePageBooks);
        layoutManager = new LinearLayoutManager(getContext());
        recommendedList.setLayoutManager(layoutManager);
        recommendedList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onHomeBooksTaskComplete(List<Book> books) {
        if (books == null){
                Log.d("DEBUG LISTENERS", "onHomeBooksTaskComplete: list of books was null");
                return;
        }
        homePageBooks = new ArrayList<>(books);
        adapter = new BookCardListAdapter(books);
        recommendedList.setLayoutManager(layoutManager);
        recommendedList.setAdapter(adapter);
    }

    @Override
    public void addHomeBook(Book book) {
        if (book == null){
            Log.d("DEBUG LISTENERS", "addHomeBook: book was null");
            return;
        }
        if (homePageBooks == null){
            homePageBooks = new ArrayList<Book>();
        }
        homePageBooks.add(book);
        adapter = new BookCardListAdapter(homePageBooks);
        recommendedList.setLayoutManager(layoutManager);
        recommendedList.setAdapter(adapter);
    }
}
