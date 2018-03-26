package com.cs428.app.bookapp.activity.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.interfaces.ISearchPresenter;
import com.cs428.app.bookapp.model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by emilyprigmore on 3/17/18.
 */

public class SearchFragment extends Fragment {

    private ISearchPresenter presenter;
    private List<Book> searchedBooks;

    private EditText searchEditText;
    private String searchString;
    private Button searchButton;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView searchedBooksRecyclerView;
    private BookCardListAdapter bookCardListAdapter;

    public SearchFragment newInstance(Serializable presenter) {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);

        layoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL,
                false);

        searchEditText = (EditText) rootView.findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchString = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        searchedBooksRecyclerView = (RecyclerView) rootView.findViewById(R.id.book_reviews);

        searchButton = (Button) rootView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchedBooks = presenter.searchBooks(searchString);
                updateSearchedBooks();
            }
        });

        return rootView;
    }

    private void updateSearchedBooks() {
        bookCardListAdapter = new BookCardListAdapter(searchedBooks);
        searchedBooksRecyclerView.setLayoutManager(layoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);
    }
}
