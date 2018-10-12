package com.udacity.classroom.yongchun.tvshow.ui;


import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.RecyclerAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.FragmentLatestBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.DatabaseViewModel;
import com.udacity.classroom.yongchun.tvshow.widget.TvShowWidgetProvider;

public class LatestFragment extends Fragment {

    private AppWidgetManager mAppWidgetManager;
    private ComponentName mWidget;
    private Context mContext;

    public LatestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLatestBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_latest, container, false);
        DatabaseViewModel viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        RecyclerAdapter adapter = new RecyclerAdapter(mContext);
        mAppWidgetManager = AppWidgetManager.getInstance(mContext);
        mWidget = new ComponentName(mContext, TvShowWidgetProvider.class);

        viewModel.getAllDetails().observe(this, details -> {
            adapter.setLatest(details);
            int[] appWidgetIds = mAppWidgetManager.getAppWidgetIds(mWidget);
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_listView);
        });

        binding.listFeedLatest.setAdapter(adapter);

        return binding.getRoot();
    }

}
