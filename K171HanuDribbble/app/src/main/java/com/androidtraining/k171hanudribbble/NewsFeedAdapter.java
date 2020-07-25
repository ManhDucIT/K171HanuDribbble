package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Model.NewsFeed;
import de.hdodenhof.circleimageview.CircleImageView;
import listener.IFeed;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedHolder> {
   private ArrayList<NewsFeed> newsFeeds;
    private Context context;
    private IFeed itemListener;

    public NewsFeedAdapter(ArrayList<NewsFeed> newsFeeds, Context context, IFeed itemListener) {
        this.newsFeeds = newsFeeds;
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override

    public NewsFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.feed_item,parent,false);
        return new NewsFeedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsFeedHolder holder, int position) {
        final NewsFeed newsFeed = newsFeeds.get(position);
        holder.bindData(newsFeed);
    }


    @Override
    public int getItemCount() {
        return newsFeeds.size();
    }
    public class NewsFeedHolder extends RecyclerView.ViewHolder  {
        ImageView imageView;
        CircleImageView avatar;
        TextView status;
        TextView author;
        TextView date;
        TextView heartNo;
        ImageView heart;
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
            heart = (ImageView) itemView.findViewById(R.id.heartic);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(itemListener!=null){
                            itemListener.ItemListener(v,getAdapterPosition());
                        }
                }
            });
        }
        public void bindData(NewsFeed newsFeed){
            seenNo.setText(String.valueOf(newsFeed.getSeenNo()));
            heartNo.setText(String.valueOf(newsFeed.getLikeNo()));
           avatar.setImageResource(newsFeed.getAvatar());
            imageView.setImageResource(newsFeed.getImage());
           commentNo.setText(String.valueOf(newsFeed.getCommentNo()));
           status.setText(newsFeed.getCaption());
           author.setText(newsFeed.getAuthor());
           date.setText(newsFeed.getDate());
           if(newsFeed.isHeart()){
               Animation aniRotate = AnimationUtils.loadAnimation(context,R.anim.rotateanim);
               heart.startAnimation(aniRotate);
               heart.setImageResource(R.drawable.heart);
           }else{
               Animation aniRotate = AnimationUtils.loadAnimation(context,R.anim.rotateanim);
               heart.startAnimation(aniRotate);
               heart.setImageResource(R.drawable.pinkheart);
           }
        }
    }
}
