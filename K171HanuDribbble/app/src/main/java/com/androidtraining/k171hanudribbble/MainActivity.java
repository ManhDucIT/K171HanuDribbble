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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtraining.Adapter.ListViewAdapter;
import com.androidtraining.Listener.INoteItemListener;
import com.androidtraining.Models.Shots;
import com.androidtraining.Models.User;
import com.androidtraining.Threads.GetRequest;
import com.androidtraining.Threads.ILoaderProgressListener;
import com.androidtraining.Threads.ShotsAsyncTask;
import com.androidtraining.Threads.UserAsyncTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INoteItemListener, ILoaderProgressListener {
    private RecyclerView rvItem;
    private RecyclerView.LayoutManager rvLayoutManager;
    private ListViewAdapter adapter;
    private boolean isLoading = false;
    private List<Shots> shotsList;
    private List<User> userList;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvItem = (RecyclerView) findViewById(R.id.rvItem);
        userList = new ArrayList<>();
        shotsList = new ArrayList<>();
//        initScrollListener();
        loadData(1);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private void initScrollListener() {
        rvItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == shotsList.size() - 1) {
                        loadMore();
                        isLoading = true;

                    }
                }
            }
        });
    }

    private void loadMore() {
        shotsList.add(null);
        adapter.notifyItemInserted(shotsList.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shotsList.remove(shotsList.size() - 1);
                adapter.notifyItemRemoved(shotsList.size());

//                fetchData();
                adapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }


    private void loadData(int page) {
        if (isNetworkAvailable()) {
            GetRequest requestShots = new GetRequest();
            requestShots.setUrl("https://api.dribbble.com/v2/user/shots");
            requestShots.setPage(page);
            new ShotsAsyncTask(MainActivity.this).execute(requestShots);
            GetRequest requestUser = new GetRequest();
            requestUser.setUrl("https://api.dribbble.com/v2/user");
            new UserAsyncTask(MainActivity.this).execute(requestUser);
        } else {
            Toast.makeText(MainActivity.this, "Please connect to internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(ImageView view, TextView textView, int position) {
        Shots shots = shotsList.get(position);
        boolean status = shots.isLiked();
        shots.setLiked(!status);
        if (status) {
            Animation aniRotateClk = AnimationUtils.loadAnimation(this, R.anim.clock_wise);
            view.setColorFilter(Color.parseColor("#9da3a5"));
            view.startAnimation(aniRotateClk);
            textView.setTextColor(Color.parseColor("#9da3a5"));
            shots.setLiked(!status);
        } else {
            Animation aniRotateClk = AnimationUtils.loadAnimation(this, R.anim.anti_clockwise);
            view.setColorFilter(Color.parseColor("#EA4C89"));
            view.startAnimation(aniRotateClk);
            textView.setTextColor(Color.parseColor("#EA4C89"));
            shots.setLiked(!status);
        }
    }

    @Override
    public void publishShotsProgress(List<Shots> lstShots) {
        shotsList = lstShots;
    }

    @Override
    public void publishUserProgress(List<User> lstUser) {
        userList = lstUser;
        System.out.println(shotsList);
        rvLayoutManager = new LinearLayoutManager(this);
        adapter = new ListViewAdapter(this, shotsList, lstUser, this);
        rvItem.setLayoutManager(rvLayoutManager);
        rvItem.setAdapter(adapter);
    }
}