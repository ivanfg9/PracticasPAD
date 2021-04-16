package com.example.googlebooksclient;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NetworkClass {
    private static final String LOG_TAG = NetworkClass.class.getSimpleName();

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARAM = "q";
    private static final String MAX_RESULTS = "maxResults";
    private static final String PRINT_TYPE = "printType";

    public static List<BookInfo> getBookInfo(String queryString, String printType, int maxResult) throws MalformedURLException, JSONException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            Uri builtUri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, Integer.toString(maxResult)).appendQueryParameter(PRINT_TYPE, printType)
                    .build();

            URL requestUrl = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setRequestMethod("GET");

            InputStream input = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            if(input == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = reader.readLine())!= null){
                builder.append(line + "\n");
            }

            if(builder.length() == 0){
                return null;
            }
            bookJSONString = builder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }

            if(reader != null){
                try {
                    reader.close();
                } catch (IOException io){
                    io.printStackTrace();
                }
            }
            Log.d(LOG_TAG,bookJSONString);
            return BookInfo.fromJsonResponse(bookJSONString);
        }
    }
}
