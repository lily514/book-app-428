package com.cs428.app.bookapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs428.app.bookapp.R;

import java.util.List;

/**
 * Created by emilyprigmore on 3/11/18.
 */

public class BookSummaryListAdapter extends RecyclerView.Adapter<BookSummaryListAdapter.BookSummaryViewHolder>{

    private List<String> summaries;
    private ViewGroup parent;

    public BookSummaryListAdapter(List<String> summaries) {
        this.summaries = summaries;
    }

    @Override
    public BookSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View bookSummaryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_summary_view, parent, false);
        return new BookSummaryViewHolder(bookSummaryView);
    }

    @Override
    public void onBindViewHolder(BookSummaryViewHolder holder, int position) {
        String bookSummary = summaries.get(position);
        holder.summary.setText(bookSummary);
    }

    @Override
    public int getItemCount() {
        if (summaries != null)
            return summaries.size();
        else
            return 0;
    }


    public class BookSummaryViewHolder extends RecyclerView.ViewHolder {
        public TextView summary;

        public BookSummaryViewHolder(View v) {
            super(v);
            summary = (TextView) v.findViewById(R.id.book_summary);
        }
    }
}
