
package com.androidtraining.k171hanudribbble.webservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Images implements Serializable {

    @JsonProperty("hidpi")
    private Object hidpi;
    @JsonProperty("normal")
    private String normal;
    @JsonProperty("teaser")
    private String teaser;

    @JsonProperty("hidpi")
    public Object getHidpi() {
        return hidpi;
    }

    @JsonProperty("hidpi")
    public void setHidpi(Object hidpi) {
        this.hidpi = hidpi;
    }

    @JsonProperty("normal")
    public String getNormal() {
        return normal;
    }

    @JsonProperty("normal")
    public void setNormal(String normal) {
        this.normal = normal;
    }

    @JsonProperty("teaser")
    public String getTeaser() {
        return teaser;
    }

    @JsonProperty("teaser")
    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

}
