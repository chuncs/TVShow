package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.databinding.ItemEpisodeBinding;
import com.udacity.classroom.yongchun.tvshow.model.Episode;
import com.udacity.classroom.yongchun.tvshow.ui.EpisodeActivity;

public class EpisodeItemViewHolder extends RecyclerView.ViewHolder {

    private ItemEpisodeBinding mBinding;
    private Context mContext;

    public EpisodeItemViewHolder(Context context, ItemEpisodeBinding binding) {
        super(binding.getRoot());
        mContext = context;
        mBinding = binding;
    }

    public void bindView(Episode episode) {
        mBinding.setEpisode(episode);
        mBinding.getRoot().setOnClickListener(view -> {
            SharedPreferences.Editor sharedPref = mContext
                    .getSharedPreferences(mContext.getString(R.string.preference_file_key), 0)
                    .edit();
            sharedPref.putString(mContext.getString(R.string.CURRENT_EPISODE), episode.getEpisodeNumber());
            sharedPref.apply();

            Intent intent = new Intent(mContext, EpisodeActivity.class);
            mContext.startActivity(intent);
        });
    }
}
