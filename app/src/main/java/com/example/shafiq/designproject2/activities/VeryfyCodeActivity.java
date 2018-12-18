package com.example.shafiq.designproject2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;

public class VeryfyCodeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageViewBackButton;
    Button buttonVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veryfy_code);
        Toolbar toolbarVerifyCode = findViewById(R.id.toolbarVerifyCodeID);
        setSupportActionBar(toolbarVerifyCode);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        imageViewBackButton = findViewById(R.id.back_button_verify_codeID);
        buttonVerify = findViewById(R.id.buttonVerifyID);
        imageViewBackButton.setOnClickListener(this);
        buttonVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_button_verify_codeID) {
            super.onBackPressed();
        }
        if (v.getId() == R.id.buttonVerifyID){
            Toast toast = Toast.makeText(this, "Verified your code", Toast.LENGTH_LONG);
            View view = toast.getView();
            view.setBackgroundColor(Color.BLUE);
            TextView text = view.findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            toast.show();
            Intent intent = new Intent(VeryfyCodeActivity.this,ResetPasswordActivity.class);
            startActivity(intent);
        }
    }
}
