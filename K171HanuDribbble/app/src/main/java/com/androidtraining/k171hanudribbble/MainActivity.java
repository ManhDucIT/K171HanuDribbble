package com.androidtraining.k171hanudribbble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IEventListener {
    ILoadmore loadmore;
    boolean isLoading;
    List<Data> lstData;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    RecyclerView recyclerView;
    TextView tv_username;
    TextView tv_text;

    TextView tv_like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstData = new ArrayList<Data>();
        Data data1 = new Data("Furniture Mobile APP UX/UI", "Joharwarwm paperpillar", R.drawable.image1, R.drawable.ui_ux,false,134, 1234, 12);
        Data data2 = new Data("Coin Wallet - Night Theme", "Nadya Fedrunova for Fireart",R.drawable.life, R.drawable.coin_wallet, true, 23456,234, 23);
        Data data3 = new Data("Affinity Designer (Big Sur Icon)", "Ariunbold Ankhaa for awsmd", R.drawable.cat, R.drawable.afinity, false, 667,78,67);
        Data data4 = new Data( "Analytics Landing Page", "Afterglow", R.drawable.avtuser, R.drawable.analytic, true,12345,  1145, 78);
        lstData.add(data1);
        lstData.add(data2);
        lstData.add(data3);
        recyclerView = findViewById(R.id.rv);
        tv_username = findViewById(R.id.tv_username);
        tv_text = findViewById(R.id.tv_text);
        layoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new Adapter(this, lstData, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == lstData.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    public void loadMore(){

            lstData.add(null);
        lstData.size();
            adapter.notifyItemInserted(lstData.size() - 1);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    lstData.remove(lstData.size() - 1);
                    lstData.size();
                    int scrollPosition = lstData.size()-3;
                    adapter.notifyItemRemoved(scrollPosition);
                    int currentSize = scrollPosition;
                    int nextLimit = currentSize+2;
                    while (currentSize - 1 < nextLimit) {
                        lstData.add(new Data("Furniture Mobile APP UX/UI", "Joharwarwm paperpillar", R.drawable.image1, R.drawable.ui_ux,false, 567, 1234, 12));
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
        Data data = lstData.get(position);
        boolean status = data.isLiked;
        double number_of_like = data.getNumber_of_like();
        if(!status){
            number_of_like= number_of_like +1;
        }else{
            number_of_like = number_of_like -1;
        }

        data.setLiked(!status);
        data.setNumber_of_like(number_of_like);

//        adapter.notifyItemChanged(position);
    }
}
