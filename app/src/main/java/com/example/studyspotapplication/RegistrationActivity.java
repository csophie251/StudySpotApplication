package com.example.studyspotapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
    }

    public void goToLoginPage(android.view.View view){
        Intent intent = new Intent (this, LoginPageActivity.class);
        startActivity(intent);
    }
}