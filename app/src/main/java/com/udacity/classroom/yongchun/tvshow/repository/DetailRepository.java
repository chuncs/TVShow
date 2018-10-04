package com.udacity.classroom.yongchun.tvshow.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.udacity.classroom.yongchun.tvshow.BuildConfig;
import com.udacity.classroom.yongchun.tvshow.helper.DetailCache;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.service.TVFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository {

    private static final String APPEND_TO_RESPONSE = "credits,similar";
    private static DetailCache detailCache = DetailCache.init();

    public static LiveData<Detail> getDetail(String tvId) {
        LiveData<Detail> cached = detailCache.get(tvId);
        if (cached != null) {
            return cached;
        }

        final MutableLiveData<Detail> detail = new MutableLiveData<>();
        detailCache.put(tvId, detail);

        TVFactory.create().fetchDetail(tvId, BuildConfig.API_KEY, APPEND_TO_RESPONSE)
                .enqueue(new Callback<Detail>() {
                    @Override
                    public void onResponse(@NonNull Call<Detail> call, @NonNull Response<Detail> response) {
                        detail.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Detail> call, @NonNull Throwable t) {
                        detailCache.clear(tvId);
                    }
                });

        return detail;
    }
}
