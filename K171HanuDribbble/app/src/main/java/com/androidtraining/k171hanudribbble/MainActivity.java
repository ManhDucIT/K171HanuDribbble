package com.androidtraining.k171hanudribbble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import Model.NewsFeed;

public class MainActivity extends AppCompatActivity {
    RecyclerView rs;
    Button followers;
    Button following;
    Button projects_btn;
    Toolbar toolbar;
    ImageView share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_right);
        followers = findViewById(R.id.follwer_btn);
        following = findViewById(R.id.following_btn);
        projects_btn = findViewById(R.id.projects_btn);
        toolbar = findViewById(R.id.toolbar);
        share = findViewById(R.id.shareBtn);
        setSupportActionBar(toolbar);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetfollower,
                        (RelativeLayout) findViewById(R.id.bottomSheetContainer));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetfollowing,
                        (RelativeLayout) findViewById(R.id.bottomSheetContainer));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        projects_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheetproject,
                        (RelativeLayout) findViewById(R.id.bottomSheetContainer));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Intent.ACTION_SEND);
                send.setType("text/plain");
                startActivity(send);
            }
        });
        rs = findViewById(R.id.newsfeedsHolder);
        ArrayList<NewsFeed> feeds = new ArrayList<NewsFeed>();
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,true,3,4,2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        NewsFeedAdapter feedAdapter = new NewsFeedAdapter(feeds,this);
        rs.setLayoutManager(linearLayoutManager);
        rs.setAdapter(feedAdapter);
    }
}
