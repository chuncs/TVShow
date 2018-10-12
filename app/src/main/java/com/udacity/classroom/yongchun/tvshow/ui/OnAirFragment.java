package com.udacity.classroom.yongchun.tvshow.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.FeedListAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.FragmentOnAirBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.OnAirViewModel;

public class OnAirFragment extends Fragment {
    
    public OnAirFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentOnAirBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_on_air, container, false);
        OnAirViewModel viewModel = ViewModelProviders.of(this).get(OnAirViewModel.class);
        FeedListAdapter adapter = new FeedListAdapter(getContext());

        viewModel.getOnAirLiveData().observe(this, adapter::submitList);
        viewModel.getNetworkState().observe(this, adapter::setNetworkState);

        binding.listFeedOnAir.setAdapter(adapter);

        return binding.getRoot();
    }

}
