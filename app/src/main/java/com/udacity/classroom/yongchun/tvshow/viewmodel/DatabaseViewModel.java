package com.udacity.classroom.yongchun.tvshow.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ToggleButton;

import com.udacity.classroom.yongchun.tvshow.model.Detail;
import com.udacity.classroom.yongchun.tvshow.repository.DatabaseRepository;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {

    private DatabaseRepository mRepository;
    private LiveData<List<Detail>> mAllDetails;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DatabaseRepository(application);
        mAllDetails = mRepository.getAllDetails();
    }

    public LiveData<List<Detail>> getAllDetails() {
        return mAllDetails;
    }

    public LiveData<Detail> getDetailById(String tvId) {
        return mRepository.getDetailById(tvId);
    }

    public void onFollowButtonClick(View view, Detail detail) {
        boolean checked = ((ToggleButton) view).isChecked();

        if (checked) {
            mRepository.insert(detail);
        } else {
            mRepository.delete(detail.getId());
        }
    }
}
