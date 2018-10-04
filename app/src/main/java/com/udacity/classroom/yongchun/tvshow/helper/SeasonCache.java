package com.udacity.classroom.yongchun.tvshow.helper;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

import com.udacity.classroom.yongchun.tvshow.model.Season;

import java.util.HashMap;
import java.util.Map;

public class SeasonCache {

    private static SeasonCache sInstance;
    private Map<String, LiveData<Season>> mCache;

    private SeasonCache() {
        mCache = new HashMap<>();
    }

    public synchronized static SeasonCache init() {
        if (sInstance == null) {
            sInstance = new SeasonCache();
        }
        return sInstance;
    }

    public LiveData<Season> get(String seasonNumber) {
        if (mCache.containsKey(seasonNumber)) {
            return mCache.get(seasonNumber);
        }
        return null;
    }

    public void put(String seasonNumber, LiveData<Season> data) {
        if (TextUtils.isEmpty(seasonNumber)) {
            return;
        }
        mCache.put(seasonNumber, data);
    }

    public void clear(String seasonNumber) {
        if (mCache.containsKey(seasonNumber)) {
            mCache.remove(seasonNumber);
        }
    }
}
