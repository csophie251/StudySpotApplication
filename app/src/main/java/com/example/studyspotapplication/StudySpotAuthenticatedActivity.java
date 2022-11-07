package com.example.studyspotapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StudySpotAuthenticatedActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_study_spot_authenticated);
        }

        public void rateLocation(android.view.View view){
            Intent intent = new Intent (this, LoginPageActivity.class);
            startActivity(intent);
        }
}