package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private IEvenListener eventListener;
    public List<Model> models;
    Context c;

    public MyAdapter(Context c, List<Model> models, IEvenListener eventListener) {
        this.c = c;
        this.models = models;
        this.eventListener = eventListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(c).inflate(R.layout.row, parent, false);
            return new ItemViewHolder(itemView, eventListener);
        } else {
            View loadingView = LayoutInflater.from(c).inflate(R.layout.loading, parent, false);
            return new LoadingViewHolder(loadingView);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            Model model = models.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.bindData(model);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }


    @Override
    public int getItemCount() {

        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return models.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CircleImageView profile_image;
        private TextView tvName;
        private TextView tvContent;
        private ImageView ivHeart;
        private TextView tvTime;
        private WeakReference<IEvenListener> eventListenerReference;

        public ItemViewHolder(@NonNull  View itemView,  IEvenListener eventListener) {
            super(itemView);

            profile_image = (CircleImageView) itemView.findViewById(R.id.imgAva);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            ivHeart = (ImageView) itemView.findViewById(R.id.imgPost);
            eventListenerReference = new WeakReference<>(eventListener);
            ivHeart.setOnClickListener(this);
        }

        public void bindData(Model model) {
            profile_image.setImageResource(model.getImgAva());
            tvName.setText(model.getTvName());
            tvContent.setText(model.getTvContent());
            tvTime.setText(model.getTvTime());
            boolean status = model.isLiked();
            if (status) {

                ivHeart.setColorFilter(Color.parseColor("#C51652"));

            } else {

                ivHeart.setColorFilter(Color.parseColor("#9da3a5"));

            }
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == ivHeart.getId()){
                eventListenerReference.get().onItemClick(ivHeart,getAdapterPosition());
            }
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}


