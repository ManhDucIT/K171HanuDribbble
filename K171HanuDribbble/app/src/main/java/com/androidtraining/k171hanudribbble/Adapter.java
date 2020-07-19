package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<Data> lstData;
    IEventListener event;
    public Adapter(List<Data> lstData, IEventListener event){
        this.lstData =lstData;
        this.event = event;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, parent, false);
        return new MyViewHolder(rowView, event);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Data data = lstData.get(position);
    holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_username;
        TextView tv_text;
        ImageView iv_avatar;
        ImageView iv_heart;
        public MyViewHolder(@NonNull View itemView, final IEventListener event) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_text = itemView.findViewById(R.id.tv_text);
            iv_avatar = itemView.findViewById(R.id.iv_avt);
            iv_heart = itemView.findViewById(R.id.iv_heart);
            iv_heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event.onClickIcon(iv_heart, getAdapterPosition());
                }
            });
        }
        public void bindData(Data data){
            String username = data.getUsername();
            String text = data.getText();
            int avatar = data.getAvatar();
            tv_username.setText(username);
            tv_text.setText(text);
            iv_avatar.setImageResource(avatar);
            boolean status = data.isLiked();
            if(status){
                iv_heart.setColorFilter(Color.RED);
            }
        }
    }
}
