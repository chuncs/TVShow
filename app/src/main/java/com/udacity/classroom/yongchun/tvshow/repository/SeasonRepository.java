package com.udacity.classroom.yongchun.tvshow.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.udacity.classroom.yongchun.tvshow.BuildConfig;
import com.udacity.classroom.yongchun.tvshow.helper.SeasonCache;
import com.udacity.classroom.yongchun.tvshow.model.Season;
import com.udacity.classroom.yongchun.tvshow.service.TVFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeasonRepository {

    private static SeasonCache seasonCache = SeasonCache.init();

    public static LiveData<Season> getSeason(String tvId, String seasonNumber) {
        final String uniqueKey = tvId + seasonNumber;
        LiveData<Season> cached = seasonCache.get(uniqueKey);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<Season> season = new MutableLiveData<>();
        seasonCache.put(uniqueKey, season);

        TVFactory.create().fetchSeason(tvId, seasonNumber, BuildConfig.API_KEY)
                .enqueue(new Callback<Season>() {
                    @Override
                    public void onResponse(@NonNull Call<Season> call, @NonNull Response<Season> response) {
                        season.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Season> call, @NonNull Throwable t) {
                        seasonCache.clear(uniqueKey);
                    }
                });

        return season;
    }
}
