package com.udacity.classroom.yongchun.tvshow.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.RecyclerAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.FragmentLatestBinding;
import com.udacity.classroom.yongchun.tvshow.viewmodel.DatabaseViewModel;

public class LatestFragment extends Fragment {

    public LatestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLatestBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_latest, container, false);
        DatabaseViewModel viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        RecyclerAdapter adapter = new RecyclerAdapter(getContext());

        viewModel.getAllDetails().observe(this, adapter::setLatest);

        binding.listFeedLatest.setAdapter(adapter);

        return binding.getRoot();
    }

}
