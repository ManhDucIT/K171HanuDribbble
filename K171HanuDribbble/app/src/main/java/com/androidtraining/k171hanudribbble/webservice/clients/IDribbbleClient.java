package com.androidtraining.k171hanudribbble.webservice.clients;


import com.androidtraining.k171hanudribbble.webservice.inputs.ShotsInput;
import com.androidtraining.k171hanudribbble.webservice.inputs.UsersInput;
import com.androidtraining.k171hanudribbble.webservice.models.ShotModel;
import com.androidtraining.k171hanudribbble.webservice.models.UserModel;
import com.androidtraining.k171hanudribbble.webservice.outputs.BaseOutput;

import bolts.Task;

public interface IDribbbleClient {

    Task<BaseOutput<ShotModel[]>> getShots(ShotsInput input);

    Task<BaseOutput<UserModel>> getUsers(UsersInput input);
}
