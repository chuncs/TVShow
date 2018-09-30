package com.udacity.classroom.yongchun.tvshow.utilities;

import android.text.TextUtils;

import com.udacity.classroom.yongchun.tvshow.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String convertGenres(List<Genre> genres) {
        List<String> temp = new ArrayList<>();

        if (genres == null) {
            return null;
        }

        for (Genre genre : genres) {
            temp.add(genre.getName());
        }

        return TextUtils.join(", ", temp);
    }
}
