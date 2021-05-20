package com.example.gymsy.connection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostData implements Runnable {
    private volatile String value;
    private String postData;
    private String url;

    public PostData(String postData, String url){
        this.postData = postData;
        this.url = url;
    }

    @Override
    public void run() {
        try  {

            URL url = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(this.postData.getBytes("UTF-8"));
            InputStream response = con.getInputStream();
            String jsonReply = convertStreamToString(response);
            this.value = jsonReply;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(){
        return this.value;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String postData() {

        try {
            Thread thread = new Thread(this);
            thread.start();
            thread.join();
            String jsonResult = this.getValue();
            Log.e("test", jsonResult );
            return jsonResult;
        }
        catch(Exception e){
            e.printStackTrace();
            String fail = "{\"msg\":\"Username Exists\"}";
            return fail;
        }
    }
}
