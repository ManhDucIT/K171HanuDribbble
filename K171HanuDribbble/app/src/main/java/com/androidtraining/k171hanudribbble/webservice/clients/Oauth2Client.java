package com.androidtraining.k171hanudribbble.webservice.clients;


import com.androidtraining.k171hanudribbble.providers.IHttpHeaderProvider;
import com.androidtraining.k171hanudribbble.webservice.BaseClient;
import com.androidtraining.k171hanudribbble.webservice.inputs.AccessTokenInput;
import com.androidtraining.k171hanudribbble.webservice.outputs.AccessTokenOutput;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;

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
