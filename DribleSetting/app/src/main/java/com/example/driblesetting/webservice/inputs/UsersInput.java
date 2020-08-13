package com.example.driblesetting.webservice.inputs;

import com.example.driblesetting.webservice.DribbbleAPIs;

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
