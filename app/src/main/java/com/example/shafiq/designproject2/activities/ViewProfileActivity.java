package com.example.shafiq.designproject2.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;
import com.example.shafiq.designproject2.model.DateInputMask;

public class ViewProfileActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String key_sharedPreference = "MYPREFS";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView imageViewBackButton, imageViewProfilePicture;
    Button buttonCancel, buttonSave;
    EditText firstName, birthDate, email, phoneNumber, oldPassword, newPassword, confirmNewPassword;
    private static final int RESULT_LOAD_IMAGE = 1;
    Button buttonChangePassword, buttonSignOut;
    String emailPattern = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        sharedPreferences = getSharedPreferences(key_sharedPreference, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        Toolbar toolbar = findViewById(R.id.toolbar_view_profileID);
        imageViewBackButton = findViewById(R.id.back_button_view_profile_ID);
        imageViewBackButton.setOnClickListener(this);
        imageViewProfilePicture = findViewById(R.id.imageViewProfilePictureID);
        firstName = findViewById(R.id.editTextFirstNameID);
        birthDate = findViewById(R.id.editTextDateBirthID);
        email = findViewById(R.id.editTextEmailID);
        email.setSelection(email.getText().length());
        phoneNumber = findViewById(R.id.editTextPhoneNumberID);
        phoneNumber.setSelection(4);
        imageViewProfilePicture.setOnClickListener(this);
        new DateInputMask(birthDate);
        buttonChangePassword = findViewById(R.id.buttonChangePasswordID);
        buttonChangePassword.setOnClickListener(this);
        buttonSignOut = findViewById(R.id.buttonSignOutID);
        buttonSignOut.setOnClickListener(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
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
        if (v.getId() == R.id.back_button_view_profile_ID) {
            super.onBackPressed();
        }
        if (v.getId() == R.id.imageViewProfilePictureID) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
        }
        if (v.getId() == R.id.buttonChangePasswordID) {
            String userFirstName = firstName.getText().toString();
            String userBirthDate = birthDate.getText().toString();
            String userEmail = email.getText().toString();

            if (userFirstName.equals("")) {
                Toast.makeText(this, "Please Enter your First Name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(userBirthDate) || userBirthDate.equals("DD/MM/YYYY")) {
                Toast.makeText(this, "Please Enter Birthdate", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(userEmail)) {
                Toast.makeText(this, "Please Enter Your email", Toast.LENGTH_SHORT).show();
            } else if (!userEmail.matches(emailPattern)) {
                Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            } else {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final View dialogueView = getLayoutInflater().inflate(R.layout.dialouge_change_password, null);
                oldPassword = dialogueView.findViewById(R.id.editTextEnterOldPasswordID);
                newPassword = dialogueView.findViewById(R.id.editTextEnterNewPasswordID);
                confirmNewPassword = dialogueView.findViewById(R.id.editTextConfirmNewPasswordID);
                buttonSave = dialogueView.findViewById(R.id.buttonSaveID);
                buttonCancel = dialogueView.findViewById(R.id.buttonCancelID);
                buttonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userOldPassword = oldPassword.getText().toString();
                        String userNewPassword = newPassword.getText().toString();
                        String userConfirmNewPassword = confirmNewPassword.getText().toString();
                        String userPassword = sharedPreferences.getString("passwordKey", null);
                        if (userOldPassword.isEmpty()) {
                            Toast.makeText(ViewProfileActivity.this, "Please Enter your old Password", Toast.LENGTH_SHORT).show();
                        } else if (!userOldPassword.equals(userPassword)) {
                            Toast.makeText(ViewProfileActivity.this, "Password does not matches", Toast.LENGTH_SHORT).show();
                        } else if (userNewPassword.isEmpty()) {
                            Toast.makeText(ViewProfileActivity.this, "Please Enter your new Password", Toast.LENGTH_SHORT).show();
                        } else if (userConfirmNewPassword.isEmpty()) {
                            Toast.makeText(ViewProfileActivity.this, "Please Enter your confirmed Password", Toast.LENGTH_SHORT).show();
                        } else if (!userNewPassword.equals(userConfirmNewPassword)) {
                            Toast.makeText(ViewProfileActivity.this, "New password and confirm password does not matches", Toast.LENGTH_SHORT).show();
                        } else {
                            editor.putString("passwordKey", userConfirmNewPassword);
                            editor.apply();
                            Toast.makeText(ViewProfileActivity.this, "Password saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ViewProfileActivity.this, ViewProfileActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewProfileActivity.this, ViewProfileActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setView(dialogueView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
        if (v.getId() == R.id.buttonSignOutID) {
            Intent intentLoginPage = new Intent(ViewProfileActivity.this, ServiceCategoryActivity.class);
            startActivity(intentLoginPage);
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

