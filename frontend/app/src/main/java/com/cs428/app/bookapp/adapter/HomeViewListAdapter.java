package com.cs428.app.bookapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chees on 2/26/2018.
 */
// http://android.xsoftlab.net/training/tv/playback/card.html
// https://www.learnhowtoprogram.com/android/web-service-backends-and-custom-fragments/custom-adapters-with-recyclerview
public class HomeViewListAdapter extends RecyclerView.Adapter<HomeViewListAdapter.CardViewHolder> {
    private ArrayList<Book> recommendedBooks;
    private Context context;

    public HomeViewListAdapter(Context context, ArrayList<Book> recommendedBooks) {
        this.context = context;
        this.recommendedBooks = recommendedBooks;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public CardViewHolder(View v) {
            super(v);
        }
    }

}
