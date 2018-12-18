package com.example.shafiq.designproject2.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.fragments.HomeServiceProvider;
import com.example.shafiq.designproject2.fragments.OutdoorServiceProvider;

public class BookingManagerActivity extends AppCompatActivity {
    DrawerLayout drawerLayoutBookingManager;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter sectionsPagerAdapterHomeOutdoor;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager viewPagerHomeOutdoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_manager);
        drawerLayoutBookingManager = findViewById(R.id.drawer_layout_booking_managerID);
        NavigationView navigationViewBookingManager = findViewById(R.id.nav_view_booking_managerID);
        View headerView = navigationViewBookingManager.getHeaderView(0);
        ImageView imageViewHeaderProfile = headerView.findViewById(R.id.nav_imageViewProviderProfileID);
        imageViewHeaderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookingManagerActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookingManagerActivity.this, ViewProfileActivity.class);
                startActivity(intent);
            }
        });
        navigationViewBookingManager.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(false);
                drawerLayoutBookingManager.closeDrawers();
                if (item.getItemId() == R.id.nav_item_bookingManagerID) {
                    Intent intent = new Intent(BookingManagerActivity.this, BookingActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_item_availabilityID) {
                    Intent intent = new Intent(BookingManagerActivity.this, BookingActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_item_locationID) {
                    Intent intent = new Intent(BookingManagerActivity.this, SearchLocationMapActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_item_serviceID) {
                    Intent intent = new Intent(BookingManagerActivity.this, ServicesActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_item_bankAccountID) {
                    Intent intent = new Intent(BookingManagerActivity.this, BankAccountActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_item_walletID) {
                    Intent intent = new Intent(BookingManagerActivity.this, WalletActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_item_signOutID) {
                    Intent intent = new Intent(BookingManagerActivity.this, BookingManagerActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sectionsPagerAdapterHomeOutdoor = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPagerHomeOutdoor = findViewById(R.id.viewPagerHomeOutdoorID);
        viewPagerHomeOutdoor.setAdapter(sectionsPagerAdapterHomeOutdoor);
        TabLayout tabLayoutHomeOutdoor = findViewById(R.id.booking_manager_activityTabs);
        viewPagerHomeOutdoor.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutHomeOutdoor));
        tabLayoutHomeOutdoor.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerHomeOutdoor));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_booking_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        if (id == R.id.action_settings_booking_manager_navigationID) {
            Toast.makeText(this, "Drawer open", Toast.LENGTH_SHORT).show();
            drawerLayoutBookingManager.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("HomeOutdoor", "Fragment" + position);
            switch (position) {
                case 0:
                    return new HomeServiceProvider();
                case 1:
                    return new OutdoorServiceProvider();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
