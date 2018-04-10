package com.cs428.app.bookapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.BookReview;

import java.util.List;

/**
 * Created by chees on 3/31/2018.
 */

public class BookReviewsListAdapter extends RecyclerView.Adapter<BookReviewsListAdapter.ReviewHolder> {
    private List<BookReview> reviews;

    public BookReviewsListAdapter(List<BookReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View reviewView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_review_layout, parent, false);
        return new ReviewHolder(reviewView);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.setReviewInfo(reviews.get(position));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {
        private TextView review;
        private TextView reviewer;
        private TextView rating;

        public ReviewHolder(View v) {
            super(v);
            reviewer = (TextView) v.findViewById(R.id.reviewer);
            review = (TextView) v.findViewById(R.id.review);
            rating = (TextView) v.findViewById(R.id.rating);
        }

        public void setReviewInfo(BookReview reviewInfo) {
            String reviewerName = reviewInfo.getReviewer();
            String reviewerRating = reviewInfo.getRating();
            reviewer.setText(reviewerName == null ? "Anonymous" : reviewerName);
            rating.setText(reviewerRating == null ? "N/A" : reviewerRating);
            review.setText(reviewInfo.getReview());
        }
    }
}
