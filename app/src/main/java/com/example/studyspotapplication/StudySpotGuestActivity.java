package com.example.studyspotapplication;


import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
=======
import android.util.Log;
import android.widget.ImageView;
>>>>>>> master
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudySpotGuestActivity extends AppCompatActivity {
    public String studySpotName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_guest);
<<<<<<< HEAD

        Button mButton = findViewById(R.id.login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(StudySpotGuestActivity.this, LoginPageActivity.class);
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
        try {
            String jsonString = "{"
                    + " \"study spots data\": ["
                    + " {"
                    + " \"name\": \"USC Village\","
                    + " \"location\" : \"123 ABC St Los Angeles, CA 90089\","
                    + " \"openHours\" : \"Monday-Sunday 9-5pm\","
                    + " \"rating\" : \"4.5\""
                    + " },"
                    + " ]"
                    + "}";
            JSONObject obj = new JSONObject(jsonString);
            JSONArray studySpotsData = obj.getJSONArray("study spots data");
            JSONObject studySpot = studySpotsData.getJSONObject(0);
            String name = this.getTitle().toString();
=======
        Intent intent = getIntent();
        studySpotName = intent.getStringExtra("name");
        Log.d("Study Spot Name: ", studySpotName);
>>>>>>> master

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