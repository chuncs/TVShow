package com.udacity.classroom.yongchun.tvshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.udacity.classroom.yongchun.tvshow.databinding.ItemSimilarBinding;
import com.udacity.classroom.yongchun.tvshow.model.Popular;

public class SimilarItemViewHolder extends RecyclerView.ViewHolder {

    private ItemSimilarBinding mBinding;
    private Context mContext;

    public SimilarItemViewHolder(Context context, ItemSimilarBinding binding) {
        super(binding.getRoot());
        mContext = context;
        mBinding = binding;
    }

    public void bindView(Popular similar) {
        mBinding.setSimilar(similar);
    }
}
