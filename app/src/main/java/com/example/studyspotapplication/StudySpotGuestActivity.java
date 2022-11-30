package com.example.studyspotapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        String studySpotRating = "4.0";
        String studySpotAddress ="test address";
        String studySpotOpenTimes = "test open time";
        Log.d("Study Spot Information:", (studySpotRating + ", " +studySpotAddress  + ", " + studySpotOpenTimes));

        // Get data from database
//        String studySpotRating = Util.retrieveStudySpotRating(studySpotName).toString();
//        String studySpotAddress = Util.retrieveStudySpotAddress(studySpotName);
//        String studySpotOpenTimes = Util.retrieveStudySpotTimesOpen(studySpotName);
//        Log.d("Study Spot Information:", (studySpotRating + ", " +studySpotAddress  + ", " + studySpotOpenTimes));

        //Set text
        final TextView name = (TextView) findViewById(R.id.StudySpotNameGuest);
        name.setText(studySpotName);
        final TextView rating = (TextView) findViewById(R.id.StudySpotRatingGuest);
        rating.setText(studySpotRating);
        final TextView location= (TextView) findViewById(R.id.StudySpotLocationGuest);
        location.setText(studySpotAddress);
        final TextView timesOpen= (TextView) findViewById(R.id.StudySpotTimesOpenGuest);
        timesOpen.setText(studySpotOpenTimes);
        final ImageView studySpotImage = (ImageView)  findViewById(R.id.imageView);
        studySpotImage.setImageResource(R.drawable.leaveylibrary); //TODO
        Log.d("Set Study Info:", "finished");
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