package com.example.studyspotapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

public class StudySpotAuthenticatedActivity extends AppCompatActivity {
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        String fileName =  " /Users/sophiecisse/AndroidStudioProjects/StudySpotApplication/app/StudyBuddiesData.json";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_authenticated);
        try {
            String jsonString = "{"
                    + " \"study spots data\": ["
                    + " {"
                    + " \"name\": \"USC Village\","
                    + " \"location\" : \"123 ABC St Los Angeles, CA 90089\","
                    + " \"openHours\" : \"Monday-Sunday 9-5pm\","
                    + " \"longitude\" : \"4.5\""
                    + " },"
                    + " ]"
                    + "}";
            String result = jsonString;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                Log.d("str", "HERE");
//                result = new String(Files.readAllBytes(Paths.get(fileName).toAbsolutePath()));
//            }
            System.out.println("result: " + result);
            Log.d("str", result);



            JSONObject obj = new JSONObject(result);
            System.out.println("HERE 1");

            JSONArray studySpotsData = obj.getJSONArray("study spots data");
            JSONObject studySpot = studySpotsData.getJSONObject(0);
            String name = studySpot.getString("name");
            String rating = studySpot.getString("longitude");
            String location = studySpot.getString("location");
            String timesOpen = studySpot.getString("openHours");
            final TextView studySpotName = (TextView) findViewById(R.id.StudySpotName);
            studySpotName.setText(name);
            final TextView studySpotRating = (TextView) findViewById(R.id.StudySpotRating);
            studySpotRating.setText(rating);
            final TextView studySpotLocation= (TextView) findViewById(R.id.StudySpotLocation);
            studySpotLocation.setText(location);
            final TextView studySpotTimesOpen= (TextView) findViewById(R.id.StudySpotTimesOpen);
            studySpotTimesOpen.setText(timesOpen);
            System.out.println("HERE 3");

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        final RatingBar ratingBar = findViewById(R.id.UserRating);
            Float userRating =  ratingBar.getRating();
            System.out.println("Review: " + userRating);
            ToggleButton lightingTag = findViewById(R.id.LightingTag);
            boolean lightTagOn = lightingTag.hasSelection();
        }
        public void goToLoginPage(android.view.View view) {
            Intent intent = new Intent(this, LoginPageActivity.class);
            startActivity(intent);
        }
        public void rateLocation(android.view.View view){
            Intent intent = new Intent (this, LoginPageActivity.class);
            startActivity(intent);
        }
}