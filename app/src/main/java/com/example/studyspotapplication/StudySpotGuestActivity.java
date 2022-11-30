package com.example.studyspotapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudySpotGuestActivity extends AppCompatActivity {
    public String studySpotName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_guest);
        Intent intent = getIntent();
        studySpotName = intent.getStringExtra("name");
        Log.d("Study Spot Name: ", studySpotName);

        String studySpotRating = "No Rating";
        String studySpotAddress = "No Address";
        String studySpotOpenTimes = "No Openings";
//        if(Util.retrieveStudySpotRating(studySpotName) != null){
//            studySpotRating = Util.retrieveStudySpotRating(studySpotName).toString(); // uncomment out later
//        }
        if(Util.retrieveStudySpotAddress(studySpotName) != null){
            studySpotAddress = Util.retrieveStudySpotAddress(studySpotName).toString(); // uncomment out later
        }
//        if(Util.retrieveStudySpotTimesOpen(studySpotName) != null){
//            studySpotOpenTimes = Util.retrieveStudySpotTimesOpen(studySpotName).toString(); // uncomment out later
//        }
        Log.d("Study Spot Information:", (studySpotRating + ", " +studySpotAddress  + ", " + studySpotOpenTimes));

        //Set text
        final TextView name = findViewById(R.id.StudySpotNameGuest);
        name.setText(studySpotName);
        final TextView rating = findViewById(R.id.StudySpotRatingGuest);
        rating.setText(studySpotRating);
        final TextView location = findViewById(R.id.StudySpotLocationGuest);
        location.setText(studySpotAddress);
        final TextView timesOpen = findViewById(R.id.StudySpotTimesOpenGuest);
        timesOpen.setText(studySpotOpenTimes);
        final ImageView studySpotImage = findViewById(R.id.imageView);
        int image = Images.getImage(studySpotName);
        studySpotImage.setImageResource(image);
        Log.d("Set Study Info:", "finished");

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
    public void goToLoginPage(android.view.View view) {
        Intent intent = new Intent(this, LoginPageActivity.class);
        startActivity(intent);
    }
    public void goToSignUpPage(android.view.View view){
        Intent intent = new Intent (this, RegistrationActivity.class);
        startActivity(intent);
    }
}