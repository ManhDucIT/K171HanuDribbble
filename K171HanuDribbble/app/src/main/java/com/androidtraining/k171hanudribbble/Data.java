package com.androidtraining.k171hanudribbble;

public class Data {
    String username;
    String text;
    int avatar;
    int image;
    boolean isLiked;
    double numberOfComment;
    double numberOfViewer;
    public Data(String username, String text, int avatar,int image, boolean isLiked, double numberOfComment, double numberOfViewer){
        this.username = username;
        this.text = text;
        this.avatar = avatar;
        this.image = image;
        this.isLiked = isLiked;
        this.numberOfComment = numberOfComment;
        this.numberOfViewer = numberOfViewer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public boolean isLiked() { return isLiked; }

    public void setLiked(boolean isLiked) { this.isLiked = isLiked; }
    public double getNumberOfComment() {
        return numberOfComment;
    }

    public void setNumberOfComment(int numberOfComment) {
        this.numberOfComment = numberOfComment;
    }
    public double getNumberOfViewer() {
        return numberOfViewer;
    }

    public void setNumberOfViewer(int numberOfViewer) {
        this.numberOfViewer = numberOfViewer;
    }

}



