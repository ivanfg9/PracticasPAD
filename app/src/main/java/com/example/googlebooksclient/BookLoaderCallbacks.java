package com.example.googlebooksclient;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

//Comprobar que se puede hacer extends AppCompatActivity
public class BookLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<BookInfo>> {

    private EditText bookAutorEdit;
    private EditText bookTitleEdit;
    private RadioGroup group;
    private TextView bookSearch;
    private TextView bookAutorText;
    private TextView bookTitleText;
    private MainActivity mA;

    protected static final String EXTRA_QUERY = "q";
    protected static final String EXTRA_PRINT_TYPE = "printType";

    public BookLoaderCallbacks (MainActivity m){
        mA = m;
    }

    @NonNull
    @Override
    public Loader<List<BookInfo>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader(mA,args.getString("queryString"),args.getString("printType"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<BookInfo>> loader, List<BookInfo> data) {
            mA.updateBooksResultList(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<BookInfo>> loader) {

    }
}
