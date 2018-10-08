package com.udacity.classroom.yongchun.tvshow.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udacity.classroom.yongchun.tvshow.model.Episode;

import java.lang.reflect.Type;
import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Episode fromEpisodeJson(String json) {
        Type type = new TypeToken<Episode>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    @TypeConverter
    public static String fromEpisode(Episode episode) {
        return new Gson().toJson(episode);
    }
}
