package com.androidtraining.Threads;

import android.content.Context;
import android.os.AsyncTask;

import com.androidtraining.Connect.APIClient;
import com.androidtraining.Models.Images;
import com.androidtraining.Models.Shots;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShotsAsyncTask extends AsyncTask<GetRequest, Void, String> {
    private ILoaderProgressListener iLoaderProgressListener;
    private List<Shots> lstShots;

    public ShotsAsyncTask(ILoaderProgressListener iLoaderProgressListener) {
        this.iLoaderProgressListener = iLoaderProgressListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(GetRequest... getRequests) {
        GetRequest getRequest = getRequests[0];
        String response = APIClient.getShotsRequest(getRequest.getUrl(), getRequest.getPage());
        return response;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        lstShots = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject shot = new JSONObject(array.get(i).toString());
                String title = shot.getString("title");
                String publishedAt = shot.getString("published_at");
                String updatedAt = shot.getString("updated_at");
                JSONObject images = shot.getJSONObject("images");
                String hidpi = images.getString("hidpi");
                String normal = images.getString("normal");
                String oneX = images.getString("one_x");
                String twoX = images.getString("two_x");
                String teaser = images.getString("teaser");
                boolean isLiked = false;
                Images ivBackground = new Images(hidpi, normal, oneX, twoX, teaser);
                lstShots.add(new Shots(ivBackground, title, publishedAt, updatedAt, isLiked));
            }
            iLoaderProgressListener.publishShotsProgress(lstShots);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
