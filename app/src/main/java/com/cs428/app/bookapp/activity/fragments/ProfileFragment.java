package com.cs428.app.bookapp.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.Book;
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.Person;

import java.util.List;

/**
 * Created by Trevor on 2/10/2018.
 */

public class ProfileFragment extends Fragment {
    private RecyclerView readingList;
    private RecyclerView reviewedList;
    private RecyclerView.Adapter readingListAdapter;
    private RecyclerView.Adapter reviewedListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button actionButton;
    private Person person = null;

    // Necessary empty constructor
    public ProfileFragment() {}

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_page_layout, container, false);
        layoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);

        readingList = (RecyclerView) v.findViewById(R.id.reading_list);
        reviewedList = (RecyclerView) v.findViewById(R.id.reviewed_list);

        readingListAdapter = new BookListAdapter(person.getReadingList());
        reviewedListAdapter = new BookListAdapter(person.getReviewedBooks());

        readingList.setLayoutManager(layoutManager);
        readingList.setAdapter(readingListAdapter);

        reviewedList.setLayoutManager(layoutManager);
        reviewedList.setAdapter(reviewedListAdapter);

        actionButton = (Button) v.findViewById(R.id.person_action);

        // You can't follow yourself, silly!
        if (person != null && person.isUser()) {
            actionButton.setVisibility(View.INVISIBLE);
        }

        // Give the option to unfollow someone
        else if (person != null && !person.isUser()
                && Model.SINGLETON.currentUser.isFollowing(person.getId())) {
            actionButton.setText(R.string.unfollow);
        }

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
        private List<Book> books;

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public ImageView imageView;
            public ViewHolder(ImageView v) {
                super(v);
                imageView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public BookListAdapter(List<Book> books) {
            this.books = books;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public BookListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            ImageView v = (ImageView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_cover_view, parent, false);

            return new ViewHolder(v);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from dataset at this position
            // - replace the contents of the view with that element
            holder.imageView.setImageBitmap(books.get(position).getCover());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return books.size();
        }
    }
}
