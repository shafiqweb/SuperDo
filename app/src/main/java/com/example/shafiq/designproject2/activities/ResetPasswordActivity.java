package com.example.shafiq.designproject2.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.shafiq.designproject2.R;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageViewBackButton;
    Button buttonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Toolbar toolbarResetPassword = findViewById(R.id.toolbarResetPasswordID);
        setSupportActionBar(toolbarResetPassword);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        imageViewBackButton = findViewById(R.id.back_button_reset_passwordID);
        buttonResetPassword = findViewById(R.id.buttonResetPasswordID);
        imageViewBackButton.setOnClickListener(this);
        buttonResetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button_reset_passwordID) {
            super.onBackPressed();
        }
        if (v.getId() == R.id.buttonResetPasswordID) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog alertDialog;
            builder.setTitle("Reset Password");
            builder.setMessage("You are successfully reset password");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intentLoginPage = new Intent(ResetPasswordActivity.this, MainActivity.class);
                    startActivity(intentLoginPage);
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
        }
    }
}
