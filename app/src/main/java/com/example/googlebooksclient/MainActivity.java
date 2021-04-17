package com.example.googlebooksclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText bookAutorEdit;
    private EditText bookTitleEdit;
    private RadioGroup group;
    private TextView bookSearch;
    private RecyclerView recyclerView;
    private BookLoaderCallbacks bookLoaderCallbacks = new BookLoaderCallbacks(this);
    BooksResultListAdapter booksResultListAdapter;

    private static final int BOOK_LOADER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookSearch = findViewById(R.id.bookSearch);
        bookAutorEdit = findViewById(R.id.bookAuthor);
        bookTitleEdit = findViewById(R.id.bookTitle);
        recyclerView = findViewById(R.id.recyclerView);
        group = findViewById(R.id.radioGroup);

        this.booksResultListAdapter = new BooksResultListAdapter(this,new ArrayList<BookInfo>());
        recyclerView.setAdapter(booksResultListAdapter);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if(loaderManager.getLoader(BOOK_LOADER_ID) != null){
            loaderManager.initLoader(BOOK_LOADER_ID,null, bookLoaderCallbacks);
        }
    }

    public void searchBooks (View view){
        String queryString = bookAutorEdit.getText().toString() + " " + bookTitleEdit.getText().toString();
        int pT = group.getCheckedRadioButtonId();
        String printType = String.valueOf(pT);

        Bundle queryBundle = new Bundle();
        queryBundle.putString(BookLoaderCallbacks.EXTRA_QUERY, queryString);
        queryBundle.putString(BookLoaderCallbacks.EXTRA_PRINT_TYPE, printType);
        LoaderManager.getInstance(this).restartLoader(BOOK_LOADER_ID, queryBundle, bookLoaderCallbacks);
    }

    public void updateBooksResultList(List<BookInfo> bookInfos){
        booksResultListAdapter.setBooksData(bookInfos);
        booksResultListAdapter.notifyDataSetChanged();
    }
}