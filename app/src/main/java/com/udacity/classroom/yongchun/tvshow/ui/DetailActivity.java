package com.udacity.classroom.yongchun.tvshow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.HorizontalAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.ActivityDetailBinding;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String tvId = sharedPref.getString(getString(R.string.CURRENT_TV_ID), null);

        DetailViewModel detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        detailViewModel.init(tvId);
        detailViewModel.getDetail().observe(this, detail -> {
            mBinding.setDetail(detail);
            adapterSetup(detail);
        });
    }

    private void adapterSetup(Detail detail) {
        SnapHelper snapHelper = new LinearSnapHelper();

        //season horizontal recyclerview adapter
        HorizontalAdapter seasonAdapter = new HorizontalAdapter(this);
        snapHelper.attachToRecyclerView(mBinding.listSeason);
        seasonAdapter.setSeasons(detail.getSeasons());
        mBinding.listSeason.setAdapter(seasonAdapter);

        //actor horizontal recyclerview adapter
        HorizontalAdapter actorAdapter = new HorizontalAdapter(this);
//        snapHelper.attachToRecyclerView(mBinding.listActor);
        actorAdapter.setActors(detail.getCredits().getCast());
        mBinding.listActor.setAdapter(actorAdapter);

        //similar horizontal recyclerview adapter
        HorizontalAdapter similarAdapter = new HorizontalAdapter(this);
        similarAdapter.setSimilar(detail.getSimilar().getResults());
        mBinding.listSimilar.setAdapter(similarAdapter);
    }
}
