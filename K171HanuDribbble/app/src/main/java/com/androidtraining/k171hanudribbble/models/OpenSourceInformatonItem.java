package com.androidtraining.k171hanudribbble.models;

public class OpenSourceInformatonItem {
    private String os_title;
    private String os_author;
    private String os_version;
    private String os_link;

    public OpenSourceInformatonItem(String os_title, String os_author, String os_version, String os_link) {
        this.os_title = os_title;
        this.os_author = os_author;
        this.os_version = os_version;
        this.os_link = os_link;
    }

    public String getOs_link() {
        return os_link;
    }

    public void setOs_link(String os_link) {
        this.os_link = os_link;
    }

    public String getOs_title() {
        return os_title;
    }

    public void setOs_title(String os_title) {
        this.os_title = os_title;
    }

    public String getOs_author() {
        return os_author;
    }

    public void setOs_author(String os_author) {
        this.os_author = os_author;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }
}
