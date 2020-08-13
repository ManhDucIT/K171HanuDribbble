package com.example.driblesetting.webservice.inputs;

import java.util.Hashtable;

public abstract class BaseInput {

    public enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }

    private Hashtable<String, String> queryParamters;
    private Hashtable<String, String> pathSegments;

    public abstract String getResource();

    public abstract HttpMethod getMethod();

    public Hashtable<String, String> getQueryParamters() {
        if(queryParamters == null){
            queryParamters = new Hashtable<String, String>();
        }

        return queryParamters;
    }

    public Hashtable<String, String> getPathSegments() {
        if(pathSegments == null){
            pathSegments = new Hashtable<String, String>();
        }

        return pathSegments;
    }
}
