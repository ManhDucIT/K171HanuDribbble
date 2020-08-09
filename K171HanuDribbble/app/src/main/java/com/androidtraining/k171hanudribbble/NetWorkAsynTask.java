package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NetWorkAsynTask extends AsyncTask<getUserRequest, Void, String> {
    IShotInterfaceClass iShotInterfaceClass;

    public NetWorkAsynTask(@NonNull Context context, IShotInterfaceClass iShotInterfaceClass){
        this.iShotInterfaceClass = iShotInterfaceClass;
    }
    public void setLoaderProgressListener(IShotInterfaceClass iShotInterfaceClass){
        this.iShotInterfaceClass = iShotInterfaceClass;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(getUserRequest... getUserRequests) {
        getUserRequest request = getUserRequests[0];
        String response = APIClient.getUserRequest(request.getRequest());
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<Shot> listData = new ArrayList<Shot>();
        try {

            JSONArray results = new JSONArray(s);
            for(int i= 0; i< results.length();i++){
                JSONObject shot = results.getJSONObject(i);
                String title = shot.getString("title");
                String description = shot.getString("description");
                JSONObject image = shot.getJSONObject("images");
                String hidpi = image.getString("hidpi");
                boolean isLiked = false;
                listData.add(new Shot(title, description, hidpi, isLiked));
            }
            iShotInterfaceClass.loadListShot(listData);

        } catch ( JSONException e){
            e.printStackTrace();
        }
    }
}
