package com.udacity.classroom.yongchun.tvshow.utilities;

import android.net.Uri;

public class NetworkUtils {

    private static final String BASE_URL = "https://image.tmdb.org/t/p";
    private static final String POSTER_SIZE = "w185";
    private static final String BACK_DROP_SIZE = "w780";

    public static Uri buildPosterUri(String path) {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath(POSTER_SIZE)
                .appendEncodedPath(path)
                .build();
    }

    public static Uri buildBackdropUri(String path) {
        return Uri.parse(BASE_URL).buildUpon()
                .appendPath(BACK_DROP_SIZE)
                .appendEncodedPath(path)
                .build();
    }
}
