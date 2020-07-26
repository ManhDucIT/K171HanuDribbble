package com.androidtraining.k171hanudribbble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IEvenListener {
    RecyclerView RecyclerView;
    MyAdapter myAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    List<Model> models;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView = findViewById(R.id.rView);
        getMyList();
        rvLayoutManager = new LinearLayoutManager(this);
        myAdapter = new MyAdapter(this, models, this);
        RecyclerView.setLayoutManager(rvLayoutManager);
        RecyclerView.setAdapter(myAdapter);
//        ViewCompat.setNestedScrollingEnabled(RecyclerView, false);
        initScrollListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.openBr) {
            Toast.makeText(this, "Opened", Toast.LENGTH_SHORT).show();
        }
        return true;

    }


    private void getMyList() {
        models = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            models.add(new Model("Phung Minh Nguyet", "16 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc.", false));
        }
    }

    @Override
    public void onItemClick(ImageView view, int position) {
        Model model = models.get(position);
        boolean status = model.isLiked();
        if(status){
            Animation aniRotateClk = AnimationUtils.loadAnimation(this, R.anim.anti_clockwise);
            view.setColorFilter(Color.parseColor("#9da3a5"));
            view.startAnimation(aniRotateClk);
            model.setLiked(!status);
        }
        else {
            Animation aniRotateClk = AnimationUtils.loadAnimation(this, R.anim.clock_wise);
            view.setColorFilter(Color.parseColor("#C51652"));
            view.startAnimation(aniRotateClk);
            model.setLiked(!status);
        }
    }

    private void initScrollListener() {
        RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == models.size() - 1) {
                        //bottom of list!
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });

    }

    private void loadMore() {
        if (models.size() < 10) {
            models.add(null);
            myAdapter.notifyItemInserted(models.size() - 1);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    models.remove(models.size() - 1);
                    models.size();
                    int scrollPosition = models.size();
                    myAdapter.notifyItemRemoved(scrollPosition);
                    int currentSize = scrollPosition;
                    int nextLimit = currentSize + 5;
                    while (currentSize - 1 < nextLimit) {
                        models.add(new Model("Phung Minh Nguyet", "16 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc.", false));
                        currentSize++;
                    }
                    System.out.println(currentSize);

                    myAdapter.notifyDataSetChanged();

                    isLoading = false;
                }

            }, 2000);
        }

    }
}


