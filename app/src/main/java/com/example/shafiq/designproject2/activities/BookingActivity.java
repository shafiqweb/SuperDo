package com.example.shafiq.designproject2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shafiq.designproject2.adapter.BookingListCustomAdapter;
import com.example.shafiq.designproject2.model.BookingModel;
import com.example.shafiq.designproject2.R;


public class BookingActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageViewBackButton;
    BookingListCustomAdapter bookingListCustomAdapter;
    ListView listView;
    private String[] bookingSituation = new String[]{"Current Booking", "Pending Booking", "Past Booking"};
    private String[] ownerNameList = new String[]{"Francois Vaccarello", "Josen Vaccarello", "Helen Vaccarello", "Shannu Vaccarello",
            "Vennella Vaccarello", "Xentia Vaccarello", "Mekhshu Vaccarello", "Beron Vaccarello", "Ponting Vaccarello", "Kueish Vaccarello",
            "Famella Vaccarello", "Bushra Vaccarello", "13venella Vaccarello"};
    private String[] beautyCategoryList = new String[]{"1.Hair", "2.Nails", "3.Massage", "4.Spray-Tan", "5.Make-Up", "6.Facial",
            "7.Hair-Removal", "8.Nutrition", "9.Injection", "10.Coaching", "11.Styling",
            "12.Influencer","13.Others"};
    private int[] drawableOwnerImageList = new int[]{R.drawable.owner1, R.drawable.owner12, R.drawable.owner13, R.drawable.owner14,
            R.drawable.owner16, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1, R.drawable.owner1,
            R.drawable.owner1, R.drawable.owner1, R.drawable.owner1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        listView = findViewById(R.id.listviewBookingID);
        imageViewBackButton = findViewById(R.id.back_button_booking_ID);
        imageViewBackButton.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar_bookingID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        bookingListCustomAdapter = new BookingListCustomAdapter(this);
        int y = 0;
        int k = 0;
        bookingListCustomAdapter.addSectionHeaderItem(new BookingModel((bookingSituation[y])));
        y = 1;

        for (int i = 1; i < 14; i++) {
            bookingListCustomAdapter.addItem(new BookingModel(ownerNameList[k], drawableOwnerImageList[k],beautyCategoryList[k]));
            if (i % 5 == 0) {
                bookingListCustomAdapter.addSectionHeaderItem(new BookingModel(bookingSituation[y]));
                y++;
            }
            k++;
        }
        listView.setAdapter(bookingListCustomAdapter);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button_booking_ID) {
            super.onBackPressed();
        }
    }
}
