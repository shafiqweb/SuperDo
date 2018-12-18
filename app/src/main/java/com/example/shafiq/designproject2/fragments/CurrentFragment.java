package com.example.shafiq.designproject2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.adapter.AdapterSortingTimeService;

public class CurrentFragment extends Fragment {
    ViewPager viewPagerSorting;
    TabLayout tabLayoutSorting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.current_fragment, container, false);
        viewPagerSorting = view.findViewById(R.id.viewPagerSortingID);
        tabLayoutSorting = view.findViewById(R.id.sortingTabs);

        AdapterSortingTimeService adapterSortingTimeService = new AdapterSortingTimeService(getChildFragmentManager());
        viewPagerSorting.setAdapter(adapterSortingTimeService);
        tabLayoutSorting.setupWithViewPager(viewPagerSorting);

        return view;
    }
}
