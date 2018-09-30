package com.udacity.classroom.yongchun.tvshow.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.repository.DetailRepository;

public class DetailViewModel extends ViewModel {

    private LiveData<Detail> mDetail;
    private DetailRepository mDetailRepo;

    public DetailViewModel(DetailRepository detailRepo) {
        mDetailRepo = detailRepo;
    }

    public void init(String tvId) {
        if (mDetail != null) {
            return;
        }

        mDetail = mDetailRepo.getDetail(tvId);
    }

    public LiveData<Detail> getDetail() {
        return mDetail;
    }
}
