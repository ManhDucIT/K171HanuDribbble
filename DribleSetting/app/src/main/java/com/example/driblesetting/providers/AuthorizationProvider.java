package com.example.driblesetting.providers;

public class AuthorizationProvider implements IAuthorizationProvider {

    private String authorization = "";

    @Override
    public String authorize() {
        return authorization;
    }

    @Override
    public void setAccessToken(String accessToken) {
        authorization = "Bearer " + accessToken;
    }

}
