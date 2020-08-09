package com.androidtraining.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("hidpi")
    @Expose
    private String hidpi;
    @SerializedName("normal")
    @Expose
    private String normal;
    @SerializedName("one_x")
    @Expose
    private String oneX;
    @SerializedName("two_x")
    @Expose
    private String twoX;
    @SerializedName("teaser")
    @Expose
    private String teaser;

    public Images(String hidpi, String normal, String oneX, String twoX, String teaser) {
        this.hidpi = hidpi;
        this.normal = normal;
        this.oneX = oneX;
        this.twoX = twoX;
        this.teaser = teaser;
    }

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getOneX() {
        return oneX;
    }

    public void setOneX(String oneX) {
        this.oneX = oneX;
    }

    public String getTwoX() {
        return twoX;
    }

    public void setTwoX(String twoX) {
        this.twoX = twoX;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

}
