package com.androidtraining.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("can_upload_shot")
    @Expose
    private Boolean canUploadShot;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("followers_count")
    @Expose
    private Integer followersCount;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("links")
    @Expose
    private Links links;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pro")
    @Expose
    private Boolean pro;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("teams")
    @Expose
    private List<Object> teams = null;

    public User(String avatarUrl, int id, String name) {
        this.avatarUrl = avatarUrl;
        this.id = id;
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean getCanUploadShot() {
        return canUploadShot;
    }

    public void setCanUploadShot(Boolean canUploadShot) {
        this.canUploadShot = canUploadShot;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPro() {
        return pro;
    }

    public void setPro(Boolean pro) {
        this.pro = pro;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getTeams() {
        return teams;
    }

    public void setTeams(List<Object> teams) {
        this.teams = teams;
    }

}