package DAO;

import com.androidtraining.k171hanudribbble.R;

import java.util.ArrayList;

import Model.NewsFeed;

public class PostDAO {

    public ArrayList<NewsFeed> getAllNewsFeed(){
        ArrayList<NewsFeed> newsFeeds = new ArrayList<>();
        for(int i = 0; i < 2;i++) {
            newsFeeds.add(new NewsFeed(R.drawable.avatar, "Hello các anh em thiện lành lại là tôi đây các ông ơi", "PhucNguyen", ",2 thg 7,2020", R.drawable.cou, 3, 4, 2, true));
        }
        return newsFeeds;
    }
    public ArrayList<NewsFeed> fetchData(){
        ArrayList<NewsFeed> newsFeeds = new ArrayList<>();
        for(int i = 0; i < 2;i++) {
            newsFeeds.add(new NewsFeed(R.drawable.avatar, "Hello các anh em thiện lành lại là tôi đây các ông ơi", "PhucNguyen", ",2 thg 7,2020", R.drawable.cou, 3, 4, 2, true));
        }
        return newsFeeds;
    }
}
