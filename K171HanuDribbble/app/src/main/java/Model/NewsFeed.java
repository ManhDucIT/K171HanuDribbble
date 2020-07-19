package Model;

public class NewsFeed {
    private int avatar;
    private String caption;
    private String author;
    private String date;
    private int image;
    private boolean like;
    private int likeNo;
    private int commentNo;
    private int seenNo;

    public NewsFeed(int avatar, String caption, String author, String date, int image,boolean like,int likeNo, int commentNo, int seenNo) {
        this.avatar = avatar;
        this.caption = caption;
        this.author = author;
        this.date = date;
        this.image = image;
        this.like = like;
        this.likeNo = likeNo;
        this.commentNo = commentNo;
        this.seenNo = seenNo;
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

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
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
