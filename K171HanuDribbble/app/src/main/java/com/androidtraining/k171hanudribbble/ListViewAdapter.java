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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtraining.k171hanudribbble.Following;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Following> lstFollowing;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private INoteItemListener eventListener;

    public ListViewAdapter(Context context, List<Following> lstFollowing, INoteItemListener eventListener) {
        this.context = context;
        this.lstFollowing = lstFollowing;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            return new ItemViewHolder(itemView, eventListener);
        } else {
            View loadingView = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(loadingView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Following following = lstFollowing.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.bindData(following);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return lstFollowing.size();
    }


    @Override
    public int getItemViewType(int position) {
        return lstFollowing.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profile_image;
        private CircleImageView cmImage;
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView ivBackground;
        private TextView tvHeart;
        private TextView tvComment;
        private TextView tvView;
        private TextView tvAttach;
        private ImageView ivHeart;

        public ItemViewHolder(@NonNull View itemView, final INoteItemListener eventListener) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            cmImage = (CircleImageView) itemView.findViewById(R.id.cmImage);
            ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvComment = (TextView) itemView.findViewById(R.id.tvComment);
            tvHeart = (TextView) itemView.findViewById(R.id.tvHeart);
            tvView = (TextView) itemView.findViewById(R.id.tvView);
            tvAttach = (TextView) itemView.findViewById(R.id.tvAttach);
            ivHeart = (ImageView) itemView.findViewById(R.id.ivHeart);
            ivHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (eventListener != null) {

                        eventListener.onItemClick(ivHeart, getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(Following follow) {
            profile_image.setImageResource(follow.getProfileImage());
            cmImage.setImageResource(follow.getIndustryImage());
            ivBackground.setImageResource(follow.getBackgroundImage());
            tvTitle.setText(follow.getTitle());
            tvDescription.setText(follow.getDescription());
            tvComment.setText(follow.getCommentCountNum());
            tvHeart.setText(follow.getHeartCountNum());
            tvView.setText(follow.getViewCount());
            tvAttach.setText(follow.getShareCount());
            boolean status = follow.isLiked();
            if(status){
                Animation aniRotateClk = AnimationUtils.loadAnimation(context,R.anim.anti_clockwise);
                ivHeart.setColorFilter(Color.parseColor("#EA4C89"));
                ivHeart.startAnimation(aniRotateClk);
                tvHeart.setTextColor(Color.parseColor("#EA4C89"));
            } else {
                Animation aniRotateClk = AnimationUtils.loadAnimation(context,R.anim.clock_wise);
                ivHeart.setColorFilter(Color.parseColor("#9da3a5"));
                ivHeart.startAnimation(aniRotateClk);
                tvHeart.setTextColor(Color.parseColor("#9da3a5"));
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
