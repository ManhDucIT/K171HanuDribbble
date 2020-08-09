package com.androidtraining.Threads;

import android.os.AsyncTask;

import com.androidtraining.Connect.APIClient;
import com.androidtraining.Models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserAsyncTask extends AsyncTask<GetRequest, Void, String> {
    private ILoaderProgressListener iLoaderProgressListener;
    private List<User> userList;

    public UserAsyncTask(ILoaderProgressListener iLoaderProgressListener) {
        this.iLoaderProgressListener = iLoaderProgressListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(GetRequest... getRequests) {
        GetRequest request = getRequests[0];
        String response = APIClient.getUserRequest(request.getUrl());
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        userList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(s);
            String avatar = object.getString("avatar_url");
            int id = object.getInt("id");
            String name = object.getString("name");
            User user = new User(avatar, id, name);
            userList.add(user);
            iLoaderProgressListener.publishUserProgress(userList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
