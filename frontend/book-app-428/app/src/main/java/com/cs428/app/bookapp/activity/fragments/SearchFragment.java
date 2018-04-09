package com.cs428.app.bookapp.activity.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilyprigmore on 3/17/18.
 */

public class SearchFragment extends Fragment implements OnSearchTaskComplete {

    private ISearchPresenter presenter;

    private RecyclerView.LayoutManager booksLayoutManager;
    private RecyclerView searchedBooksRecyclerView;
    private BookCardListAdapter bookCardListAdapter;

    private RecyclerView.LayoutManager userLayoutManager;
    private RecyclerView searchedUsersRecyclerView;
    private UserCardListAdapter userCardListAdapter;

    private List<Book> bookList;
    private List<User> userList;



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

        bookCardListAdapter = new BookCardListAdapter(bookList);
        userCardListAdapter = new UserCardListAdapter(userList);

        searchedUsersRecyclerView.setLayoutManager(userLayoutManager);
        searchedUsersRecyclerView.setAdapter(userCardListAdapter);

        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

        bookList = new ArrayList<Book>();
        userList = new ArrayList<User>();

        return rootView;
    }

    @Override
    public void onSearchTaskComplete(List<Book> book_results, List<User> user_results) {
        bookList = book_results;
        userList = user_results;

        bookCardListAdapter = new BookCardListAdapter(book_results);
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

        userCardListAdapter = new UserCardListAdapter(user_results);
        searchedUsersRecyclerView.setLayoutManager(userLayoutManager);
        searchedUsersRecyclerView.setAdapter(userCardListAdapter);
    }

    @Override
    public void addBook(Book book) {
        bookList.add(book);
        bookCardListAdapter = new BookCardListAdapter(bookList);
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);
    }

    @Override
    public void addUser(User user) {
        userList.add(user);

        userCardListAdapter = new UserCardListAdapter(userList);
        searchedUsersRecyclerView.setLayoutManager(userLayoutManager);
        searchedUsersRecyclerView.setAdapter(userCardListAdapter);
    }

    @Override
    public void addBooks(List<Book> books) {
        if (books == null){
            Log.d("DEBUG LISTENERS", "addBooks: list of books was null");
            return;
        }
        if (bookList != null){
            bookList.addAll(books);
        }
        else {
            bookList = new ArrayList<Book>();
            bookList.addAll(books);
        }
        bookCardListAdapter = new BookCardListAdapter(bookList);
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

    }
}
