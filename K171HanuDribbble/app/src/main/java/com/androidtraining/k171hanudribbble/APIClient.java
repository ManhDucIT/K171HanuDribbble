package com.androidtraining.k171hanudribbble;

import android.net.Uri;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {


    public static String getUserRequest (String url){
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        String response = null;

        try {
            // Step 1: Create Uri
            Uri builtUri = Uri.parse(url).buildUpon().build();

            // Step 2: Packed into Url
            URL requestURL = new URL(builtUri.toString());

            // Step 3: Create HttpURLConnection
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer f9b621d68d4662a43b8e5e9240035c3da5f09158df9533fa6b6e1144dd8a60b2");

            // Step 4 (OPTIONAL): Start connection
            urlConnection.connect();

            // Step 5: Get read stream
            inputStream = urlConnection.getInputStream();

            // Step 6: Convert stream to String
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            response = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;


}


}
