package com.cs428.app.bookapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cs428.app.bookapp.R;
import com.cs428.app.bookapp.model.Book;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Lily on 3/16/18.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {
    private List<String> books;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public ViewHolder(ImageView v) {
            super(v);
            imageView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BookListAdapter(List<String> books) {
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

        //TODO: make sure this works, getCover now returns a url so this was changed to adapt to that
        //TODO: changing books to booksIds is going to change how we getCover
//        String url = books.get(position).getCover();
//        Bitmap bitmap = null;
//        try { bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
//        } catch (IOException e) {e.printStackTrace();}
//        holder.imageView.setImageBitmap(bitmap);

        //original code:
        //holder.imageView.setImageBitmap(books.get(position).getCover());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return books.size();
    }
}