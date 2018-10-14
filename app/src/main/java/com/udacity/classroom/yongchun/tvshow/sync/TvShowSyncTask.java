package com.udacity.classroom.yongchun.tvshow.sync;

import android.content.Context;
import android.support.annotation.NonNull;

import com.udacity.classroom.yongchun.tvshow.BuildConfig;
import com.udacity.classroom.yongchun.tvshow.database.AppDatabase;
import com.udacity.classroom.yongchun.tvshow.helper.AppExecutors;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.service.TVFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowSyncTask {

    synchronized public static void syncTvShow(Context context) {
        AppDatabase db = AppDatabase.getsInstance(context);
        List<Detail> details = db.detailDao().nLoadAllDetails();

        if (details != null) {
            for (Detail detail : details) {
                TVFactory.create().syncDetail(detail.getId(), BuildConfig.API_KEY)
                        .enqueue(new Callback<Detail>() {
                            @Override
                            public void onResponse(@NonNull Call<Detail> call, @NonNull Response<Detail> response) {
                                AppExecutors.getInstance().getDiskIO().execute(() ->
                                        db.detailDao().insert(response.body()));
                            }

                            @Override
                            public void onFailure(@NonNull Call<Detail> call, @NonNull Throwable t) {

                            }
                        });
            }
        }
    }
}
