package com.androidtraining.k171hanudribbble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.androidtraining.k171hanudribbble.R;
import com.androidtraining.k171hanudribbble.ListViewAdapter;
import com.androidtraining.k171hanudribbble.Following;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INoteItemListener {
    private List<Following> lstFollowing;
    private RecyclerView rvItem;
    private RecyclerView.LayoutManager rvLayoutManager;
    private ListViewAdapter adapter;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvItem = (RecyclerView) findViewById(R.id.rvItem);
        preparedData();
        initAdapter();
        initScrollListener();
    }

    private void preparedData() {
        lstFollowing = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
        }
    }

    private void initAdapter() {
        rvLayoutManager = new LinearLayoutManager(this);
        adapter = new ListViewAdapter(this, lstFollowing, this);
        rvItem.setLayoutManager(rvLayoutManager);
        rvItem.setAdapter(adapter);
    }

    private void initScrollListener() {
        rvItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastVisibleItemPosition() == lstFollowing.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        lstFollowing.add(null);
        lstFollowing.size();
        adapter.notifyItemInserted(lstFollowing.size() - 1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstFollowing.remove(lstFollowing.size() - 1);
                lstFollowing.size();
                int scrollPosition = lstFollowing.size();
                adapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 5;
                while (currentSize - 1 < nextLimit) {
                    lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
                    currentSize++;
                }
                System.out.println(currentSize);
                adapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    @Override
    public void onItemClick(ImageView view, int position) {
        Following follow = lstFollowing.get(position);
        boolean status = follow.isLiked();
        follow.setLiked(!status);
        adapter.notifyItemChanged(position);
    }
}
