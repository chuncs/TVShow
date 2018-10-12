package com.udacity.classroom.yongchun.tvshow.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class TvShowWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TvShowRemoteViewsFactory(getApplicationContext());
    }
}
