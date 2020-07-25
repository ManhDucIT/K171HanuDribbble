package Model;

public class NewsFeed {
    private int avatar;
    private String caption;
    private String author;
    private String date;
    private int image;
    private int likeNo;
    private int commentNo;
    private int seenNo;
    private boolean heart;

    public boolean isHeart() {
        return heart;
    }

    public void setHeart(boolean heart) {
        this.heart = heart;
    }

    public NewsFeed(int avatar, String caption, String author, String date, int image, int likeNo, int commentNo, int seenNo, boolean heart) {
        this.avatar = avatar;
        this.caption = caption;
        this.author = author;
        this.date = date;
        this.image = image;
        this.likeNo = likeNo;
        this.commentNo = commentNo;
        this.seenNo = seenNo;
        this.heart = heart;
    }
    public NewsFeed(){

    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public int getLikeNo() {
        return likeNo;
    }

    public void setLikeNo(int likeNo) {
        this.likeNo = likeNo;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public int getSeenNo() {
        return seenNo;
    }

    public void setSeenNo(int seenNo) {
        this.seenNo = seenNo;
    }
}
