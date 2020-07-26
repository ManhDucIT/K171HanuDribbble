package com.androidtraining.k171hanudribbble;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING =1;

    Activity activity;
    List<Data> lstData;
    IEventListener event;
   int visibleTreshHold = 5;
   Context c;
   int lastVisibleItem, totalItemCount;
    public Adapter(Context context, List<Data> lstData, IEventListener event){
        this.c = context;
        this.lstData =lstData;
        this.event = event;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, parent, false);
            return new MyViewHolder(rowView, event);
        }else{
            View loadingView = LayoutInflater.from(c).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(loadingView);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Data data = lstData.get(position);
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.bindData(data);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

//        Data data = lstData.get(position);
//        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }
    @Override
    public int getItemViewType(int position) {
        return lstData.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_username;
        TextView tv_text;
        ImageView iv_image;
        ImageView iv_avatar;
        ImageView iv_heart;

        public MyViewHolder(@NonNull View itemView, final IEventListener event) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_text = itemView.findViewById(R.id.tv_text);
            iv_image= itemView.findViewById(R.id.iv_image);
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
            int image = data.getImage();
            int avatar = data.getAvatar();
            tv_username.setText(username);
            tv_text.setText(text);
            iv_image.setImageResource(image);
            iv_avatar.setImageResource(avatar);
            boolean status = data.isLiked();
            if(status){
                iv_heart.setColorFilter(Color.RED);
            }
        }
    }
}
