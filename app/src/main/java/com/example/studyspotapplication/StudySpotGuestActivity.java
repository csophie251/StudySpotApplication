package com.example.studyspotapplication;

import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudySpotGuestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_guest);

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

            //String name = studySpot.getString("name");
            String rating = studySpot.getString("rating");
            String location = studySpot.getString("location");
            String timesOpen = studySpot.getString("openHours");
            final TextView studySpotName = (TextView) findViewById(R.id.StudySpotNameGuest);
            studySpotName.setText(name);
            final TextView studySpotRating = (TextView) findViewById(R.id.StudySpotRatingGuest);
            studySpotRating.setText(rating);
            final TextView studySpotLocation = (TextView) findViewById(R.id.StudySpotLocationGuest);
            studySpotLocation.setText(location);
            final TextView studySpotTimesOpen = (TextView) findViewById(R.id.StudySpotTimesOpenGuest);
            studySpotTimesOpen.setText(timesOpen);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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