package com.example.shafiq.designproject2.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafiq.designproject2.fragments.Pricing;
import com.example.shafiq.designproject2.fragments.Profile;
import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.fragments.Reviews;

public class OwnerDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle;
    ImageView imageViewBackButton;
    TextView nameTextView;
    private String ownerFullName, ownerFirstName;
    private int ownerProfileImage;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter ownerDetailSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager viewPagerOwnerDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        Toolbar toolbar = findViewById(R.id.toolbarOwnerDetailsID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        nameTextView = findViewById(R.id.textViewOwnerNickNameID);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            ownerFullName = bundle.getString("OwnerFullName");
            ownerProfileImage = bundle.getInt("OwnerProfileImage");
            ownerFirstName = bundle.getString("FirstName");
        }
        nameTextView.setText(ownerFirstName);
        imageViewBackButton = findViewById(R.id.back_button_ownerDetailsID);
        ownerDetailSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPagerOwnerDetails = findViewById(R.id.containerOwnerDetailsID);
        viewPagerOwnerDetails.setAdapter(ownerDetailSectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.ownerDetailsTabLayout);
        viewPagerOwnerDetails.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerOwnerDetails));
        imageViewBackButton.setOnClickListener(this);
    }
    public Bundle getMyData() {
        Bundle myData = new Bundle();
        myData.putString("OwnerFullName", ownerFullName);
        myData.putInt("OwnerImage", ownerProfileImage);
        myData.putString("FirstName", ownerFirstName);
        return myData;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_owner_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_owner_details_navigateID) {
            Toast.makeText(this,"Action Clicked",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        super.onBackPressed();
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
            switch (position) {
                case 0:
                    //Profile profile = new Profile();
                    return new Profile();
                case 1:
                    //Pricing pricing = new Pricing();
                    return new Pricing();
                case 2:
                    //Reviews reviews = new Reviews();
                    return new Reviews();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
