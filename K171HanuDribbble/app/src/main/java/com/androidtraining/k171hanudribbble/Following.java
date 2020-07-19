package com.androidtraining.k171hanudribbble;

public class Following {

    private int profileImage;
    private int industryImage;
    private String title;
    private String description;
    private int backgroundImage;
    private String heartCountNum;
    private String commentCountNum;
    private String viewCount;
    private String shareCount;
    private boolean isLiked;

    public Following(int profileImage, int industryImage, String title, String description, int backgroundImage, String heartCountNum, String commentCountNum, String viewCount, String shareCount, boolean isLiked) {
        this.profileImage = profileImage;
        this.industryImage = industryImage;
        this.title = title;
        this.description = description;
        this.backgroundImage = backgroundImage;
        this.heartCountNum = heartCountNum;
        this.commentCountNum = commentCountNum;
        this.viewCount = viewCount;
        this.shareCount = shareCount;
        this.isLiked = isLiked;
    }

    public Following() {
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public int getIndustryImage() {
        return industryImage;
    }

    public void setIndustryImage(int industryImage) {
        this.industryImage = industryImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getHeartCountNum() {
        return heartCountNum;
    }

    public void setHeartCountNum(String heartCountNum) {
        this.heartCountNum = heartCountNum;
    }

    public String getCommentCountNum() {
        return commentCountNum;
    }

    public void setCommentCountNum(String commentCountNum) {
        this.commentCountNum = commentCountNum;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }
}
