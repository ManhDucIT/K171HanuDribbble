package com.androidtraining.k171hanudribbble.providers;

public interface IAuthorizationProvider {

    String authorize();

    void setAccessToken(String accessToken);

}
