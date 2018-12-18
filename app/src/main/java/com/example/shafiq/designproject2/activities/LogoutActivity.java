package com.example.shafiq.designproject2.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.shafiq.designproject2.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class LogoutActivity extends AppCompatActivity {
    Button logoutButton;
    private FirebaseAuth mAuth;
    Bundle bundle;
    TextView emailTextView, userNameTextView;
    ImageView profileImage;
    Toolbar toolbar;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        setTitle("Log Out");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        logoutButton = findViewById(R.id.buttonLogoutID);
        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.textViewUserEmailID);
        userNameTextView = findViewById(R.id.textViewUserNameID);
        profileImage = findViewById(R.id.imageViewID);
        bundle = getIntent().getExtras();
        assert bundle != null;
        String email = bundle.getString("Email");
        String userName = bundle.getString("userName");
        String photoUrl = bundle.getString("photoUrl");
        userNameTextView.setText(userName);
        emailTextView.setText(email);
        Picasso.with(this).load(photoUrl).into(profileImage);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                updateUI();
            }
        });
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser == null) {
//            updateUI();
//        }
//
//    }

    private void updateUI() {
        Toast.makeText(LogoutActivity.this, "you are logged out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LogoutActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LogoutActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
