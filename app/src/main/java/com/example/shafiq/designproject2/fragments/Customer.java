package com.example.shafiq.designproject2.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shafiq.designproject2.model.DateInputMask;
import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.activities.ServiceCategoryActivity;

import static android.app.Activity.RESULT_OK;

public class Customer extends Fragment implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;
    public static final String key_sharedPreference = "MYPREFS";
    Button signUpButton;
    CheckBox checkBoxAgree;
    ImageView imageViewProfilePicture;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText firstName, birthDate, email, phoneNumber, password, confirmPassword;
    String emailPattern = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.customer, container, false);
        imageViewProfilePicture = rootview.findViewById(R.id.imageViewProfilePictureID);
        firstName = rootview.findViewById(R.id.editTextFirstNameID);
        birthDate = rootview.findViewById(R.id.editTextDateBirthID);
        email = rootview.findViewById(R.id.editTextEmailID);
        email.setSelection(email.getText().length());
        phoneNumber = rootview.findViewById(R.id.editTextPhoneNumberID);
        password = rootview.findViewById(R.id.editTextPasswordID);
        confirmPassword = rootview.findViewById(R.id.editTextConfirmPasswordID);
        checkBoxAgree = rootview.findViewById(R.id.checkBoxSignUpID);
        signUpButton = rootview.findViewById(R.id.buttonSignUpID);
        //firstName.requestFocus();//Cursor setting in first Edit Text
        phoneNumber.setSelection(4);
        new DateInputMask(birthDate);
        imageViewProfilePicture.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        return rootview;

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageViewProfilePictureID) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
        if (v.getId() == R.id.buttonSignUpID) {
            String userFirstName = firstName.getText().toString();
            String userBirthDate = birthDate.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userConfirmPassword = confirmPassword.getText().toString();

            if (userFirstName.equals("")) {
                Toast.makeText(getActivity(), "Please Enter your First Name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(userBirthDate) || userBirthDate.equals("DD/MM/YYYY")) {
                Toast.makeText(getActivity(), "Please Enter Birthdate", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(getActivity(), "Please Enter Your email", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(userPassword)) {
                Toast.makeText(getActivity(), "Please Enter your password", Toast.LENGTH_SHORT).show();
            } else if (!userEmail.matches(emailPattern)) {
                Toast.makeText(getActivity(), "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            } else if (!userPassword.equals(userConfirmPassword)) {
                Toast.makeText(getActivity(), "Password and confirm password didn't match", Toast.LENGTH_LONG).show();
            } else if (!checkBoxAgree.isChecked()) {
                Toast.makeText(getActivity(), "CheckBox is not checked", Toast.LENGTH_LONG).show();
            } else {
                sharedPreferences = getActivity().getSharedPreferences(key_sharedPreference, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("emailKey", userEmail);
                editor.putString("passwordKey", userPassword);
                editor.apply();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog alertDialog;
                builder.setTitle("Sign Up Successful");
                builder.setMessage("You are successfully signed up as customer");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentLoginPage = new Intent(getActivity(), ServiceCategoryActivity.class);
                        startActivity(intentLoginPage);
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageViewProfilePicture.setImageURI(selectedImage);
        }
    }
}
