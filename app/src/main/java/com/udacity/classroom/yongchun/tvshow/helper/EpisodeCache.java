package com.udacity.classroom.yongchun.tvshow.helper;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

import com.udacity.classroom.yongchun.tvshow.model.Episode;

import java.util.HashMap;
import java.util.Map;

public class EpisodeCache {

    private static EpisodeCache sInstance;
    private Map<String, LiveData<Episode>> mCache;

    private EpisodeCache() {
        mCache = new HashMap<>();
    }

    public synchronized static EpisodeCache init() {
        if (sInstance == null) {
            sInstance = new EpisodeCache();
        }
        return sInstance;
    }

    public LiveData<Episode> get(String episodeNumber) {
        if (mCache.containsKey(episodeNumber)) {
            return mCache.get(episodeNumber);
        }
        return null;
    }

    public void put(String episodeNumber, LiveData<Episode> data) {
        if (TextUtils.isEmpty(episodeNumber)) {
            return;
        }
        mCache.put(episodeNumber, data);
    }

    public void clear(String episodeNumber) {
        if (mCache.containsKey(episodeNumber)) {
            mCache.remove(episodeNumber);
        }
    }
}
