package com.udacity.classroom.yongchun.tvshow.adapter;

import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.databinding.ItemActorBinding;
import com.udacity.classroom.yongchun.tvshow.model.Cast;

public class ActorItemViewHolder extends RecyclerView.ViewHolder {

    private ItemActorBinding mBinding;

    public ActorItemViewHolder(ItemActorBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bindView(Cast cast) {
        mBinding.setActor(cast);
    }
}
