package com.udacity.classroom.yongchun.tvshow.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

@Entity(tableName = "detail_table")
public class Detail {

    @PrimaryKey
    @NonNull
    private String id;
    @Ignore
    private String backdrop_path;
    private String poster_path;
    private String name;
    @Ignore
    private String overview;
    @Ignore
    private String vote_average;
    @Ignore
    private Credit credits;
    @Ignore
    private Similar similar;
    private Date last_air_date;
    private Episode last_episode_to_air;
    private Episode next_episode_to_air;
    @Ignore
    private List<Season> seasons;
    @Ignore
    private List<Genre> genres;

    public Detail(@NonNull String id, String poster_path, String name, Date last_air_date, Episode last_episode_to_air, Episode next_episode_to_air) {
        this.id = id;
        this.poster_path = poster_path;
        this.name = name;
        this.last_air_date = last_air_date;
        this.last_episode_to_air = last_episode_to_air;
        this.next_episode_to_air = next_episode_to_air;
    }

    public String getId() {
        return id;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public String getPoster_path() {
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

    public Credit getCredits() {
        return credits;
    }

    public Date getLast_air_date() {
        return last_air_date;
    }

    public Similar getSimilar() {
        return similar;
    }

    public Episode getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public Episode getNext_episode_to_air() {
        return next_episode_to_air;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
