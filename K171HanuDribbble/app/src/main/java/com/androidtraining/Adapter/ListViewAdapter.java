package com.androidtraining.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtraining.Listener.INoteItemListener;
import com.androidtraining.Models.Shots;
import com.androidtraining.Models.User;
import com.androidtraining.Threads.LoadImage;
import com.androidtraining.k171hanudribbble.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private INoteItemListener eventListener;
    private List<User> lstUser;
    private List<Shots> lstShots;

    public ListViewAdapter(Context context, List<Shots> lstShots, List<User> lstUser, INoteItemListener eventListener) {
        this.context = context;
        this.lstShots = lstShots;
        this.eventListener = eventListener;
        this.lstUser = lstUser;
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
            Shots shots = lstShots.get(position);
            User user = lstUser.get(0);
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.bindData(shots, user);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return lstShots.size();
    }


    @Override
    public int getItemViewType(int position) {
        return lstShots.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profile_image;
        private TextView tvTitle;
        private TextView tvUser;
        private TextView tvTime;
        private ImageView ivBackground;
        private TextView tvHeart;
        private TextView tvComment;
        private TextView tvView;
        private TextView tvAttach;
        private ImageView ivHeart;

        public ItemViewHolder(@NonNull View itemView, final INoteItemListener eventListener) {
            super(itemView);
            profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvUser = (TextView) itemView.findViewById(R.id.tvUser);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvComment = (TextView) itemView.findViewById(R.id.tvComment);
            tvHeart = (TextView) itemView.findViewById(R.id.tvHeart);
            tvView = (TextView) itemView.findViewById(R.id.tvView);
            tvAttach = (TextView) itemView.findViewById(R.id.tvAttach);
            ivHeart = (ImageView) itemView.findViewById(R.id.ivHeart);
            ivHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (eventListener != null) {
                        eventListener.onItemClick(ivHeart, tvHeart, getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(Shots shots, User user) {

            LoadImage avatar = new LoadImage(profile_image);
            String avatar_url = user.getAvatarUrl();
            avatar.execute(avatar_url);
            tvTitle.setText(shots.getTitle());
            tvUser.setText(user.getName());
            LoadImage background = new LoadImage(ivBackground);
            String images = shots.getImages().getHidpi();
            background.execute(images);
            CharSequence time = convertTime(shots.getPublishedAt());
            tvTime.setText(", "+ time);
        }

        private CharSequence convertTime(String time){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            CharSequence ago = null;
            try {
                long timePublishAt = sdf.parse(time).getTime();
                long now = System.currentTimeMillis();
                ago = DateUtils.getRelativeTimeSpanString(timePublishAt, now, DateUtils.MINUTE_IN_MILLIS);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return ago;
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
