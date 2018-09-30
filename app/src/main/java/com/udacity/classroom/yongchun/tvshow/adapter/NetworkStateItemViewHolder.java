package com.udacity.classroom.yongchun.tvshow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.udacity.classroom.yongchun.tvshow.databinding.ItemNetworkStateBinding;
import com.udacity.classroom.yongchun.tvshow.model.NetworkState;

public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

    private ItemNetworkStateBinding mBinding;

    NetworkStateItemViewHolder(ItemNetworkStateBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bindView(NetworkState networkState) {
        if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
            mBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            mBinding.progressBar.setVisibility(View.GONE);
        }

        if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
            mBinding.errorMessage.setVisibility(View.VISIBLE);
            mBinding.errorMessage.setText(networkState.getMessage());
        } else {
            mBinding.errorMessage.setVisibility(View.GONE);
        }
    }
}
