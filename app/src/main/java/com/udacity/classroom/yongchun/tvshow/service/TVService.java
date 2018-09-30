package com.udacity.classroom.yongchun.tvshow.service;

import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVService {

    @GET("3/tv/{params}")
    Call<Feed> fetchFeed(@Path("params") String params,
                             @Query("api_key") String apiKey,
                             @Query("page") int page);

    @GET("3/tv/{tv_id}")
    Call<Detail> fetchDetail(@Path("tv_id") String id,
                             @Query("api_key") String apiKey,
                             @Query("append_to_response") String appendix);
}
