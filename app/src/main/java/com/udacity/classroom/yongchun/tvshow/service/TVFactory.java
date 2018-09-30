package com.udacity.classroom.yongchun.tvshow.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TVFactory {

    private static final Object LOCK = new Object();
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static TVService sInstance;

    public static TVService create() {
        if (sInstance == null) {
            synchronized (LOCK) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                sInstance = retrofit.create(TVService.class);
            }
        }

        return sInstance;
    }
}
