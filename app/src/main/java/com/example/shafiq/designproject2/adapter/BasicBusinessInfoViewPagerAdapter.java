package com.example.shafiq.designproject2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shafiq.designproject2.fragments.BasicInfo;
import com.example.shafiq.designproject2.fragments.BusinessInfo;

public class BasicBusinessInfoViewPagerAdapter extends FragmentPagerAdapter{
    private String fragments[] = {"BASIC INFO","BUSINESS INFO"};

    public BasicBusinessInfoViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BasicInfo();
            case 1:
                return new BusinessInfo();
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
