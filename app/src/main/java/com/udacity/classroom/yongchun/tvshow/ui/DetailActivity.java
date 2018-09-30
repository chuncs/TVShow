package com.udacity.classroom.yongchun.tvshow.ui;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ActivityDetailBinding;
import com.udacity.classroom.yongchun.tvshow.repository.DetailRepository;
import com.udacity.classroom.yongchun.tvshow.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding mBinding;

    public static final String CURRENT_TV_ID = "current_tv_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), 0);
        String tvId = sharedPref.getString(CURRENT_TV_ID, null);

        DetailViewModel detailViewModel = new DetailViewModel(new DetailRepository());
        detailViewModel.init(tvId);
        detailViewModel.getDetail().observe(this, detail ->
                mBinding.setDetail(detail));
    }
}
