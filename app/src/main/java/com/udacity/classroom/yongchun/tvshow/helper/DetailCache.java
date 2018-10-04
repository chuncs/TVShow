package com.udacity.classroom.yongchun.tvshow.helper;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

import com.udacity.classroom.yongchun.tvshow.model.Detail;

import java.util.HashMap;
import java.util.Map;

public class DetailCache {

    private static DetailCache sInstance;
    private Map<String, LiveData<Detail>> mCache;

    private DetailCache() {
        mCache = new HashMap<>();
    }

    public synchronized static DetailCache init() {
        if (sInstance == null) {
            sInstance = new DetailCache();
        }
        return sInstance;
    }

    public LiveData<Detail> get(String tvId) {
        if (mCache.containsKey(tvId)) {
            return mCache.get(tvId);
        }
        return null;
    }

    public void put(String tvId, LiveData<Detail> data) {
        if (TextUtils.isEmpty(tvId)) {
            return;
        }
        mCache.put(tvId, data);
    }

    public void clear(String tvId) {
        if (mCache.containsKey(tvId)) {
            mCache.remove(tvId);
        }
    }
}
