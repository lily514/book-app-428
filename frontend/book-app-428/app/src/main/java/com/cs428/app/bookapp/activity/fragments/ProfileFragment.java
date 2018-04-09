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
import com.cs428.app.bookapp.model.Model;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2018.
 */

public class ProfileFragment extends Fragment {
    private RecyclerView readingList;
    private RecyclerView reviewedList;
    private RecyclerView.Adapter readingListAdapter;
    private RecyclerView.Adapter reviewedListAdapter;
    private RecyclerView.LayoutManager readingLayoutManager;
    private RecyclerView.LayoutManager reviewedLayoutManager;
    private Button actionButton;
    private IProfilePresenter presenter;

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
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity)getActivity()).setBannerTitle("Profile");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_page_layout, container, false);
        readingLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);
        reviewedLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false);

        readingList = (RecyclerView) v.findViewById(R.id.reading_list);
        reviewedList = (RecyclerView) v.findViewById(R.id.reviewed_list);

        readingListAdapter = new BookCardListAdapter(presenter.getPersonsReadingList());
        reviewedListAdapter = new BookCardListAdapter(presenter.getPersonsReadingList());

        readingList.setLayoutManager(readingLayoutManager);
        readingList.setAdapter(readingListAdapter);

        reviewedList.setLayoutManager(reviewedLayoutManager);
        reviewedList.setAdapter(reviewedListAdapter);

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


}
