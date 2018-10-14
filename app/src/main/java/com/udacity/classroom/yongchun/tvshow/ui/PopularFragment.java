package com.udacity.classroom.yongchun.tvshow.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.FeedListAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.FragmentPopularBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.PopularViewModel;

public class PopularFragment extends Fragment {

    private Context mContext;

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPopularBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_popular, container, false);
        PopularViewModel viewModel = ViewModelProviders.of(this).get(PopularViewModel.class);
        FeedListAdapter adapter = new FeedListAdapter(mContext);

        viewModel.getPopularLiveData().observe(this, adapter::submitList);
        viewModel.getNetworkState().observe(this, adapter::setNetworkState);

        binding.listFeedPopular.setAdapter(adapter);

        return binding.getRoot();
    }

}
