package com.udacity.classroom.yongchun.tvshow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.RecyclerAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.ActivitySeasonBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.SeasonViewModel;

public class SeasonActivity extends AppCompatActivity {

    private ActivitySeasonBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_season);
        mBinding.toolbarSeason.setNavigationOnClickListener(view -> onBackPressed());

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String tvId = sharedPref.getString(getString(R.string.CURRENT_TV_ID), null);
        String seasonNumber = sharedPref.getString(getString(R.string.CURRENT_SEASON), null);

        SeasonViewModel seasonViewModel = ViewModelProviders.of(this).get(SeasonViewModel.class);

        seasonViewModel.init(tvId, seasonNumber);
        seasonViewModel.getSeason().observe(this, season -> {
            if (season != null) {
                mBinding.setSeason(season);
                RecyclerAdapter episodeAdapter = new RecyclerAdapter(this);
                episodeAdapter.setEpisodes(season.getEpisodes());
                mBinding.listEpisode.setAdapter(episodeAdapter);
            }
        });
    }
}
