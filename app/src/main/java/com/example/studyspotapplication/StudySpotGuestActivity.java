package com.example.studyspotapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudySpotGuestActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            String name = " Leavey Library";
            String rating = " 4.5";
            String location = " 625 W Jefferson St,  Los Angeles, CA 90007";
            String timesOpen =  "Times Open: MWF 9-5pm S-S 12-2pm";

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_study_spot_guest);
            final TextView studySpotName = (TextView) findViewById(R.id.StudySpotNameGuest);
            studySpotName.setText(name);
            final TextView studySpotRating = (TextView) findViewById(R.id.StudySpotRatingGuest);
            studySpotRating.setText(rating);
            final TextView studySpotLocation= (TextView) findViewById(R.id.StudySpotLocationGuest);
            studySpotLocation.setText(location);
            final TextView studySpotTimesOpen= (TextView) findViewById(R.id.StudySpotTimesOpenGuest);
            studySpotTimesOpen.setText(timesOpen);


        }
        public void goToLoginPage(android.view.View view) {
            Intent intent = new Intent(this, LoginPageActivity.class);
            startActivity(intent);
        }
}