package com.cs428.app.bookapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.Person;
import com.cs428.app.bookapp.model.Person;

import java.util.List;


//TODO: make person cards
/**
 * Created by lily on 4/9/2018.
 */
// http://android.xsoftlab.net/training/tv/playback/card.html
// https://www.learnhowtoprogram.com/android/web-service-backends-and-custom-fragments/custom-adapters-with-recyclerview
public class PersonCardListAdapter extends RecyclerView.Adapter<PersonCardListAdapter.CardViewHolder> {
    private List<Person> person_ids;
    private ViewGroup parent;

    public PersonCardListAdapter(List<Person> personResults) {
        this.person_ids = personResults;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View bookCardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
        return new CardViewHolder(bookCardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Person person = person_ids.get(position);
        holder.personName.setText(person.getName());

        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(parent.getContext(), "Follow clicked", Toast.LENGTH_SHORT).show();
                // TODO: disable follow button and follow person in model

            }
        });

    }

    @Override
    public int getItemCount() {
        if (person_ids != null)
            return person_ids.size();
        else
            return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView personName;
        public Button followButton;

        public CardViewHolder(View v) {
            super(v);

            personName = (TextView) v.findViewById(R.id.user_card_title);
            followButton = (Button) v.findViewById(R.id.follow_button);

        }
    }



}
