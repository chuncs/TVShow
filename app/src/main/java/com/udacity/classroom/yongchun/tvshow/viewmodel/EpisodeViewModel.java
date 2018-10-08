package com.udacity.classroom.yongchun.tvshow.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.classroom.yongchun.tvshow.model.Episode;
import com.udacity.classroom.yongchun.tvshow.repository.EpisodeRepository;

public class EpisodeViewModel extends ViewModel {

    private LiveData<Episode> mEpisode;

    public void init(String tvId, String seasonNumber, String episodeNumber) {
        mEpisode = EpisodeRepository.getEpisode(tvId, seasonNumber, episodeNumber);
    }

    public LiveData<Episode> getEpisode() {
        return mEpisode;
    }
}
