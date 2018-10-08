package com.udacity.classroom.yongchun.tvshow.model;

import java.util.List;

public class Episode {

    private String air_date;
    private String episode_number;
    private String season_number;
    private String name;
    private String overview;
    private String still_path;
    private List<Cast> guest_stars;

    public String getAirDate() {
        return air_date;
    }

    public String getEpisodeNumber() {
        return episode_number;
    }

    public String getSeasonNumber() {
        return season_number;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getStillPath() {
        return still_path;
    }

    public List<Cast> getGuestStars() {
        return guest_stars;
    }
}
