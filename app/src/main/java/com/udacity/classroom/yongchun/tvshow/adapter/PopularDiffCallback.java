package com.udacity.classroom.yongchun.tvshow.adapter;

import android.support.v7.util.DiffUtil;

import com.udacity.classroom.yongchun.tvshow.model.Popular;

public class PopularDiffCallback extends DiffUtil.ItemCallback<Popular> {

    @Override
    public boolean areItemsTheSame(Popular oldItem, Popular newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(Popular oldItem, Popular newItem) {
        return oldItem == newItem;
    }
}
