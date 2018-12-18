package com.example.shafiq.designproject2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.shafiq.designproject2.fragments.SortByBookingTime;
import com.example.shafiq.designproject2.fragments.SortByService;

public class AdapterSortingTimeService extends FragmentPagerAdapter {
    private String fragments[] = {"Sort by Booking time", "Sort by Service"};

    public AdapterSortingTimeService(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.d("SortingTimeService", "Fragment" + position);

        switch (position) {
            case 0:
                return new SortByBookingTime();
            case 1:
                return new SortByService();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}
