package com.example.shafiq.designproject2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;

public class SendSMSActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextUserEmail;
    Button buttonSendEmail;
    String userEmail;
    String userPassword;
    Bundle bundle;
    ImageView imageViewBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        Toolbar toolbarForgotPassword = findViewById(R.id.toolbarForgotPasswordID);
        setSupportActionBar(toolbarForgotPassword);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        imageViewBackButton = findViewById(R.id.back_button_forgot_passwordID);
        editTextUserEmail = findViewById(R.id.editTextUserEmailID);
        bundle = getIntent().getExtras();
        userPassword = bundle.getString("userPasswordKey");
        buttonSendEmail = findViewById(R.id.buttonSendEmailID);
        buttonSendEmail.setOnClickListener(this);
        imageViewBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button_forgot_passwordID) {
            super.onBackPressed();
        }
        if (v.getId() == R.id.buttonSendEmailID) {
            userEmail = editTextUserEmail.getText().toString();
            Toast toast = Toast.makeText(this, "Please enter Valid Email", Toast.LENGTH_LONG);
            View view = toast.getView();
            view.setBackgroundColor(Color.BLUE);
            TextView text = view.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            toast.show();
            Intent intent = new Intent(SendSMSActivity.this,VeryfyCodeActivity.class);
            startActivity(intent);
            //editTextUserEmail.setText(userPassword);

        }
    }
}
