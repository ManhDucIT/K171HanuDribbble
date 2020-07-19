package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtraining.k171hanudribbble.Following;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    private Context context;
    private List<Following> lstFollowing;

    public ListViewAdapter(Context context, List<Following> lstFollowing) {
        this.context = context;
        this.lstFollowing = lstFollowing;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Following follow = lstFollowing.get(position);
        holder.bindData(follow);
    }

    @Override
    public int getItemCount() {
        return lstFollowing.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView profile_image;
        private CircleImageView cmImage;
        private TextView tvTitle;
        private TextView tvDescription;
        private ImageView ivBackground;
        private TextView tvHeart;
        private TextView tvComment;
        private TextView tvView;
        private TextView tvAttach;

        public ViewHolder(@NonNull View itemView) {
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
        }



    }
}
