package com.example.driblesetting.webservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel implements Serializable {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("login")
    private String login;

    @JsonProperty("html_url")
    private String html_url;

    @JsonProperty("avatar_url")
    private String avatar_url;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("location")
    private String location;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("can_upload_shot")
    private boolean can_upload_shot;

    @JsonProperty("pro")
    private boolean pro;

    @JsonProperty("followers_count")
    private int followers_count;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("type")
    private String type;

    @JsonProperty("teams")
    private ArrayList<Team> teams = new ArrayList<>();

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("html_url")
    public String getHtml_url() {
        return html_url;
    }

    @JsonProperty("html_url")
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @JsonProperty("avatar_url")
    public String getAvatar_url() {
        return avatar_url;
    }

    @JsonProperty("avatar_url")
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonProperty("bio")
    public String getBio() {
        return bio;
    }

    @JsonProperty("bio")
    public void setBio(String bio) {
        this.bio = bio;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("can_upload_shot")
    public boolean isCan_upload_shot() {
        return can_upload_shot;
    }

    @JsonProperty("can_upload_shot")
    public void setCan_upload_shot(boolean can_upload_shot) {
        this.can_upload_shot = can_upload_shot;
    }

    @JsonProperty("pro")
    public boolean isPro() {
        return pro;
    }

    @JsonProperty("pro")
    public void setPro(boolean pro) {
        this.pro = pro;
    }

    @JsonProperty("followers_count")
    public int getFollowers_count() {
        return followers_count;
    }

    @JsonProperty("followers_count")
    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    @JsonProperty("created_at")
    public String getCreated_at() {
        return created_at;
    }

    @JsonProperty("created_at")
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("teams")
    public ArrayList<Team> getTeams() {
        return teams;
    }

    @JsonProperty("teams")
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
