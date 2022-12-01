package com.example.studyspotapplication;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudySpotAuthenticatedActivity extends AppCompatActivity {
    public ArrayList<String> selectedTags;
    public ArrayList<String> reviewsList;
    String name;
    String username;
    CheckBox busy;
    CheckBox outlets;
    CheckBox quiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_authenticated);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        //Display name, location, rating ,open times
        displayStudySpotInfo();

        //Display study spot image
        final ImageView studySpotImage = findViewById(R.id.imageView);
        int image = Images.getImage(name);
        studySpotImage.setImageResource(image);

        //Tags
        selectedTags = new ArrayList<String>();
        busy = findViewById(R.id.BusyTag);
        outlets = findViewById(R.id.OutletTag);
        quiet = findViewById(R.id.QuietTag);

        //Display reviews
        reviewsList = new ArrayList<String>();
        displayReviews();

        Button mButton = findViewById(R.id.logout);
        mButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(StudySpotAuthenticatedActivity.this, LoginPageActivity.class);
            startActivity(myIntent);
        });

        Button mButton2 = findViewById(R.id.mapBtn);
        mButton2.setOnClickListener(view -> {
            Intent myIntent = new Intent(StudySpotAuthenticatedActivity.this, MapsActivity.class);
            startActivity(myIntent);
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
                    runOnUiThread(() -> Toast.makeText(StudySpotAuthenticatedActivity.this, "Invalid Study Spot", Toast.LENGTH_SHORT).show());
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

    public void displayReviews(){
        new Thread() {
            @Override
            public void run() {
                reviewsList = Util.retrieveReviews(name);
                if (reviewsList == null || reviewsList.isEmpty() ) {
                    runOnUiThread(() -> {
                        reviewsList = new ArrayList<String>();
                        reviewsList.add("No Reviews Yet!");
                    });
                }
                runOnUiThread(() -> {
                    final TextView studySpotReviews= findViewById(R.id.PlaceReviewsHere);
                    String text = "";
                    for(int i =0; i < (int) reviewsList.size(); i++){
                        text += reviewsList.get(i);
                        text += '\n';
                    }
                    studySpotReviews.setText(text);
                });
            }
        }.start();
    }

    public void saveRating(android.view.View view) {
        new Thread() {
            @Override
            public void run() {
                final RatingBar ratingBar = findViewById(R.id.UserRating);
                Float rate =  ratingBar.getRating();
                Double userRating = parseDouble(rate.toString());
                Log.d("myTag", "Rating: " + userRating);
//                String newRating = "4.0";
                String newRating = Util.sendRating(username, name, userRating);
                if (newRating == null || newRating.isEmpty() ) {
                    runOnUiThread(() -> Toast.makeText(StudySpotAuthenticatedActivity.this, "Failed to save rating", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> {
                        final TextView studySpotRating =  findViewById(R.id.StudySpotRating);
                        studySpotRating.setText(newRating);
                    });
                }
            }
        }.start();
    }
    public void saveTags(android.view.View view){
        new Thread() {
            @Override
            public void run() {
                ArrayList<String> selectedTags = new ArrayList<String>();
                if (busy.isChecked()) {
                    selectedTags.add("Busy");
                    Log.d("Add", "busy to list");
                }
                if (outlets.isChecked()) {
                    selectedTags.add("Outlets");
                    Log.d("Add", "outlets to list");
                }
                if (quiet.isChecked()) {
                    selectedTags.add("Quiet");
                    Log.d("Add", "quiet to list");
                }
//                boolean isSaved = true;
                boolean isSaved = Util.sendTags(name, selectedTags);
                if (isSaved ) {
                    runOnUiThread(() -> {
                        Log.d("true", "tags were successfully added to database");
                    });
                }
                else {
                    runOnUiThread(() -> {
                        Log.d("false", "some error occurred while adding tags to database");
                    });
                }
            }
        }.start();
    }

    public void saveReview(android.view.View view){
        new Thread() {
            @Override
            public void run() {
                final EditText studySpotNewReview = findViewById(R.id.WriteAReviewText);
                String newReview = studySpotNewReview.getText().toString();
                boolean sendReview = true;
               // boolean sendReview = Util.sendReview(username, name, newReview);
                if (sendReview ) {
                    runOnUiThread(() -> {
                        Log.d("true", "new review was successfully added to database");
                        displayReviews();
                    });
                }
                else {
                    runOnUiThread(() -> {
                        Log.d("true", "new review was successfully added to database");
                        displayReviews();
                    });
                }
            }
        }.start();
    }
}