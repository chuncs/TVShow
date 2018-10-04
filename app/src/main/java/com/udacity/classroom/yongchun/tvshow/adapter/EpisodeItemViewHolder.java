package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.databinding.ItemEpisodeBinding;
import com.udacity.classroom.yongchun.tvshow.model.Episode;

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
    }
}
