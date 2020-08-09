package com.androidtraining.k171hanudribbble;

public class Shot {
    String title;
    String description;
    String hidpi;
    boolean isLiked;
    double numberOfComment;
    double numberOfViewer;
    double number_of_like;
    public Shot(String title,String description, String hidpi, boolean isLiked){
        this.title = title;
        this.description = description;
       this.hidpi = hidpi;
       this.isLiked =isLiked ;
    }

//    public Shot(String title, String description, String hidpi, boolean isLiked ) {
//        this.title = title;
//    }

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

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
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

    public double getNumber_of_like() {
        return number_of_like;
    }

    public void setNumber_of_like(double number_of_like) {
        this.number_of_like = number_of_like;
    }
}
