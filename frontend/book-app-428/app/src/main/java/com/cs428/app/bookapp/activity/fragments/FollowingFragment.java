package com.cs428.app.bookapp.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.interfaces.IHomePresenter;
import com.cs428.app.bookapp.interfaces.IProfilePresenter;
import com.cs428.app.bookapp.model.Person;

import java.util.List;

/**
 * Created by chees on 3/31/2018.
 */

public class FollowingFragment extends Fragment {
    private IHomePresenter presenter;

    public FollowingFragment(){}

    public static FollowingFragment newInstance(IHomePresenter presenter){
        FollowingFragment fragment = new FollowingFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.following_page_layout, container, false);
        
        RecyclerView followingList = (RecyclerView) v.findViewById(R.id.following_list);
        FollowingListAdapter adapter = new FollowingListAdapter(presenter.getCurrentUser().getFollowing());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        followingList.setAdapter(adapter);
        followingList.setLayoutManager(layoutManager);

        return v;
    }

    class FollowingListAdapter extends RecyclerView.Adapter<FollowingListAdapter.FollowingViewHolder> {
        private List<String> following;

        public FollowingListAdapter(List<String> following) {
            this.following = following;
        }

        @Override
        public FollowingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View followingListView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_view, parent, false);
            return new FollowingViewHolder(followingListView);
        }



        @Override
        public void onBindViewHolder(FollowingViewHolder holder, int position) {
            holder.followingPerson.setText(following.get(position));
        }

        @Override
        public int getItemCount() {
            return following.size();
        }

        class FollowingViewHolder extends RecyclerView.ViewHolder {
            public TextView followingPerson;

            public FollowingViewHolder(View v) {
                super(v);
                followingPerson = (TextView) v.findViewById(R.id.text_view_item);
            }
        }
    }
}
