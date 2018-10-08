package com.udacity.classroom.yongchun.tvshow.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.udacity.classroom.yongchun.tvshow.BuildConfig;
import com.udacity.classroom.yongchun.tvshow.helper.EpisodeCache;
import com.udacity.classroom.yongchun.tvshow.model.Episode;
import com.udacity.classroom.yongchun.tvshow.service.TVFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    private static EpisodeCache episodeCache = EpisodeCache.init();

    public static LiveData<Episode> getEpisode(String tvId, String seasonNumber, String episodeNumber) {
        final String uniqueKey = tvId + seasonNumber + episodeNumber;
        LiveData<Episode> cached = episodeCache.get(uniqueKey);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<Episode> episode = new MutableLiveData<>();
        episodeCache.put(uniqueKey, episode);

        TVFactory.create().fetchEpisode(tvId, seasonNumber, episodeNumber, BuildConfig.API_KEY)
                .enqueue(new Callback<Episode>() {
                    @Override
                    public void onResponse(@NonNull Call<Episode> call, @NonNull Response<Episode> response) {
                        episode.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Episode> call, @NonNull Throwable t) {
                        episodeCache.clear(uniqueKey);
                    }
                });

        return episode;
    }
}
