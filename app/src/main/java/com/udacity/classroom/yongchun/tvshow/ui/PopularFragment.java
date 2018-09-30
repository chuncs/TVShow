package com.udacity.classroom.yongchun.tvshow.ui;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.FeedListAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.FragmentPopularBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.FeedViewModel;

public class PopularFragment extends Fragment {

    private static final String POPULAR_PATH = "popular";

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPopularBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_popular, container, false);
        FeedViewModel viewModel = new FeedViewModel(POPULAR_PATH);
        FeedListAdapter adapter = new FeedListAdapter(getContext());

        viewModel.getPopularLiveData().observe(this, adapter::submitList);
        viewModel.getNetworkState().observe(this, adapter::setNetworkState);

        binding.listFeedPopular.setAdapter(adapter);

        return binding.getRoot();
    }

}
