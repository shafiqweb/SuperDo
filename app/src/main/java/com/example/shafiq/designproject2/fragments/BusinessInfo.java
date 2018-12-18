package com.example.shafiq.designproject2.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.activities.BookingManagerActivity;
import com.example.shafiq.designproject2.activities.ServiceCategoryActivity;

public class BusinessInfo extends Fragment implements View.OnClickListener {
    Button buttonSignUp;
    EditText companyName, address, nid, tin, tradeLicense, companyProfile;
    CheckBox checkBoxAgree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.business_info, container, false);
        companyName = rootview.findViewById(R.id.editTextCompanyNameID);
        address = rootview.findViewById(R.id.editTextAddressID);
        nid = rootview.findViewById(R.id.editTextNidID);
        tin = rootview.findViewById(R.id.editTextTinID);
        tradeLicense = rootview.findViewById(R.id.editTextTradeLicenseID);
        companyProfile = rootview.findViewById(R.id.editTextCompanyProfileID);
        checkBoxAgree = rootview.findViewById(R.id.checkBoxSignUpID);
        buttonSignUp = rootview.findViewById(R.id.buttonSignUpID);
        buttonSignUp.setOnClickListener(this);
        return rootview;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSignUpID) {
            String providerCompanyName = companyName.getText().toString();
            String providerAddress = address.getText().toString();
            String providerNid = nid.getText().toString();
            String providerTin = tin.getText().toString();
            String providerTradeLicense = tradeLicense.getText().toString();
            String providerCompanyProfile = companyProfile.getText().toString();

            if (providerCompanyName.equals("")) {
                Toast.makeText(getActivity(), "Please Enter your company name", Toast.LENGTH_SHORT).show();
            }
//            else if (TextUtils.isEmpty(providerAddress)) {
//                Toast.makeText(getActivity(), "Please Enter your address", Toast.LENGTH_SHORT).show();
//            } else if (TextUtils.isEmpty(providerNid)) {
//                Toast.makeText(getActivity(), "Please Enter your NID", Toast.LENGTH_SHORT).show();
//            } else if (TextUtils.isEmpty(providerTin)) {
//                Toast.makeText(getActivity(), "Please Enter your TIN", Toast.LENGTH_SHORT).show();
//            } else if (TextUtils.isEmpty(providerTradeLicense)) {
//                Toast.makeText(getActivity(), "Please Enter your Trade License Number", Toast.LENGTH_SHORT).show();
//            } else if (TextUtils.isEmpty(providerCompanyProfile)) {
//                Toast.makeText(getActivity(), "Please Enter your copany profile", Toast.LENGTH_SHORT).show();
//            } else if (!checkBoxAgree.isChecked()) {
//                Toast.makeText(getActivity(), "Check box is not checked", Toast.LENGTH_SHORT).show();
//            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog alertDialog;
                builder.setTitle("Sign Up Successful");
                builder.setMessage("You are successfully signed up as provider");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentLoginPage = new Intent(getActivity(), BookingManagerActivity.class);
                        startActivity(intentLoginPage);
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }
}
