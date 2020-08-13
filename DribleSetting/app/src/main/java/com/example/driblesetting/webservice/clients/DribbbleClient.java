package com.example.driblesetting.webservice.clients;


import com.example.driblesetting.providers.IHttpHeaderProvider;
import com.example.driblesetting.webservice.BaseClient;
import com.example.driblesetting.webservice.inputs.ShotsInput;
import com.example.driblesetting.webservice.inputs.UsersInput;
import com.example.driblesetting.webservice.models.ShotModel;
import com.example.driblesetting.webservice.models.UserModel;
import com.example.driblesetting.webservice.outputs.BaseOutput;

import bolts.Task;
import okhttp3.HttpUrl;

public class DribbbleClient extends BaseClient implements IDribbbleClient {

    public DribbbleClient(IHttpHeaderProvider httpHeaderProvider) {
        super(httpHeaderProvider);
    }

    @Override
    protected HttpUrl baseUrl() {
        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("https")
                .host("api.dribbble.com")
                .addPathSegment("v2")
                .addPathSegment("user");

        return builder.build();
    }


    @Override
    public Task<BaseOutput<ShotModel[]>> getShots(ShotsInput input) {
        return executeAsync(input, ShotModel[].class);
    }

    @Override
    public Task<BaseOutput<UserModel>> getUsers(UsersInput input) {
        return executeAsync(input, UserModel.class);
    }
}

