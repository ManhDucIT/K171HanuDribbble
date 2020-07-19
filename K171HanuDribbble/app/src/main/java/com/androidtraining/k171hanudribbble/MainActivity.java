package com.androidtraining.k171hanudribbble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IEventListener {
    List<Data> lstData;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    RecyclerView recyclerView;
    TextView tv_username;
    TextView tv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstData = new ArrayList<Data>();
        Data data1 = new Data("Mandela Day", "Yoga Perdana", R.drawable.image1, R.drawable.flower,false, 1234, 12);
        Data data2 = new Data("Chinese Lady", "Nadya Fedrunova for Fireart", R.drawable.dog,R.drawable.life, true, 234, 23);
        Data data3 = new Data("Connect Microsite", "Nadya Fedrunova for Fireart", R.drawable.flower, R.drawable.cat,false, 78,67);
        lstData.add(data1);
        lstData.add(data2);
        lstData.add(data3);
        recyclerView = findViewById(R.id.rv);
        tv_username = findViewById(R.id.tv_username);
        tv_text = findViewById(R.id.tv_text);
        layoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new Adapter(lstData, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClickIcon(ImageView iv_heart, int position) {
        Data data = lstData.get(position);
        boolean status = data.isLiked;

        data.setLiked(!status);

        adapter.notifyItemChanged(position);
    }
}
