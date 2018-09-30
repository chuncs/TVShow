package com.udacity.classroom.yongchun.tvshow.model;

import java.util.List;

public class Detail {

    private String backdrop_path;
    private String poster_path;
    private String name;
    private String overview;
    private String vote_average;
    private List<Season> seasons;
    private Credit credits;
    private Similar similar;
    private NextEpisode next_episode_to_air;
    private List<Genre> genres;

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getVoteAverage() {
        return vote_average;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public Credit getCredits() {
        return credits;
    }

    public Similar getSimilar() {
        return similar;
    }

    public NextEpisode getNextEpisode() {
        return next_episode_to_air;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
