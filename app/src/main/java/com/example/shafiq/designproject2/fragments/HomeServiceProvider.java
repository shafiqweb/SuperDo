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
import com.example.shafiq.designproject2.adapter.AdapterCurrentPendingCompleted;

public class HomeServiceProvider extends Fragment {
    ViewPager viewPagerCurrentPendingCompleted;
    TabLayout tabLayoutCurrentPendingCompleted;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_service_provider,container,false);
        viewPagerCurrentPendingCompleted = view.findViewById(R.id.viewPagerCurrentPendingCompletedID);
        tabLayoutCurrentPendingCompleted = view.findViewById(R.id.current_pending_completedTabs);

        AdapterCurrentPendingCompleted adapterCurrentPendingCompleted = new AdapterCurrentPendingCompleted(getChildFragmentManager());
        viewPagerCurrentPendingCompleted.setAdapter(adapterCurrentPendingCompleted);
        tabLayoutCurrentPendingCompleted.setupWithViewPager(viewPagerCurrentPendingCompleted);
        return view;
    }
}
