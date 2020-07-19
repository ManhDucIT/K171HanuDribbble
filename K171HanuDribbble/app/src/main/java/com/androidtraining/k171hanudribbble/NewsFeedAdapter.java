package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Model.NewsFeed;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedHolder> {
   private ArrayList<NewsFeed> newsFeeds;
    private Context context;

    public NewsFeedAdapter(ArrayList<NewsFeed> newsFeeds, Context context) {
        this.newsFeeds = newsFeeds;
        this.context = context;
    }

    @NonNull
    @Override

    public NewsFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.feed_item,parent,false);
        return new NewsFeedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedHolder holder, int position) {
        NewsFeed newsFeed = newsFeeds.get(position);
        holder.seenNo.setText(String.valueOf(newsFeed.getSeenNo()));
        holder.heartNo.setText(String.valueOf(newsFeed.getLikeNo()));
        holder.avatar.setImageResource(newsFeed.getAvatar());
        holder.imageView.setImageResource(newsFeed.getImage());
        holder.commentNo.setText(String.valueOf(newsFeed.getCommentNo()));
        holder.status.setText(newsFeed.getCaption());
        holder.author.setText(newsFeed.getAuthor());
        holder.date.setText(newsFeed.getDate());
    }



    @Override
    public int getItemCount() {
        return newsFeeds.size();
    }
    public class NewsFeedHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CircleImageView avatar;
        TextView status;
        TextView author;
        TextView date;
        TextView heartNo;
        TextView commentNo;
        TextView seenNo;
        ImageView commentic;
        ImageView seenic;
        public NewsFeedHolder(@NonNull View itemView) {
            super(itemView);
            status = (TextView) itemView.findViewById(R.id.status) ;
            imageView = (ImageView) itemView.findViewById(R.id.image);
            avatar = (CircleImageView) itemView.findViewById(R.id.item_avatar);
            author = (TextView) itemView.findViewById(R.id.author);
            date = (TextView) itemView.findViewById(R.id.date);
            heartNo = (TextView) itemView.findViewById(R.id.heartNo);
            commentNo = (TextView) itemView.findViewById(R.id.commentNo);
            seenNo = (TextView) itemView.findViewById(R.id.seenNo);
            commentic = (ImageView) itemView.findViewById(R.id.commentic);
            seenic = (ImageView) itemView.findViewById(R.id.seenic);
        }
    }
}
