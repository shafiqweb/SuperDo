package com.example.shafiq.designproject2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.activities.BookingDetailActivity;
import com.example.shafiq.designproject2.activities.OwnerDetailsActivity;

public class Pricing extends Fragment implements View.OnClickListener{
    Button buttonRequestBookingPricing;
    Bundle myDataBundle;
    String ownerFullName;
    int ownerProfile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewPricing =  inflater.inflate(R.layout.pricing, container, false);
        buttonRequestBookingPricing = viewPricing.findViewById(R.id.buttonRequestBookingPricingID);
        OwnerDetailsActivity ownerDetailsActivity = (OwnerDetailsActivity) getActivity();
        assert ownerDetailsActivity != null;
        myDataBundle = ownerDetailsActivity.getMyData();
        ownerFullName = myDataBundle.getString("OwnerFullName");
        ownerProfile = myDataBundle.getInt("OwnerImage");
        String ownerFirstName = myDataBundle.getString("FirstName");
        String requestWithOwnerFirstName = "Request Booking with "+ ownerFirstName;
        buttonRequestBookingPricing.setText(requestWithOwnerFirstName);
        buttonRequestBookingPricing.setOnClickListener(this);
        return viewPricing;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRequestBookingPricingID){
            Intent intent = new Intent(getActivity(), BookingDetailActivity.class);
            intent.putExtra("OwnerImage", ownerProfile);
            intent.putExtra("OwnerFullName", ownerFullName);
            startActivity(intent);
        }
    }
}
