package com.androidtraining.Threads;

import com.androidtraining.Models.Shots;
import com.androidtraining.Models.User;

import java.util.List;

public interface ILoaderProgressListener {
    void publishShotsProgress(List<Shots> lstShots);
    void publishUserProgress(List<User> lstUser);
}
