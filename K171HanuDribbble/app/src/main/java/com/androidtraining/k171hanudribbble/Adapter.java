package com.androidtraining.k171hanudribbble;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING =1;

    Activity activity;
    List<Shot> lstData;
    IEventListener event;
   Context c;
    public Adapter(Context context, List<Shot> lstData, IEventListener event){
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
            Shot data = lstData.get(position);
            MyViewHolder viewHolder = (MyViewHolder) holder;
            try {
                viewHolder.bindData(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        TextView tv_title;
        TextView tv_text;
        ImageView iv_image;
        ImageView iv_avatar;
        ImageView iv_heart;
        TextView tv_like;
        public MyViewHolder(@NonNull View itemView, final IEventListener event) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_text = itemView.findViewById(R.id.tv_text);
            iv_image= itemView.findViewById(R.id.iv_image);
            iv_avatar = itemView.findViewById(R.id.iv_avt);
            iv_heart = itemView.findViewById(R.id.iv_heart);
            tv_like = itemView.findViewById(R.id.tv_like);
            iv_heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event.onClickIcon(iv_heart, getAdapterPosition());
                }
            });
        }
        public void bindData(Shot data) throws IOException {
            tv_title.setText(data.getTitle());
            String linkImage = data.getHidpi();
            Picasso.get().load(linkImage).into(iv_image);

            double number_of_like = data.getNumber_of_like();

            DecimalFormat decimalFormat = new DecimalFormat("0.#");
            String result = decimalFormat.format(Double.valueOf((number_of_like)));
            tv_like.setText(String.valueOf(result));
            boolean status = data.isLiked();
            if(status){
                iv_heart.setColorFilter(Color.parseColor("#ea4c89") , PorterDuff.Mode.MULTIPLY);
            }
        }
    }
}
