package com.udacity.classroom.yongchun.tvshow.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.udacity.classroom.yongchun.tvshow.database.AppDatabase;
import com.udacity.classroom.yongchun.tvshow.database.DetailDao;
import com.udacity.classroom.yongchun.tvshow.helper.AppExecutors;
import com.udacity.classroom.yongchun.tvshow.model.Detail;

import java.util.List;

public class DatabaseRepository {

    private DetailDao mDetailDao;
    private LiveData<List<Detail>> mAllDetails;

    public DatabaseRepository(Application application) {
        AppDatabase db = AppDatabase.getsInstance(application);
        mDetailDao = db.detailDao();
        mAllDetails = mDetailDao.loadAllDetails();
    }

    public LiveData<List<Detail>> getAllDetails() {
        return mAllDetails;
    }

    public LiveData<Detail> getDetailById(String tvId) {
        return mDetailDao.loadDetailById(tvId);
    }

    public void insert(Detail detail) {
        AppExecutors.getInstance().getDiskIO().execute(() -> mDetailDao.insert(detail));
    }

    public void delete(String tvId) {
        AppExecutors.getInstance().getDiskIO().execute(() -> mDetailDao.delete(tvId));
    }
}
