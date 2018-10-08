package com.udacity.classroom.yongchun.tvshow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.RecyclerAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.ActivityEpisodeBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.EpisodeViewModel;

public class EpisodeActivity extends AppCompatActivity {

    private ActivityEpisodeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_episode);
        mBinding.toolbarEpisode.setNavigationOnClickListener(view -> onBackPressed());

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String tvId = sharedPref.getString(getString(R.string.CURRENT_TV_ID), null);
        String seasonNumber = sharedPref.getString(getString(R.string.CURRENT_SEASON), null);
        String episodeNumber = sharedPref.getString(getString(R.string.CURRENT_EPISODE), null);

        EpisodeViewModel episodeViewModel = ViewModelProviders.of(this).get(EpisodeViewModel.class);

        episodeViewModel.init(tvId, seasonNumber, episodeNumber);
        episodeViewModel.getEpisode().observe(this, episode -> {
            if (episode != null) {
                mBinding.setEpisode(episode);
                RecyclerAdapter guestAdapter = new RecyclerAdapter(this);
                guestAdapter.setActors(episode.getGuestStars());
                mBinding.listGuest.setAdapter(guestAdapter);
            }
        });
    }
}
