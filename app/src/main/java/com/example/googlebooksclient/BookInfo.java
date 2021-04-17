package com.example.googlebooksclient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BookInfo {

    private String title;
    private String authors;
    private URL infoLink;

    public BookInfo(String t, String a, URL iL){
        title = t;
        authors = a;
        infoLink = iL;
    }

    static List<BookInfo> fromJsonResponse(String s) throws JSONException, MalformedURLException {
        JSONObject json = new JSONObject(s);
        JSONArray jsonArray = json.getJSONArray("items");
        List<BookInfo> lista = new ArrayList<BookInfo>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject js = jsonArray.getJSONObject(i);
            String k = js.getString("kind");
            String l = js.getString("link");
            JSONObject vInfo = js.getJSONObject("volumeInfo");
            String t = vInfo.getString("title");
            JSONArray authorsJSON = vInfo.getJSONArray("authors");
            String author = "";
            for (int j = 0; j < authorsJSON.length(); j++) {
                author.concat(authorsJSON.getString(0));
            }

            URL url = new URL(l);
            lista.add(new BookInfo(t, author, url));
        }

        return lista;
    }

    public String getTitle(){
        return ;
    }
}
