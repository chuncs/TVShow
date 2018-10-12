package com.udacity.classroom.yongchun.tvshow.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.udacity.classroom.yongchun.tvshow.datasource.FeedDataFactory;
import com.udacity.classroom.yongchun.tvshow.datasource.FeedDataSource;
import com.udacity.classroom.yongchun.tvshow.model.NetworkState;
import com.udacity.classroom.yongchun.tvshow.model.Popular;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OnAirViewModel extends ViewModel {

    private static final String ON_AIR_PATH = "on_the_air";
    private LiveData<NetworkState> mNetworkState;
    private LiveData<PagedList<Popular>> mOnAirLiveData;

    public OnAirViewModel() {
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);

        FeedDataFactory feedDataFactory = new FeedDataFactory(ON_AIR_PATH);
        mNetworkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
                (Function<FeedDataSource, LiveData<NetworkState>>) FeedDataSource::getNetworkState);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .build();

        //noinspection unchecked
        mOnAirLiveData = new LivePagedListBuilder(feedDataFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<NetworkState> getNetworkState() {
        return mNetworkState;
    }

    public LiveData<PagedList<Popular>> getOnAirLiveData() {
        return mOnAirLiveData;
    }
}
