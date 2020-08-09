package com.androidtraining.Threads;

public class GetRequest {
    private int page;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public String getUserUrl() {
//        return "https://api.dribbble.com/v2/user";
//    }
//
//    public String getShotsUrl() {
//        return "https://api.dribbble.com/v2/user/shots";
//    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
