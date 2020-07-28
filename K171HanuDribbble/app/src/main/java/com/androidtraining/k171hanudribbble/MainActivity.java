package com.androidtraining.k171hanudribbble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

import DAO.PostDAO;
import Model.NewsFeed;
import listener.IFeed;
import listener.ILoadMore;

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
        rs = (RecyclerView)findViewById(R.id.newsfeedsHolder);
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

        final PostDAO pD = new PostDAO();
        feeds = pD.getAllNewsFeed();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rs.setLayoutManager(linearLayoutManager);
        feedAdapter = new NewsFeedAdapter(feeds, this, this);
        rs.setAdapter(feedAdapter);
        rs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("scroll", "scroll");
                if(linearLayoutManager.findLastVisibleItemPosition() == feeds.size()-1 && feeds.get(feeds.size()-1)!= null){
                    Log.d("wait", "scroll");
                    feeds.add(null);
                    feedAdapter.notifyItemInserted(feeds.size()-1);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            feeds.remove(feeds.size()-1);
                            feedAdapter.notifyItemRemoved(feeds.size());
                           feeds.addAll(pD.fetchData());
                            feedAdapter.notifyDataSetChanged();
                        }
                    },5000);

                }
            }

        });

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
    public void ItemListener(int position,ImageView v) {
        NewsFeed newsFeed  = feeds.get(position);
        boolean currentStatus = newsFeed.isHeart();
        if(currentStatus){
            Animation aniRotate = AnimationUtils.loadAnimation(this,R.anim.rotateanim);
            v.startAnimation(aniRotate);
            v.setImageResource(R.drawable.heart);
            newsFeed.setHeart(!currentStatus);
        }else {
            Animation aniRotate = AnimationUtils.loadAnimation(this, R.anim.rotateanim);
            v.startAnimation(aniRotate);
            v.setImageResource(R.drawable.pinkheart);
            newsFeed.setHeart(!currentStatus);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
