package com.example.googlebooksclient;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {

    private String queryString;
    private String printType;
    private static final int MAX_RESULT = 40;

    public BookLoader(@NonNull Context context,String qs, String pt) {
        super(context);
        queryString = qs;
        printType = pt;
    }

    @Nullable
    @Override
    public List<BookInfo> loadInBackground() {
        try {
            return NetworkClass.getBookInfo(queryString, printType, MAX_RESULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
