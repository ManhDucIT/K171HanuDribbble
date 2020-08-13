package com.example.driblesetting.webservice.clients;

import com.example.driblesetting.webservice.inputs.ShotsInput;
import com.example.driblesetting.webservice.inputs.UsersInput;
import com.example.driblesetting.webservice.models.ShotModel;
import com.example.driblesetting.webservice.models.UserModel;
import com.example.driblesetting.webservice.outputs.BaseOutput;

import bolts.Task;

public interface IDribbbleClient {

    Task<BaseOutput<ShotModel[]>> getShots(ShotsInput input);

    Task<BaseOutput<UserModel>> getUsers(UsersInput input);
}
