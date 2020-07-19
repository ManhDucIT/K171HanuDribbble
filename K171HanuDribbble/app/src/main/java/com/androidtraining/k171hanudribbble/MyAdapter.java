package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
 Context c;
 ArrayList<Model> models;

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.imgAva.setImageResource(models.get(position).getImgAva());
        holder.tvName.setText(models.get(position).getTvName());
        holder.tvTime.setText(models.get(position).getTvTime());
        holder.tvContent.setText(models.get(position).getTvContent());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
