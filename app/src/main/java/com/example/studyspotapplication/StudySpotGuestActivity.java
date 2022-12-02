package com.example.studyspotapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudySpotGuestActivity extends AppCompatActivity {
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_guest);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Log.d("Study Spot Name: ", name);

        //Display name, location, rating ,open times
        displayStudySpotInfo();

        //Display study spot image
        final ImageView studySpotImage = findViewById(R.id.imageView);
        int image = Images.getImage(name);
        studySpotImage.setImageResource(image);

        Button mButton = findViewById(R.id.login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(StudySpotGuestActivity.this, LandingPageActivity.class);
                startActivity(myIntent);
            }
        });

        Button mButton2 = findViewById(R.id.mapBtn);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(StudySpotGuestActivity.this, GuestMapsActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public void displayStudySpotInfo() {
        Log.d("myTag", ">> displayStudySpotInfo");
        new Thread() {
            @Override
            public void run() {
                StudySpot ss = Util.retrieveStudySpot(name);
                String rating = "No Rating";
                String location = "No Address";
                String hours = "No Openings";
                if (ss == null || ss.rating == null || ss.location == null || ss.hours == null) {
                    runOnUiThread(() -> Toast.makeText(StudySpotGuestActivity.this, "Invalid Study Spot", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> {
                        final TextView studySpotName = findViewById(R.id.StudySpotName);
                        studySpotName.setText(name);
                        final TextView studySpotRating =  findViewById(R.id.StudySpotRating);
                        studySpotRating.setText(ss.rating);
                        final TextView studySpotLocation=  findViewById(R.id.StudySpotLocation);
                        studySpotLocation.setText(ss.location);
                        final TextView studySpotTimesOpen= findViewById(R.id.StudySpotTimesOpen);
                        studySpotTimesOpen.setText(ss.hours);
                    });
                }
            }
        }.start();
    }
    public void goToLoginPage(android.view.View view) {
        Intent intent = new Intent(this, LoginPageActivity.class);
        startActivity(intent);
    }
    public void goToSignUpPage(android.view.View view){
        Intent intent = new Intent (this, RegistrationActivity.class);
        startActivity(intent);
    }
}