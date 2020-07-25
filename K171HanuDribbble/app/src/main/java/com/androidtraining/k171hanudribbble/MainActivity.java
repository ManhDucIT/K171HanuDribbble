package com.androidtraining.k171hanudribbble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

import Model.NewsFeed;
import listener.IFeed;

public class MainActivity extends AppCompatActivity implements IFeed {
    RecyclerView rs;
    Button followers;
    Button following;
    Button projects_btn;
    Toolbar toolbar;
    ArrayList<NewsFeed> feeds;
    NewsFeedAdapter feedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_right);
        followers = findViewById(R.id.follwer_btn);
        following = findViewById(R.id.following_btn);
        projects_btn = findViewById(R.id.projects_btn);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        rs = findViewById(R.id.newsfeedsHolder);
         feeds = new ArrayList<NewsFeed>();
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feeds.add(new NewsFeed(R.drawable.avatar,"Hello các anh em thiện lành lại là tôi đây các ông ơi","PhucNguyen",",2 thg 7,2020",R.drawable.cou,3,4,2,true));
        feedAdapter = new NewsFeedAdapter(feeds,this,this);
        rs.setAdapter(feedAdapter);
        rs.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnShare:
                Intent send = new Intent(Intent.ACTION_SEND);
                send.setType("text/plain");
                startActivity(send);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ItemListener(View view, int position) {
        NewsFeed nf = feeds.get(position);
        boolean currentStatus = nf.isHeart();
        nf.setHeart(!currentStatus);
        feedAdapter.notifyItemChanged(position);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
