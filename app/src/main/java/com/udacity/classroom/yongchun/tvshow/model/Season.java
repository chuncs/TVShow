package com.udacity.classroom.yongchun.tvshow.model;

import java.util.List;

public class Season {

    private String name;
    private String poster_path;
    private String season_number;
    private List<Episode> episodes;

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public String getSeasonNumber() {
        return season_number;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
