package com.androidtraining.k171hanudribbble.webservice.inputs;

public class UsersInput extends BaseInput {
    @Override
    public String getResource() {
       return "";
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}
