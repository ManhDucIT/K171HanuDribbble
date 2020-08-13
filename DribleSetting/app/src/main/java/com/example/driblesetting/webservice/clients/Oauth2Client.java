package com.example.driblesetting.webservice.clients;

import com.example.driblesetting.providers.IHttpHeaderProvider;
import com.example.driblesetting.webservice.BaseClient;
import com.example.driblesetting.webservice.inputs.AccessTokenInput;
import com.example.driblesetting.webservice.outputs.AccessTokenOutput;
import com.example.driblesetting.webservice.outputs.BaseOutput;

import bolts.Task;
import okhttp3.HttpUrl;

public class Oauth2Client extends BaseClient implements IOauth2Client {

    public Oauth2Client(IHttpHeaderProvider httpHeaderProvider) {
        super(httpHeaderProvider);
    }

    @Override
    protected HttpUrl baseUrl() {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("https")
                .host("dribbble.com");

        return builder.build();
    }

    @Override
    public Task<BaseOutput<AccessTokenOutput>> getAccessToken(AccessTokenInput input) {
        return executeAsync(input, AccessTokenOutput.class);
    }

}
