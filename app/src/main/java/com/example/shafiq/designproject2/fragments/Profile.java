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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.activities.BookingDetailActivity;
import com.example.shafiq.designproject2.activities.OwnerDetailsActivity;

public class Profile extends Fragment {
    ImageView imageViewBenner, profileImageOwner;
    TextView ownerNameTextView;
    Button buttonRequestBooking;
    private String ownerFullName;
    private int ownerProfile;
    String ownerFirstName,requestWithOwnerFirstName;
    Bundle myDataBundle;
    OwnerDetailsActivity ownerDetailsActivity;
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View viewOwnerProfile = inflater.inflate(R.layout.profile, container, false);
        imageViewBenner = viewOwnerProfile.findViewById(R.id.imageViewBennerID);
        ownerNameTextView = viewOwnerProfile.findViewById(R.id.textViewOwnerFullNameID);
        imageViewBenner.setScaleType(ImageView.ScaleType.FIT_XY);
        profileImageOwner = viewOwnerProfile.findViewById(R.id.imageViewOwnerProfileID);
        buttonRequestBooking = viewOwnerProfile.findViewById(R.id.buttonRequestBookingID);
        ownerDetailsActivity = (OwnerDetailsActivity) getActivity();
        assert ownerDetailsActivity != null;

        myDataBundle = ownerDetailsActivity.getMyData();
        if (myDataBundle != null) {
            ownerFullName = myDataBundle.getString("OwnerFullName");
            ownerProfile = myDataBundle.getInt("OwnerImage");
            ownerFirstName = myDataBundle.getString("FirstName");
            requestWithOwnerFirstName = "Request Booking with " + ownerFirstName;
            ownerNameTextView.setText(ownerFullName);
            profileImageOwner.setImageResource(ownerProfile);
            buttonRequestBooking.setText(requestWithOwnerFirstName);
        } else {
            ownerFullName = "";
            ownerProfile = Integer.parseInt("");
            ownerFirstName = "";
        }

        buttonRequestBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookingDetailActivity.class);
                intent.putExtra("OwnerImage", ownerProfile);
                intent.putExtra("OwnerFullName", ownerFullName);
                intent.putExtra("OwnerFirstName",ownerFirstName);
                startActivity(intent);
            }
        });

        return viewOwnerProfile;
    }
}
