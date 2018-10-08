package com.udacity.classroom.yongchun.tvshow.service;

import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.model.Episode;
import com.udacity.classroom.yongchun.tvshow.model.Feed;
import com.udacity.classroom.yongchun.tvshow.model.Season;

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

    @GET("3/tv/{tv_id}/season/{season_number}")
    Call<Season> fetchSeason(@Path("tv_id") String tvId,
                             @Path("season_number") String seasonNumber,
                             @Query("api_key") String apiKey);

    @GET("3/tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    Call<Episode> fetchEpisode(@Path("tv_id") String tvId,
                               @Path("season_number") String seasonNumber,
                               @Path("episode_number") String episodeNumber,
                               @Query("api_key") String apiKey);
}
