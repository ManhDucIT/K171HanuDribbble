package com.androidtraining.k171hanudribbble;

public class Model {
    private String tvName, tvTime, tvContent ;
    private int imgAva;
    private boolean isLiked;

    public Model(String tvName, String tvTime, String tvContent, boolean isLiked) {
        this.tvName = tvName;
        this.tvTime = tvTime;
        this.tvContent = tvContent;
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getTvTime() {
        return tvTime;
    }

    public void setTvTime(String tvTime) {
        this.tvTime = tvTime;
    }

    public String getTvContent() {
        return tvContent;
    }

    public void setTvContent(String tvContent) {
        this.tvContent = tvContent;
    }

    public int getImgAva() {
        return imgAva;
    }

    public void setImgAva(int imgAva) {
        this.imgAva = imgAva;
    }
}
