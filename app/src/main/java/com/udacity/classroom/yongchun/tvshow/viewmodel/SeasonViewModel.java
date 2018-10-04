package com.udacity.classroom.yongchun.tvshow.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.classroom.yongchun.tvshow.model.Season;
import com.udacity.classroom.yongchun.tvshow.repository.SeasonRepository;

public class SeasonViewModel extends ViewModel {

    private LiveData<Season> mSeason;

    public void init(String tvId, String seasonNumber) {
        mSeason = SeasonRepository.getSeason(tvId, seasonNumber);
    }

    public LiveData<Season> getSeason() {
        return mSeason;
    }
}
