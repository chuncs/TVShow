package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.model.Cast;
import com.udacity.classroom.yongchun.tvshow.model.Season;
import com.udacity.classroom.yongchun.tvshow.model.Similar;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_SEASON = 0;
    private static final int VIEW_TYPE_ACTOR = 1;
    private static final int VIEW_TYPE_Similar = 2;
    private Context mContext;
    private List<Season> mSeasons;
    private List<Cast> mActors;
    private List<Similar> mSimilars;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
