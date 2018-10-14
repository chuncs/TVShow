package com.udacity.classroom.yongchun.tvshow.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.udacity.classroom.yongchun.tvshow.R;
import com.udacity.classroom.yongchun.tvshow.adapter.ViewPagerAdapter;
import com.udacity.classroom.yongchun.tvshow.databinding.ActivityMainBinding;
import com.udacity.classroom.yongchun.tvshow.sync.TvShowSyncUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MenuItem mPrevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupUI();
        TvShowSyncUtils.initialize(this);
    }

    private void setupUI() {
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_latest:
                    mBinding.viewPager.setCurrentItem(0, false);
                    break;
                case R.id.menu_popular:
                    mBinding.viewPager.setCurrentItem(1, false);
                    break;
                case R.id.menu_on_air:
                    mBinding.viewPager.setCurrentItem(2, false);
                    break;
            }
            return false;
        });

        mBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mPrevMenuItem != null) {
                    mPrevMenuItem.setChecked(false);
                } else {
                    mBinding.bottomNavigation.getMenu().getItem(0).setChecked(false);
                }

                mBinding.bottomNavigation.getMenu().getItem(position).setChecked(true);
                mPrevMenuItem = mBinding.bottomNavigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mBinding.viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        LatestFragment latestFragment = new LatestFragment();
        PopularFragment popularFragment = new PopularFragment();
        OnAirFragment onAirFragment = new OnAirFragment();

        adapter.addFragment(latestFragment);
        adapter.addFragment(popularFragment);
        adapter.addFragment(onAirFragment);

        viewPager.setAdapter(adapter);
    }

}
