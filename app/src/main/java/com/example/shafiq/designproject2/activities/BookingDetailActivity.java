package com.example.shafiq.designproject2.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;

import java.util.Calendar;

public class BookingDetailActivity extends AppCompatActivity implements View.OnClickListener {
    Bundle bundle;
    ImageView imageViewProfile,imageViewBackButton;
    TextView textViewOwnerFullName;
    Button buttonSelectService, buttonSelectAvailableDate, buttonSelectAvailableTime, buttonCancelBooking, buttonConfirmBooking;
    PopupMenu popupMenuSelectService;
    int imageViewProfileOwner;
    String ownerFullName,ownerFirstName, selectedService, date, time;
    EditText editTextEnterAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);
        Toolbar toolbar = findViewById(R.id.toolbarBookingDetailsID);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        imageViewProfile = findViewById(R.id.imageViewBookingProfileImageID);
        textViewOwnerFullName = findViewById(R.id.textViewBookingOwnerFullNameID);
        textViewOwnerFullName.setFocusableInTouchMode(true);
        textViewOwnerFullName.requestFocus();
        imageViewBackButton = findViewById(R.id.back_button_booking_detailsID);
        buttonSelectService = findViewById(R.id.buttonSelectServiceID);
        buttonSelectAvailableDate = findViewById(R.id.buttonAvailableDateBookingDetailsID);
        buttonSelectAvailableTime = findViewById(R.id.buttonAvailableTimeBookingDetailsID);
        editTextEnterAddress = findViewById(R.id.editTextEnterAddressBookingDetailsID);
        buttonCancelBooking = findViewById(R.id.buttonCancelBookingID);
        buttonConfirmBooking = findViewById(R.id.buttonConfirmBookingID);
        imageViewBackButton.setOnClickListener(this);
        buttonSelectService.setOnClickListener(this);
        buttonSelectAvailableDate.setOnClickListener(this);
        buttonSelectAvailableTime.setOnClickListener(this);
        buttonConfirmBooking.setOnClickListener(this);
        buttonCancelBooking.setOnClickListener(this);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            imageViewProfileOwner = bundle.getInt("OwnerImage");
            ownerFullName = bundle.getString("OwnerFullName");
            ownerFirstName = bundle.getString("OwnerFirstName");
            imageViewProfile.setImageResource(imageViewProfileOwner);
            textViewOwnerFullName.setText(ownerFullName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_booking_details, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button_booking_detailsID) {
            super.onBackPressed();
        }
        if (v.getId() == R.id.buttonSelectServiceID) {
            popupMenuSelectService = new PopupMenu(BookingDetailActivity.this, buttonSelectService);
            //popupMenuSelectService.setGravity(Gravity.END);
            popupMenuSelectService.getMenuInflater().inflate(R.menu.menu_popup_select_service, popupMenuSelectService.getMenu());
            popupMenuSelectService.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(BookingDetailActivity.this, "selected service", Toast.LENGTH_SHORT).show();
                    selectedService = item.getTitle().toString();
                    buttonSelectService.setText(selectedService);
                    return true;
                }
            });
            popupMenuSelectService.show();
        }
        if (v.getId() == R.id.buttonAvailableDateBookingDetailsID) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(BookingDetailActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            date = day + "/" + (month + 1) + "/" + year;
                            buttonSelectAvailableDate.setText(date);
                            Toast.makeText(BookingDetailActivity.this, "selected available date", Toast.LENGTH_SHORT).show();
                        }
                    }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
        if (v.getId() == R.id.buttonAvailableTimeBookingDetailsID) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(BookingDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Toast.makeText(BookingDetailActivity.this, "selected available time", Toast.LENGTH_SHORT).show();
                    time = hourOfDay + ":" + minute;
                    buttonSelectAvailableTime.setText(time);
                }
            }, hour, minute, true);
            timePickerDialog.show();

        }
        if (v.getId() == R.id.buttonConfirmBookingID) {
            String enteredAddress = editTextEnterAddress.getText().toString();
            if (selectedService == null || date == null || time == null || enteredAddress.equals("")) {
                Toast.makeText(BookingDetailActivity.this, "Please Select service,date,time & Enter Your Address", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BookingDetailActivity.this, "Confirmed Booking", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookingDetailActivity.this,MapsActivity.class);
                intent.putExtra("OwnerFirstName",ownerFirstName);
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.buttonCancelBookingID) {
            Toast.makeText(BookingDetailActivity.this, "Cancelled Booking", Toast.LENGTH_SHORT).show();
//            buttonSelectService.setText(R.string.select_service);
//            buttonSelectAvailableDate.setText(R.string.select_available_date);
//            buttonSelectAvailableTime.setText(R.string.select_available_time);
//            editTextEnterAddress.setText("");
//            editTextEnterAddress.setHint(R.string.enter_address);
//            startActivity(new Intent(this,OwnerDetailsActivity.class));
            super.onBackPressed();
        }
    }
}
