package com.androidtraining.k171hanudribbble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.androidtraining.k171hanudribbble.R;
import com.androidtraining.k171hanudribbble.ListViewAdapter;
import com.androidtraining.k171hanudribbble.Following;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Following> lstFollowing;
    private RecyclerView rvItem;
    private RecyclerView.LayoutManager rvLayoutManager;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvItem = (RecyclerView) findViewById(R.id.rvItem);
        preparedData();
        rvItem.setNestedScrollingEnabled(false);
        rvLayoutManager = new LinearLayoutManager(this);
        adapter = new ListViewAdapter(this, lstFollowing);
        rvItem.setLayoutManager(rvLayoutManager);
        rvItem.setAdapter(adapter);
    }

    private void preparedData() {
        lstFollowing = new ArrayList<>();
        lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
        lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
        lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
        lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
        lstFollowing.add(new Following(R.drawable.profile, R.drawable.avatar, "Event Discovery app", "Shahriar Hossain for Prelook Studio, 6 days ago", R.drawable.background, "242", "19", "16.393", "2", false));
    }
}
