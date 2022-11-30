package com.example.studyspotapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LandingPageActivity extends AppCompatActivity {
    private TextView eGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void goToLoginPage(android.view.View view) {
        Intent intent = new Intent(this, LoginPageActivity.class);
        startActivity(intent);
    }
    public void goToSignUpPage(android.view.View view){
        Intent intent = new Intent (this, RegistrationActivity.class);
        startActivity(intent);
    }
    public void goToGuestMapPage(android.view.View view){
        Intent intent = new Intent (this, GuestMapsActivity.class);
        startActivity(intent);
    }
}