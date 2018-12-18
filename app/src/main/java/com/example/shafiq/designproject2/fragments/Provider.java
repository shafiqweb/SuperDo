package com.example.shafiq.designproject2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.adapter.BasicBusinessInfoViewPagerAdapter;

import static android.app.Activity.RESULT_OK;

public class Provider extends Fragment implements View.OnClickListener{
    private static final int RESULT_LOAD_IMAGE = 2;
    ImageView imageViewProviderProfilePicture;
    ViewPager viewPagerBasicBusiness;
    TabLayout tabLayoutBasicBusiness;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.provider,container,false);
        imageViewProviderProfilePicture = view.findViewById(R.id.imageViewProviderProfilePictureID);
        viewPagerBasicBusiness = view.findViewById(R.id.viewPagerBasicBusinessID);
        tabLayoutBasicBusiness = view.findViewById(R.id.tabLayout_basic_businessTabsID);
        BasicBusinessInfoViewPagerAdapter basicBusinessInfoViewPagerAdapter = new BasicBusinessInfoViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerBasicBusiness.setAdapter(basicBusinessInfoViewPagerAdapter);
        tabLayoutBasicBusiness.setupWithViewPager(viewPagerBasicBusiness);
        imageViewProviderProfilePicture.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewProviderProfilePictureID) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageViewProviderProfilePicture.setImageURI(selectedImage);
        }
    }
}
