package com.example.shafiq.designproject2.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.shafiq.designproject2.adapter.CustomAdapter;
import com.example.shafiq.designproject2.model.Image;
import com.example.shafiq.designproject2.R;

import java.util.ArrayList;

public class ServiceCategoryActivity extends AppCompatActivity {
    DrawerLayout drawerLayoutServiceCategory;
    ListView listView;
    CustomAdapter customAdapter;
    ArrayList<Image> imageModelArrayList;
    private int[] drawableImageList = new int[]{R.drawable.hair, R.drawable.nails, R.drawable.massage, R.drawable.spraytan,
            R.drawable.makeup, R.drawable.facial, R.drawable.hairremoval, R.drawable.nutrition,
            R.drawable.injection, R.drawable.coaching,
            R.drawable.styling, R.drawable.influencer};
    private String[] imageNameList = new String[]{"1.Hair", "2.Nails", "3.Massage", "4.Spray-Tan", "5.Make-Up", "6.Facial",
            "7.Hair-Removal", "8.Nutrition", "9.Injection", "10.Coaching", "11.Styling",
            "12.Influencer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category);
        drawerLayoutServiceCategory = findViewById(R.id.drawer_layout_service_categoryID);
        NavigationView navigationViewServiceCategory = findViewById(R.id.nav_view_service_categoryID);
        View headerView = navigationViewServiceCategory.getHeaderView(0);
        ImageView imageViewHeaderProfile = headerView.findViewById(R.id.nav_imageViewOwnerProfileID);
        imageViewHeaderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceCategoryActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceCategoryActivity.this, ViewProfileActivity.class);
                startActivity(intent);
            }
        });
        navigationViewServiceCategory.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // set item as selected to persist highlight
                item.setChecked(false);
                // close drawer when item is tapped
                drawerLayoutServiceCategory.closeDrawers();
                if (item.getItemId() == R.id.nav_bookingID) {
                    Intent intent = new Intent(ServiceCategoryActivity.this, BookingActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_payment_methodID) {
                    Intent intent = new Intent(ServiceCategoryActivity.this, PaymentMethodsActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.nav_favoriteID){
                    Intent intent = new Intent(ServiceCategoryActivity.this, FavouriteActivity.class);
                    startActivity(intent);
                }

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                return true;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar_service_categoryID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        listView = findViewById(R.id.serviceListviewID);
        imageModelArrayList = populateList();
        customAdapter = new CustomAdapter(this, imageModelArrayList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ServiceCategoryActivity.this, SpecificServiceActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private ArrayList<Image> populateList() {
        ArrayList<Image> list = new ArrayList<Image>();
        for (int i = 0; i < imageNameList.length; i++) {
            Image imageModel = new Image();
            imageModel.setName(imageNameList[i]);
            imageModel.setImage_drawable(drawableImageList[i]);
            list.add(imageModel);
        }
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionSettingServiceCategoryNavigationID) {
            Toast.makeText(this, "Action Clicked", Toast.LENGTH_SHORT).show();
            //Drawer left to right or vice-versa
            drawerLayoutServiceCategory.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
