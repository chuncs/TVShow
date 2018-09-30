package com.udacity.classroom.yongchun.tvshow.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class FeedDataFactory extends DataSource.Factory {

    private MutableLiveData<FeedDataSource> mutableLiveData;
    private String mQuery;

    public FeedDataFactory(String query) {
        this.mQuery = query;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        FeedDataSource feedDataSource = new FeedDataSource(mQuery);
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }

    public MutableLiveData<FeedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
