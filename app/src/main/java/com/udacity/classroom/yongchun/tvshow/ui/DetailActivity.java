package com.udacity.classroom.yongchun.tvshow.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.SnapHelper;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.RecyclerAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.ActivityDetailBinding;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.viewmodel.DatabaseViewModel;
import com.udacity.classroom.yongchun.tvshow.viewmodel.DetailViewModel;

import java.util.ArrayDeque;
import java.util.Deque;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding mBinding;
    private SharedPreferences sharedPref;
    private static Deque<String> tvIdStack = new ArrayDeque<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mBinding.toolbar.setNavigationOnClickListener(view -> onBackPressed());

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String tvId = sharedPref.getString(getString(R.string.CURRENT_TV_ID), null);

        if (savedInstanceState == null) {
            tvIdStack.addFirst(tvId);
        }

        DetailViewModel detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        detailViewModel.init(tvId);
        detailViewModel.getDetail().observe(this, detail -> {
            if (detail != null) {
                mBinding.setDetail(detail);
                adapterSetup(detail);
            }
        });

        DatabaseViewModel databaseViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        mBinding.setDbViewModel(databaseViewModel);
        databaseViewModel.getDetailById(tvId).observe(this, detail -> {
            if (detail != null) {
                mBinding.buttonFollow.setChecked(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tvIdStack.removeFirst();
        sharedPref.edit().putString(getString(R.string.CURRENT_TV_ID), tvIdStack.peekFirst()).apply();
    }

    private void adapterSetup(Detail detail) {

        //season horizontal recyclerview adapter
        RecyclerAdapter seasonAdapter = new RecyclerAdapter(this);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mBinding.listSeason);
        seasonAdapter.setSeasons(detail.getSeasons());
        mBinding.listSeason.setAdapter(seasonAdapter);

        //actor horizontal recyclerview adapter
        RecyclerAdapter actorAdapter = new RecyclerAdapter(this);
        actorAdapter.setActors(detail.getCredits().getCast());
        mBinding.listActor.setAdapter(actorAdapter);

        //similar horizontal recyclerview adapter
        RecyclerAdapter similarAdapter = new RecyclerAdapter(this);
        similarAdapter.setSimilar(detail.getSimilar().getResults());
        mBinding.listSimilar.setAdapter(similarAdapter);
    }
}
