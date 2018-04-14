package com.cs428.app.bookapp.activity.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.adapter.PersonCardListAdapter;
import com.cs428.app.bookapp.interfaces.ISearchPresenter;
import com.cs428.app.bookapp.interfaces.OnBitmapComplete;
import com.cs428.app.bookapp.interfaces.OnSearchTaskComplete;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilyprigmore on 3/17/18.
 */

public class SearchFragment extends Fragment implements OnSearchTaskComplete, OnBitmapComplete {

    private ISearchPresenter presenter;

    private RecyclerView.LayoutManager booksLayoutManager;
    private RecyclerView searchedBooksRecyclerView;
    private BookCardListAdapter bookCardListAdapter;

    private RecyclerView.LayoutManager personLayoutManager;
    private RecyclerView searchedPersonsRecyclerView;
    private PersonCardListAdapter personCardListAdapter;

    private List<Book> bookList;
    private List<Person> personList;



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
        personLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL,
                false);

        searchedBooksRecyclerView = (RecyclerView) rootView.findViewById(R.id.book_search_results);
        searchedPersonsRecyclerView = (RecyclerView) rootView.findViewById(R.id.user_search_results);

        bookCardListAdapter = new BookCardListAdapter(bookList, this);
        personCardListAdapter = new PersonCardListAdapter(personList, this);

        searchedPersonsRecyclerView.setLayoutManager(personLayoutManager);
        searchedPersonsRecyclerView.setAdapter(personCardListAdapter);

        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

        bookList = new ArrayList<Book>();
        personList = new ArrayList<Person>();

        return rootView;
    }

    @Override
    public void onSearchTaskComplete(List<Book> book_results, List<Person> person_results) {
        bookList = book_results;
        personList = person_results;

        bookCardListAdapter = new BookCardListAdapter(book_results, this);
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

        personCardListAdapter = new PersonCardListAdapter(person_results, this);
        searchedPersonsRecyclerView.setLayoutManager(personLayoutManager);
        searchedPersonsRecyclerView.setAdapter(personCardListAdapter);
    }

    @Override
    public void addBook(Book book) {
        if (book == null){
            Log.d("DEBUG LISTENERS", "addBook: book was null");
            return;
        }
        bookList.add(book);
        bookCardListAdapter = new BookCardListAdapter(bookList, this);
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);
    }

    @Override
    public void addPerson(Person person) {
        if (person == null){
            Log.d("DEBUG LISTENERS", "addPersons: person was null");
            return;
        }
        personList.add(person);
        personCardListAdapter = new PersonCardListAdapter(personList, this);
        searchedPersonsRecyclerView.setLayoutManager(personLayoutManager);
        searchedPersonsRecyclerView.setAdapter(personCardListAdapter);
    }

    @Override
    public void addBooks(List<Book> books) {
        if (books == null){
            Log.d("DEBUG LISTENERS", "addBooks: list of books was null");
            return;
        }
        if (bookList == null){
            bookList = new ArrayList<Book>();
        }
        bookList.addAll(books);
        bookCardListAdapter = new BookCardListAdapter(bookList, this);
        searchedBooksRecyclerView.setLayoutManager(booksLayoutManager);
        searchedBooksRecyclerView.setAdapter(bookCardListAdapter);

    }

    @Override
    public void updateImages() {
        bookCardListAdapter.notifyDataSetChanged();
    }
}
