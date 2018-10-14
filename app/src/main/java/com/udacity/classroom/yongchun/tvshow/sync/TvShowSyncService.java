package com.udacity.classroom.yongchun.tvshow.sync;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class TvShowSyncService extends JobService {

    private AsyncTask<Void, Void, Void> mFetchTvShowTask;

    @Override
    public boolean onStartJob(JobParameters job) {
        mFetchTvShowTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Context context = getApplicationContext();
                TvShowSyncTask.syncTvShow(context);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(job, false);
            }
        };

        mFetchTvShowTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mFetchTvShowTask != null) {
            mFetchTvShowTask.cancel(true);
        }

        return true;
    }
}
