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

        public ViewHolder(@NonNull View itemView, BooksResultListAdapter booksResultListAdapter) {
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            author = itemView.findViewById(R.id.authorText);
            url = itemView.findViewById(R.id.urlText);
            this.booksResultListAdapter = booksResultListAdapter;
        }
    }

    public BooksResultListAdapter(Context context, ArrayList<BookInfo> bI){
        inflater = LayoutInflater.from(context);
        mBooksData = bI;
    }

    @Override
    public BooksResultListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksResultListAdapter.ViewHolder holder, int position) {
        BookInfo mCurrent = mBooksData.get(position);
        holder.title.setText(mCurrent.toString());
    }

    @Override
    public int getItemCount() {
        return mBooksData.size();
    }


    public void setBooksData(List<BookInfo> data){
        mBooksData = new ArrayList<>(data);
    }
}
