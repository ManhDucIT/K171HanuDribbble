package com.androidtraining.k171hanudribbble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IShotInterfaceClass, IEventListener{
    ILoadmore loadmore;
    boolean isLoading;
    List<Shot> listData;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    RecyclerView recyclerView;
    TextView tv_title;
    TextView tv_text;

    TextView tv_like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        tv_title = findViewById(R.id.tv_title);
        tv_text = findViewById(R.id.tv_text);
        layoutManager =  new LinearLayoutManager(this);
        if(isNetworkAvailable()){
            getUserRequest request = new getUserRequest();
            request.setRequest("https://api.dribbble.com/v2/user/shots");
            request.setPage(1);
            new NetWorkAsynTask(this, this).execute(request);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == listData.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });

    }


    private boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    public void loadMore(){

            listData.add(null);
        listData.size();
            adapter.notifyItemInserted(listData.size() - 1);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    listData.remove(listData.size() - 1);
                    listData.size();
                    int scrollPosition = listData.size()-3;
                    adapter.notifyItemRemoved(scrollPosition);
                    int currentSize = scrollPosition;
                    int nextLimit = currentSize+2;
                    while (currentSize - 1 < nextLimit) {
                        listData.add(new Shot("Furniture Mobile APP UX/UI", "Joharwarwm paperpillar", "https://static.dribbble.com/users/5735828/screenshots/13975284/design.png", false));
                        currentSize++;
                    }
                    System.out.println(currentSize);
                    adapter.notifyDataSetChanged();
                    isLoading = false;
                }
            }, 3000);

    }
    @Override
    public void onClickIcon(ImageView iv_heart, int position) {
       Shot shot = listData.get(position);
        boolean status = shot.isLiked;
        double number_of_like = shot.getNumber_of_like();
        if(!status){
            number_of_like= number_of_like +1;
        }else{
            number_of_like = number_of_like -1;
        }

        shot.setLiked(!status);
        shot.setNumber_of_like(number_of_like);

        adapter.notifyItemChanged(position);
    }

    @Override
    public void loadListShot(List<Shot> listData) {
        String a="a";
        recyclerView.setLayoutManager(layoutManager);
        this.listData = listData;
        adapter= new Adapter(this, listData, this);
        recyclerView.setAdapter(adapter);
    }
}
