
package com.androidtraining.k171hanudribbble.webservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("video_file_name")
    private String videoFileName;
    @JsonProperty("video_file_size")
    private Integer videoFileSize;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("silent")
    private Boolean silent;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("url")
    private String url;
    @JsonProperty("small_preview_url")
    private String smallPreviewUrl;
    @JsonProperty("large_preview_url")
    private String largePreviewUrl;
    @JsonProperty("xlarge_preview_url")
    private String xlargePreviewUrl;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("video_file_name")
    public String getVideoFileName() {
        return videoFileName;
    }

    @JsonProperty("video_file_name")
    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    @JsonProperty("video_file_size")
    public Integer getVideoFileSize() {
        return videoFileSize;
    }

    @JsonProperty("video_file_size")
    public void setVideoFileSize(Integer videoFileSize) {
        this.videoFileSize = videoFileSize;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("silent")
    public Boolean getSilent() {
        return silent;
    }

    @JsonProperty("silent")
    public void setSilent(Boolean silent) {
        this.silent = silent;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("small_preview_url")
    public String getSmallPreviewUrl() {
        return smallPreviewUrl;
    }

    @JsonProperty("small_preview_url")
    public void setSmallPreviewUrl(String smallPreviewUrl) {
        this.smallPreviewUrl = smallPreviewUrl;
    }

    @JsonProperty("large_preview_url")
    public String getLargePreviewUrl() {
        return largePreviewUrl;
    }

    @JsonProperty("large_preview_url")
    public void setLargePreviewUrl(String largePreviewUrl) {
        this.largePreviewUrl = largePreviewUrl;
    }

    @JsonProperty("xlarge_preview_url")
    public String getXlargePreviewUrl() {
        return xlargePreviewUrl;
    }

    @JsonProperty("xlarge_preview_url")
    public void setXlargePreviewUrl(String xlargePreviewUrl) {
        this.xlargePreviewUrl = xlargePreviewUrl;
    }

}
