package com.example.shafiq.designproject2.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafiq.designproject2.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 234;
    EditText userNameEditText, passwordEditText;
    TextView signUpTextView;
    private CallbackManager mCallbackManager;
    public static final String key_sharedPreference = "MYPREFS";
    private static final String TAG = "Login";
    private FirebaseAuth mAuth;
    Button facebookLoginButton, googleLoginButton, loginButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CheckBox checkBox;
    Boolean saveLogin;
    TextView textViewForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(key_sharedPreference, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
        userNameEditText = findViewById(R.id.editTextUserNameId);
        passwordEditText = findViewById(R.id.editTextPasswordId);
        signUpTextView = findViewById(R.id.textViewSignUpID);
        userNameEditText.setSelection(userNameEditText.getText().toString().length());
        passwordEditText.setSelection(passwordEditText.getText().toString().length());
        loginButton = findViewById(R.id.loginButtonID);
        checkBox = findViewById(R.id.checkBoxID);
        textViewForgotPassword = findViewById(R.id.textViewForgotPasswordID);
        textViewForgotPassword.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        checkBox.setOnClickListener(this);
        facebookLoginButton = findViewById(R.id.facebookLoginButtonID);
        googleLoginButton = findViewById(R.id.googleLoginButtonID);
        loginButton.setOnClickListener(this);
        userNameEditText.setHint("ABC");
        passwordEditText.setHint("123456");
        saveLogin = sharedPreferences.getBoolean("saveLogin", true);
        if (saveLogin) {
            userNameEditText.setText(sharedPreferences.getString("userEmail", null));
            passwordEditText.setText(sharedPreferences.getString("userPassword", null));
            userNameEditText.setSelection(userNameEditText.getText().length());
            passwordEditText.setSelection(passwordEditText.getText().length());
        }
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginButton.setEnabled(true);
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Log.d(TAG, "facebook:onSuccess:" + loginResult);

                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });
            }
        });
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLoginButton.setEnabled(true);
                signIn();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String email = user.getEmail();
                                String userName = user.getDisplayName();
                                String profilePicUrl = String.valueOf(user.getPhotoUrl());
                                Toast.makeText(MainActivity.this, "you are google logged in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
                                intent.putExtra("Email", email);
                                intent.putExtra("userName", userName);
                                intent.putExtra("photoUrl", profilePicUrl);
                                startActivity(intent);
                            }
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //You can Toast here
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Pass the activity result back to the Facebook SDK
//        mCallbackManager.onActivityResult(requestCode, resultCode, data);
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            updateUI();
//        }
//
//    }
//
//    private void updateUI() {
//        Toast.makeText(MainActivity.this, "you are logged in", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this,LogoutActivity.class);
//        startActivity(intent);
//        finish();
//    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        final AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            facebookLoginButton.setEnabled(false);
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String email = user.getEmail();
                                String userName = user.getDisplayName();
                                String profilePicUrl = String.valueOf(user.getPhotoUrl());

                                Toast.makeText(MainActivity.this, "you are in logged in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
                                intent.putExtra("Email", email);
                                intent.putExtra("userName", userName);
                                intent.putExtra("photoUrl", profilePicUrl);
                                startActivity(intent);
                                finish();
                            }

                            //updateUI();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            facebookLoginButton.setEnabled(true);
                            //updateUI();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textViewSignUpID) {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.loginButtonID) {
            String userName = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String userEmail = sharedPreferences.getString("emailKey", null);
            String userPassword = sharedPreferences.getString("passwordKey", null);
            if (userName.equals(userEmail) && password.equals(userPassword)) {
                if (checkBox.isChecked()) {
                    editor.putBoolean("saveLogin", true);
                    editor.putString("userEmail", userEmail);
                    editor.putString("userPassword", userPassword);
                    editor.apply();
                }
                Intent intent = new Intent(MainActivity.this, ServiceCategoryActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(MainActivity.this, "enter correct name and password", Toast.LENGTH_SHORT).show();
            }


        }
        if (v.getId() == R.id.textViewForgotPasswordID) {
            sharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
            String userPassword = sharedPreferences.getString("passwordKey", null);
            Intent intent = new Intent(MainActivity.this, SendSMSActivity.class);
            intent.putExtra("userPasswordKey", userPassword);
            startActivity(intent);
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("message/rfc822");
//            startActivity(Intent.createChooser(intent,"Choose an email client"));
        }
    }

}
