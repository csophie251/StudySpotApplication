package com.example.studyspotapplication;

import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudySpotAuthenticatedActivity extends AppCompatActivity {
    public Float originalRating;
    public JSONArray reviewsArray;
    ArrayList<String> selectedTags;
    CheckBox busy;
    CheckBox outlets;
    CheckBox quiet;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_authenticated);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.d("myTag", name);
        selectedTags = new ArrayList<String>();
        // start new thread getting data with name
        busy = findViewById(R.id.BusyTag);
        outlets = findViewById(R.id.OutletTag);
        quiet = findViewById(R.id.QuietTag);
        try {
            String jsonString = "{"
                    + " \"study spots data\": ["
                    + " {"
                    + " \"name\": \" + name +\","
                    + " \"location\" : \"123 ABC St Los Angeles, CA 90089\","
                    + " \"openHours\" : \"Monday-Sunday 9-5pm\","
                    +  " \"reviews\" : ["
                    + " \"Amazing Spot!\","
                    + " \"10 out of 10\","
                    + " \"Loved it here!\" ],"
                    + " \"rating\" : \"4.5\""
                    + " },"
                    + " ]"
                    + "}";


            JSONObject obj = new JSONObject(jsonString);
            JSONArray studySpotsData = obj.getJSONArray("study spots data");
            JSONObject studySpot = studySpotsData.getJSONObject(0);


            reviewsArray =  studySpot.getJSONArray("reviews");
            int arySize = reviewsArray.length();
            final TextView studySpotReviews= (TextView) findViewById(R.id.PlaceReviewsHere);
            String text = "";
            for(int i =0; i < arySize; i++){
                text += reviewsArray.get(i);
                text += '\n';
            }
            studySpotReviews.setText(text);

            String rating = studySpot.getString("rating");
            originalRating =parseFloat(rating);
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

        public void saveRating(android.view.View view) {
            final RatingBar ratingBar = findViewById(R.id.UserRating);
            Float userRating =  ratingBar.getRating();
            Log.d("myTag", "user rating: " + userRating);

            Float newRating = (originalRating + userRating)/2;
            final TextView studySpotRating = (TextView) findViewById(R.id.StudySpotRating);
            studySpotRating.setText(newRating.toString());
        }

        public void saveReview(android.view.View view){
            //final TextView studySpotNewReview = (TextView) findViewById(R.id.WriteAReviewText);
            //String newReview = (String) studySpotNewReview.getText();
            //int index = reviewsArray.length() + 1;
        }
        public void saveTags(android.view.View view){
            if(busy.isChecked()){
                for (String element : selectedTags){
                    if (element.contains("busy")){
                        Log.d("myTag", "already selected busy");
                    }
                    else{
                        selectedTags.add("busy");
                        Log.d("myTag", "add busy to list");
                    }
                }
            }
            else{
                for (String element : selectedTags){
                    if (element.contains("busy")){
                        Log.d("myTag", "remove busy from list");
                    }
                }
            }
            if(outlets.isChecked()){
                for (String element : selectedTags){
                    if (element.contains("outlets")){
                        Log.d("myTag", "already selected outlets");
                    }
                    else{
                        selectedTags.add("outlets");
                        Log.d("myTag", "add outlets to list");
                    }
                }
            }
            else{
                for (String element : selectedTags){
                    if (element.contains("outlets")){
                        Log.d("myTag", "remove outlets from list");
                    }
                }
            }
            if(quiet.isChecked()){
                for (String element : selectedTags){
                    if (element.contains("quiet")){
                        Log.d("myTag", "already selected quiet");
                    }
                    else{
                        selectedTags.add("quiet");
                        Log.d("myTag", "add quiet to list");
                    }
                }
            }
            else{
                for (String element : selectedTags){
                    if (element.contains("quiet")){
                        Log.d("myTag", "remove quiet from list");
                    }
                }
            }
        }

        public void onQuietClicked(android.view.View view){
//            for (String element : selectedTags){
//                if (element.contains("quiet")){
//                    Log.d("myTag", "already selected quiet");
//                }
//                else{
//                    Log.d("myTag", "add quiet to list");
//                    selectedTags.add("quiet");
//                }
//            }
        }
        public void onBusyClicked(android.view.View view){
//            for (String element : selectedTags){
//                if (element.contains("busy")){
//                    Log.d("myTag", "already selected busy");
//                }
//                else{
//                    Log.d("myTag", "add busy to list");
//                    selectedTags.add("busy");
//                }
//            }
        }
        public void onOutletClicked(android.view.View view){
//            for (String element : selectedTags){
//                if (element.contains("outlet")){
//                    Log.d("myTag", "already selected outlet");
//                }
//                else{
//                    Log.d("myTag", "add outlet to list");
//                    selectedTags.add("outlet");
//                }
//            }
        }
}