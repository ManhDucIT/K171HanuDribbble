package com.androidtraining.k171hanudribbble.webservice.clients;



import com.androidtraining.k171hanudribbble.providers.IHttpHeaderProvider;
import com.androidtraining.k171hanudribbble.webservice.BaseClient;
import com.androidtraining.k171hanudribbble.webservice.inputs.ShotsInput;
import com.androidtraining.k171hanudribbble.webservice.inputs.UsersInput;
import com.androidtraining.k171hanudribbble.webservice.models.ShotModel;
import com.androidtraining.k171hanudribbble.webservice.models.UserModel;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;

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

