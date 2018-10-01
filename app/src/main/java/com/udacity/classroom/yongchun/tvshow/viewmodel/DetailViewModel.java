package com.udacity.classroom.yongchun.tvshow.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.repository.DetailRepository;

public class DetailViewModel extends ViewModel {

    private LiveData<Detail> mDetail;

    public void init(String tvId) {
        mDetail = DetailRepository.getDetail(tvId);
    }

    public LiveData<Detail> getDetail() {
        return mDetail;
    }
}
