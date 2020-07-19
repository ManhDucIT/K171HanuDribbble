package com.androidtraining.k171hanudribbble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    RecyclerView RecyclerView;
    com.androidtraining.k171hanudribbble.MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView = findViewById(R.id.rView);
        RecyclerView.setNestedScrollingEnabled(false);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this ));
        myAdapter = new MyAdapter(this,getMyList() );
        RecyclerView.setAdapter(myAdapter);

    }

    private ArrayList<Model> getMyList(){
        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model("Phung Minh Nguyet", "16 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "4 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "9 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "16 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "4 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "9 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "16 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "4 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));
        models.add(new Model("Phung Minh Nguyet", "9 giờ trước", "Android CardView widget allows us to control the background color, shadow, corner radius, elevation etc."));



     return models;
    }
}
