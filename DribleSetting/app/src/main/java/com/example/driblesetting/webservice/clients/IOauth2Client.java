package com.example.driblesetting.webservice.clients;

import com.example.driblesetting.webservice.inputs.AccessTokenInput;
import com.example.driblesetting.webservice.outputs.AccessTokenOutput;
import com.example.driblesetting.webservice.outputs.BaseOutput;

import bolts.Task;

public interface IOauth2Client {

    Task<BaseOutput<AccessTokenOutput>> getAccessToken(AccessTokenInput input);

}
