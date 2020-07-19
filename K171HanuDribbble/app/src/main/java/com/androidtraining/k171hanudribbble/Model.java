package com.androidtraining.k171hanudribbble;

public class Model {
    private String tvName, tvTime, tvContent ;
    private int imgAva;

    public Model(String tvName, String tvTime, String tvContent) {
        this.tvName = tvName;
        this.tvTime = tvTime;
        this.tvContent = tvContent;
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
