package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.BR;
import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemFeedBinding;
import com.udacity.classroom.yongchun.tvshow.model.Popular;
import com.udacity.classroom.yongchun.tvshow.ui.DetailActivity;

public class PopularItemViewHolder extends RecyclerView.ViewHolder {

    private ItemFeedBinding mBinding;
    private Context mContext;

    PopularItemViewHolder(Context context, ItemFeedBinding binding) {
        super(binding.getRoot());
        mContext = context;
        mBinding = binding;
    }

    public void bindView(Popular popular) {
        mBinding.setVariable(BR.popular, popular);
        mBinding.executePendingBindings();
        mBinding.getRoot().setOnClickListener(view -> {
            SharedPreferences.Editor sharedPref = mContext
                    .getSharedPreferences(mContext.getString(R.string.preference_file_key), 0)
                    .edit();
            sharedPref.putString(mContext.getString(R.string.CURRENT_TV_ID), String.valueOf(popular.getId()));
            sharedPref.apply();

            Intent intent = new Intent(mContext, DetailActivity.class);
            mContext.startActivity(intent);
        });
    }
}
