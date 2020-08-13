package com.example.driblesetting.providers;

import okhttp3.Headers;

public class HttpHeaderProvider implements IHttpHeaderProvider {

    private IAuthorizationProvider authorizationProvider;

    public HttpHeaderProvider(IAuthorizationProvider authorizationProvider){
        this.authorizationProvider = authorizationProvider;
    }

    @Override
    public Headers getHeaders() {
        Headers headers = new Headers.Builder()
                .add("Authorization", authorizationProvider.authorize())
                .add("Accept", "application/vnd.dribbble.v2.param+json")
                .build();

        return headers;
    }

}