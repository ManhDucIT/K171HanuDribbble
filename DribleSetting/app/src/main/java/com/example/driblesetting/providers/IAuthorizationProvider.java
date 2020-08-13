package com.example.driblesetting.providers;

public interface IAuthorizationProvider {

    String authorize();

    void setAccessToken(String accessToken);

}
