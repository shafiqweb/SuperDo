package com.example.shafiq.designproject2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.shafiq.designproject2.fragments.CompletedFragment;
import com.example.shafiq.designproject2.fragments.CurrentFragment;
import com.example.shafiq.designproject2.fragments.PendingFragment;

public class AdapterCurrentPendingCompleted extends FragmentPagerAdapter {
    private String fragments[] = {"Current", "Pending", "Completed"};

    public AdapterCurrentPendingCompleted(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Log.d("CPC", "Fragment" + position);

        switch (position) {
            case 0:
                return new CurrentFragment();
            case 1:
                return new PendingFragment();
            case 2:
                return new CompletedFragment();
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
