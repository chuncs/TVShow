package com.udacity.classroom.yongchun.tvshow.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.FeedListAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.FragmentOnAirBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.FeedViewModel;

public class OnAirFragment extends Fragment {

    private static final String ON_AIR_PATH = "on_the_air";

    public OnAirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentOnAirBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_on_air, container, false);
        FeedViewModel viewModel = new FeedViewModel(ON_AIR_PATH);
        FeedListAdapter adapter = new FeedListAdapter(getContext());

        viewModel.getPopularLiveData().observe(this, adapter::submitList);
        viewModel.getNetworkState().observe(this, adapter::setNetworkState);

        binding.listFeedOnAir.setAdapter(adapter);

        return binding.getRoot();
    }

}
