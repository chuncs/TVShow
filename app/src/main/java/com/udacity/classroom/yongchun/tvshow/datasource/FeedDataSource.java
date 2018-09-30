package com.udacity.classroom.yongchun.tvshow.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.udacity.classroom.yongchun.tvshow.BuildConfig;
import com.udacity.classroom.yongchun.tvshow.model.Feed;
import com.udacity.classroom.yongchun.tvshow.model.NetworkState;
import com.udacity.classroom.yongchun.tvshow.model.Popular;
import com.udacity.classroom.yongchun.tvshow.service.TVFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDataSource extends PageKeyedDataSource<Integer, Popular> {

    private MutableLiveData<NetworkState> mNetworkState;
    private String mQuery;

    public FeedDataSource(String query) {
        mNetworkState = new MutableLiveData<>();
        mQuery = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Popular> callback) {
        mNetworkState.postValue(NetworkState.LOADING);

        TVFactory.create().fetchFeed(mQuery, BuildConfig.API_KEY, 1)
                .enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                        if (response.isSuccessful()) {
                            callback.onResult(response.body().getResults(), null, 2);
                            mNetworkState.postValue(NetworkState.LOADED);
                        } else {
                            mNetworkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                        mNetworkState.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Popular> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Popular> callback) {
        mNetworkState.postValue(NetworkState.LOADING);

        TVFactory.create().fetchFeed(mQuery, BuildConfig.API_KEY, params.key)
                .enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(@NonNull Call<Feed> call, @NonNull Response<Feed> response) {
                        if (response.isSuccessful()) {
                            if (params.key > response.body().getTotalPages() || params.key > 1000) {
                                callback.onResult(response.body().getResults(), null);
                            } else {
                                callback.onResult(response.body().getResults(), params.key + 1);
                            }
                            mNetworkState.postValue(NetworkState.LOADED);
                        } else {
                            mNetworkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Feed> call, @NonNull Throwable t) {
                        mNetworkState.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));
                    }
                });
    }

    public MutableLiveData getNetworkState() {
        return mNetworkState;
    }
}
