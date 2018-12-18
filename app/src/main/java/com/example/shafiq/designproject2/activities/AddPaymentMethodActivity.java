package com.example.shafiq.designproject2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;

public class AddPaymentMethodActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageViewBackButton;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);
        imageViewBackButton = findViewById(R.id.back_button_add_payment_methods_ID);
        buttonSave = findViewById(R.id.buttonSaveID);
        imageViewBackButton.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar_add_payment_methodsID);
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
        if (v.getId() == R.id.back_button_add_payment_methods_ID) {
            super.onBackPressed();
        }
        if (v.getId() == R.id.buttonSaveID){
            Intent intent = new Intent(AddPaymentMethodActivity.this,ServiceCategoryActivity.class);
            startActivity(intent);
        }

    }
}
