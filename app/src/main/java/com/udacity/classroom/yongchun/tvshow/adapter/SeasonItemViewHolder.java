package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.databinding.ItemSeasonBinding;
import com.udacity.classroom.yongchun.tvshow.model.Season;

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
    }
}
