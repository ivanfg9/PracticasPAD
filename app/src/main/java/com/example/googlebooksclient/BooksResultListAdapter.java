package com.example.googlebooksclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BooksResultListAdapter extends RecyclerView.Adapter<BooksResultListAdapter.ViewHolder>{

    private ArrayList<BookInfo> mBooksData;
    private LayoutInflater inflater;

    protected class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView title;
        protected TextView author;
        protected TextView url;
        private BooksResultListAdapter booksResultListAdapter;

        public ViewHolder(View itemView, BooksResultListAdapter booksResultListAdapter) {
            super(itemView);
            this.title = itemView.findViewById(R.id.titleText);
            this.author = itemView.findViewById(R.id.authorText);
            this.url = itemView.findViewById(R.id.urlText);
            this.booksResultListAdapter = booksResultListAdapter;
        }

     
    }

    public BooksResultListAdapter(Context context, ArrayList<BookInfo> bI){
        this.inflater = LayoutInflater.from(context);
        this.mBooksData = bI;
    }

    @Override
    public BooksResultListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksResultListAdapter.ViewHolder holder, int position) {
        BookInfo mCurrent = mBooksData.get(position);
        holder.title.setText(mCurrent.getTitle().toString());
        holder.author.setText(mCurrent.getAuthors().toString());
        holder.url.setText(mCurrent.getInfoLink().toString());
    }

    @Override
    public int getItemCount() {
        return mBooksData.size();
    }


    public void setBooksData(List<BookInfo> data){
        mBooksData = new ArrayList<>(data);
    }
}
