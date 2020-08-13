package com.androidtraining.k171hanudribbble.webservice.clients;

import com.androidtraining.k171hanudribbble.webservice.inputs.AccessTokenInput;
import com.androidtraining.k171hanudribbble.webservice.outputs.AccessTokenOutput;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;


import bolts.Task;

public interface IOauth2Client {

    Task<BaseOutput<AccessTokenOutput>> getAccessToken(AccessTokenInput input);

}
