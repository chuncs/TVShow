package com.udacity.classroom.yongchun.tvshow.repository;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.LiveData;
import android.content.ComponentName;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.database.AppDatabase;
import com.udacity.classroom.yongchun.tvshow.database.DetailDao;
import com.udacity.classroom.yongchun.tvshow.helper.AppExecutors;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.widget.TvShowWidgetProvider;

import java.util.List;

public class DatabaseRepository {

    private AppWidgetManager mAppWidgetManager;
    private ComponentName mWidget;
    private DetailDao mDetailDao;
    private LiveData<List<Detail>> mAllDetails;

    public DatabaseRepository(Application application) {
        AppDatabase db = AppDatabase.getsInstance(application);
        mDetailDao = db.detailDao();
        mAllDetails = mDetailDao.loadAllDetails();
        mAppWidgetManager = AppWidgetManager.getInstance(application);
        mWidget = new ComponentName(application, TvShowWidgetProvider.class);
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

    public void widgetChange() {
        AppExecutors.getInstance().getDiskIO().execute(() -> {
            int[] appWidgetIds = mAppWidgetManager.getAppWidgetIds(mWidget);
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_listView);
        });
    }
}
