package com.cs428.app.bookapp.activity.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.activity.MainActivity;
import com.cs428.app.bookapp.adapter.BookCardListAdapter;
import com.cs428.app.bookapp.interfaces.Serializable;

/**
 * Created by Trevor on 2/10/2018.
 */

public class HomeFragment extends Fragment {
    public RecyclerView recommendedList;
    private Serializable presenter;

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
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MainActivity)getActivity()).setBannerTitle("Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        recommendedList = (RecyclerView) rootView.findViewById(R.id.recommended_list);

        BookCardListAdapter adapter = new BookCardListAdapter(presenter.getHomePageBooks());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recommendedList.setLayoutManager(layoutManager);
        recommendedList.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) rootView.findViewById(R.id.banner);
        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        setHasOptionsMenu(true);

        SearchView searchView = (SearchView) rootView.findViewById(R.id.search_text_view);
        setSearchViewListener(searchView);

        return rootView;
    }


    public void setSearchViewListener(SearchView searchView) {
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearchButtonAction();
            }
        });
    }

    public void doSearchButtonAction() {
        Fragment searchFragment = SearchFragment.newInstance(presenter);
        ((MainActivity)getActivity()).transitionFragment(searchFragment, "Home");
    }


}
