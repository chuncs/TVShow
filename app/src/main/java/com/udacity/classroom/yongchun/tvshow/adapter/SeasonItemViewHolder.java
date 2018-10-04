package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemSeasonBinding;
import com.udacity.classroom.yongchun.tvshow.model.Season;
import com.udacity.classroom.yongchun.tvshow.ui.SeasonActivity;

public class SeasonItemViewHolder extends RecyclerView.ViewHolder {

    private ItemSeasonBinding mBinding;
    private Context mContext;

    public SeasonItemViewHolder(Context context, ItemSeasonBinding binding) {
        super(binding.getRoot());
        mContext = context;
        mBinding = binding;
    }

    public void bindView(Season season) {
        mBinding.setSeason(season);
        mBinding.getRoot().setOnClickListener(view -> {
            SharedPreferences.Editor sharedPref = mContext
                    .getSharedPreferences(mContext.getString(R.string.preference_file_key), 0)
                    .edit();
            sharedPref.putString(mContext.getString(R.string.CURRENT_SEASON), season.getSeasonNumber());
            sharedPref.apply();

            Intent intent = new Intent(mContext, SeasonActivity.class);
            mContext.startActivity(intent);
        });
    }
}
