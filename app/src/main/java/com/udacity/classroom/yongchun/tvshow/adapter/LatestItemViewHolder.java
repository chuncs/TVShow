package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemLatestBinding;
import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.ui.DetailActivity;

public class LatestItemViewHolder extends RecyclerView.ViewHolder {

    private ItemLatestBinding mBinding;
    private Context mContext;

    public LatestItemViewHolder(Context context, ItemLatestBinding binding) {
        super(binding.getRoot());
        mContext = context;
        mBinding = binding;
    }

    public void bindView(Detail detail) {
        mBinding.setDetail(detail);
        mBinding.getRoot().setOnClickListener(view -> {
            SharedPreferences.Editor sharedPref = mContext
                    .getSharedPreferences(mContext.getString(R.string.preference_file_key), 0)
                    .edit();
            sharedPref.putString(mContext.getString(R.string.CURRENT_TV_ID), detail.getId());
            sharedPref.apply();

            Intent intent = new Intent(mContext, DetailActivity.class);
            mContext.startActivity(intent);
        });
    }
}
