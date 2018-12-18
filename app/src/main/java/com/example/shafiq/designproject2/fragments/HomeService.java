package com.example.shafiq.designproject2.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.adapter.MyViewPagerAdapter;

public class HomeService extends Fragment implements View.OnClickListener{
    Button buttonPopup;
    PopupMenu popupMenu;
    ViewPager viewPagerDistanceRating;
    TabLayout tabLayoutDistanceRating;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_service, container, false);
        buttonPopup = view.findViewById(R.id.buttonPopupID);
        viewPagerDistanceRating = view.findViewById(R.id.viewPagerDistanceRatingID);
        tabLayoutDistanceRating = view.findViewById(R.id.distance_ratingsTabs);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerDistanceRating.setAdapter(viewPagerAdapter);
        tabLayoutDistanceRating.setupWithViewPager(viewPagerDistanceRating);
        buttonPopup.setOnClickListener(this);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPopupID){
           popupMenu = new PopupMenu(getActivity(),buttonPopup);
           //popupMenu.setGravity(Gravity.END);//call requires API level 23,so red marks
           popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
           popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
               @Override
               public boolean onMenuItemClick(MenuItem item) {
                   String location = item.getTitle().toString();
                   buttonPopup.setText(location);
                   return true;
               }
           });
           popupMenu.show();
        }

    }
}
