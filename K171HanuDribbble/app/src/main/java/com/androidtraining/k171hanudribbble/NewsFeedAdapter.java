package com.androidtraining.k171hanudribbble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import Model.NewsFeed;
import de.hdodenhof.circleimageview.CircleImageView;
import listener.IFeed;
import listener.ILoadMore;

public class NewsFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
   private ArrayList<NewsFeed> newsFeeds;
    private Context context;
    private IFeed iFeed;
    private int VIEW_TYPE_ITEM = 0;
    private int VIEW_TYPE_PROGRESS = 1;
    private ILoadMore iLoadMore;
    boolean isLoading;
    private int visibleThreshold =5;
    private int lastItem;
    private int totalCount;
    private RecyclerView recyclerView;

    public NewsFeedAdapter(ArrayList<NewsFeed> newsFeeds, final Context context, IFeed iFeed) {
        this.newsFeeds = newsFeeds;
        this.context = context;
        this.iFeed = iFeed;
    }

    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.feed_item, parent, false);
            return new NewsFeedHolder(itemView, iFeed);
        }else if(viewType == VIEW_TYPE_PROGRESS) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View itemView = layoutInflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final NewsFeed newsFeed = newsFeeds.get(position);
        if(holder instanceof NewsFeedHolder) {
            ((NewsFeedHolder) holder).bindData(newsFeed);
        }else if(holder instanceof  LoadingHolder){
            ((LoadingHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return newsFeeds.get(position) == null ? VIEW_TYPE_PROGRESS:VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return newsFeeds.size();
    }
    public class NewsFeedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        WeakReference<IFeed> iFeedWeakReference;
        public NewsFeedHolder(@NonNull View itemView, IFeed ifeed) {
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
            heart.setOnClickListener(this);
            iFeedWeakReference = new WeakReference<>(ifeed);

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
                heart.setImageResource(R.drawable.pinkheart);
            }
            else{
                heart.setImageResource(R.drawable.heart);
            }
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == heart.getId()){
                iFeedWeakReference.get().ItemListener(getAdapterPosition(),heart);
            }
            }
        }
        public class LoadingHolder extends RecyclerView.ViewHolder{
            ProgressBar progressBar;
            public LoadingHolder(@NonNull View itemView) {
                super(itemView);
                progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
            }
        }
    }

