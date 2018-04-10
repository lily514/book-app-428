package com.cs428.app.bookapp.activity.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.adapter.UserCardListAdapter;
import com.cs428.app.bookapp.interfaces.ISearchPresenter;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by emilyprigmore on 3/17/18.
 */

public class SearchFragment extends Fragment {

    private ISearchPresenter presenter;

    private RecyclerView.LayoutManager booksLayoutManager;
    private RecyclerView searchedBooksRecyclerView;
    private BookCardListAdapter bookCardListAdapter;

    private RecyclerView.LayoutManager userLayoutManager;
    private RecyclerView searchedUsersRecyclerView;
    private UserCardListAdapter userCardListAdapter;

    public static SearchFragment newInstance(Serializable presenter) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESENTER", presenter);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = (ISearchPresenter) getArguments().getSerializable(
                "PRESENTER");
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity)getActivity()).setBannerTitle("Search Results");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        booksLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL,
                false);
        userLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL,
                false);

        searchedBooksRecyclerView = (RecyclerView) rootView.findViewById(R.id.book_search_results);
        searchedUsersRecyclerView = (RecyclerView) rootView.findViewById(R.id.user_search_results);

        bookCardListAdapter = new BookCardListAdapter(presenter.getBookSearchResults());
        userCardListAdapter = new UserCardListAdapter(presenter.getUserSearchResults());

        searchedUsersRecyclerView.setLayoutManager(userLayoutManager);
        searchedUsersRecyclerView.setAdapter(userCardListAdapter);

        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

        return rootView;
    }

    private void updateSearchedBooks() {
        bookCardListAdapter = new BookCardListAdapter(presenter.getBookSearchResults());
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

        userCardListAdapter = new UserCardListAdapter(presenter.getUserSearchResults());
        searchedUsersRecyclerView.setLayoutManager(userLayoutManager);
        searchedUsersRecyclerView.setAdapter(userCardListAdapter);
    }
}
