package com.udacity.classroom.yongchun.tvshow.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.squareup.picasso.Picasso;
import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.database.AppDatabase;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.model.Episode;
import com.udacity.classroom.yongchun.tvshow.utilities.NetworkUtils;

import java.io.IOException;
import java.util.List;

public class TvShowRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private Context mContext;
    private AppDatabase mDb;
    private List<Detail> mDetails;

    public TvShowRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mDb = AppDatabase.getsInstance(mContext);
    }

    @Override
    public void onDataSetChanged() {
        mDetails = mDb.detailDao().nLoadAllDetails();
    }

    @Override
    public void onDestroy() {
        if (mDetails != null) {
            mDetails.clear();
        }
    }

    @Override
    public int getCount() {
        return mDetails != null ? mDetails.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);

        if (mDetails != null) {
            Detail detail = mDetails.get(position);
            String posterPath;

            //Widget title
            views.setTextViewText(R.id.widget_name, detail.getName());

            //Widget poster
            if (TextUtils.isEmpty(detail.getPoster_path())) {
                posterPath = null;
            } else {
                posterPath = NetworkUtils.buildPosterUri(detail.getPoster_path()).toString();
            }

            try {
                Bitmap bitmap = Picasso.get()
                        .load(posterPath)
                        .get();
                if (bitmap != null) {
                    views.setImageViewBitmap(R.id.widget_poster, bitmap);
                } else {
                    views.setImageViewResource(R.id.widget_poster, R.drawable.placeholder);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Widget last episode air date
            if (detail.getLast_episode_to_air() != null) {
                Episode lastEpisode = detail.getLast_episode_to_air();
                String lastDate = mContext.getString(R.string.last_episode, lastEpisode.getSeasonNumber(),
                        lastEpisode.getEpisodeNumber(), lastEpisode.getAirDate());
                views.setTextViewText(R.id.widget_last, lastDate);
            } else {
                views.setTextViewText(R.id.widget_last, mContext.getString(R.string.last_unknown));
            }

            //Widget next episode air date
            if (detail.getNext_episode_to_air() != null) {
                Episode nextEpisode = detail.getNext_episode_to_air();
                String nextDate = mContext.getString(R.string.next_episode, nextEpisode.getSeasonNumber(),
                        nextEpisode.getEpisodeNumber(), nextEpisode.getAirDate());
                views.setTextViewText(R.id.widget_next, nextDate);
            } else {
                views.setTextViewText(R.id.widget_next, mContext.getString(R.string.next_unknown));
            }

            //Widget click event
            Bundle extras = new Bundle();
            extras.putString(TvShowWidgetProvider.EXTRA_ITEM, mDetails.get(position).getId());
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            views.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
        }

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
