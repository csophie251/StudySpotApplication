package com.example.studyspotapplication;

import static java.lang.Double.parseDouble;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
import android.widget.Button;
=======
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
>>>>>>> master
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class StudySpotAuthenticatedActivity extends AppCompatActivity {
    public String studySpotName;
    public ArrayList<String> selectedTags;
    public ArrayList<String> reviewsList;
    CheckBox busy;
    CheckBox outlets;
    CheckBox quiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_authenticated);
        Intent intent = getIntent();
        studySpotName = intent.getStringExtra("name");
        Log.d("Study Spot Name: ", studySpotName);

<<<<<<< HEAD
        Button mButton = findViewById(R.id.logout);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(StudySpotAuthenticatedActivity.this, LoginPageActivity.class);
                startActivity(myIntent);
            }
        });
        Button mButton2 = findViewById(R.id.mapBtn);
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(StudySpotAuthenticatedActivity.this, MapsActivity.class);
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
                    +  " \"reviews\" : ["
                    + " \"Amazing Spot!\","
                    + " \"10 out of 10\","
                    + " \"Loved it here!\" ],"
                    + " \"rating\" : \"4.5\""
                    + " },"
                    + " ]"
                    + "}";
=======
       String studySpotRating = "4.0";
//        String studySpotAddress ="test address"; //remove later
//        String studySpotOpenTimes = "test open time";  //remove later
//        Log.d("Study Spot Information:", (studySpotRating + ", " +studySpotAddress  + ", " + studySpotOpenTimes)); // remove
>>>>>>> master

        // Get data from database
        // String studySpotRating = Util.retrieveStudySpotRating(studySpotName).toString(); // uncomment out later
        String studySpotAddress = Util.retrieveStudySpotAddress(studySpotName);
        String studySpotOpenTimes = Util.retrieveStudySpotTimesOpen(studySpotName);
        Log.d("Study Spot Information:", (studySpotRating + ", " +studySpotAddress  + ", " + studySpotOpenTimes));

        //Set text
        final TextView name = (TextView) findViewById(R.id.StudySpotName);
        name.setText(studySpotName);
        final TextView rating = (TextView) findViewById(R.id.StudySpotRating);
        rating.setText(studySpotRating);
        final TextView location= (TextView) findViewById(R.id.StudySpotLocation);
        location.setText(studySpotAddress);
        final TextView timesOpen= (TextView) findViewById(R.id.StudySpotTimesOpen);
        timesOpen.setText(studySpotOpenTimes);
        Log.d("Set Study Info:", "finished");

        //Tags
        selectedTags = new ArrayList<String>();
        busy = findViewById(R.id.BusyTag);
        outlets = findViewById(R.id.OutletTag);
        quiet = findViewById(R.id.QuietTag);
        Log.d("Tags:", "initialize tags lit");

        //Reviews
        reviewsList = new ArrayList<String>();
        reviewsList.add("Awesome spot!"); //comment out later
        reviewsList.add("pretty!"); //comment out later
        reviewsList.add("ahve fun!"); //comment out later
        reviewsList.add("great!"); //comment out later
        reviewsList.add("gerat 3!"); //comment out later
        reviewsList.add("testing!"); //comment out later
        reviewsList.add("yay!"); //comment out later
        reviewsList.add("fun!"); //comment out later
        reviewsList.add("aws!"); //comment out later
        reviewsList.add("best!"); //comment out later
        reviewsList.add("gerat hob !"); //comment out later
        reviewsList.add("sdf!"); //comment out later
        reviewsList.add("fs!"); //comment out later
        reviewsList.add("sdf!"); //comment out later
        reviewsList.add("prettdsfy!"); //comment out later
        reviewsList.add("sdf!"); //comment out later
        reviewsList.add("helo !"); //comment out later
        reviewsList.add("etsgl!"); //comment out later

        //reviewsList = Util.retrieveReviews(studySpotName); //uncomment out later
        final TextView studySpotReviews= (TextView) findViewById(R.id.PlaceReviewsHere);
        String text = "";
        for(int i =0; i < (int) reviewsList.size(); i++){
            text += reviewsList.get(i);
            text += '\n';
        }
        studySpotReviews.setText(text);

        final ImageView studySpotImage = (ImageView)  findViewById(R.id.imageView);
        int image = Images.getImage(studySpotName);
        studySpotImage.setImageResource(image);

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
        Float rating =  ratingBar.getRating();
        Double userRating = parseDouble(rating.toString());
        Log.d("User Rating: ",  userRating.toString());
        Double newRating = Util.sendRating(studySpotName, userRating);
        Log.d("Updated Rating: ",  newRating.toString());
        final TextView studySpotRating = (TextView) findViewById(R.id.StudySpotRating);
        studySpotRating.setText(newRating.toString());
    }
    public void saveTags(View view) throws InterruptedException {
        selectedTags = new ArrayList<String>();
        if (busy.isChecked()) {
            selectedTags.add("busy");
            Log.d("Add", "busy to list");
        }
        if (outlets.isChecked()) {
            selectedTags.add("outlets");
            Log.d("Add", "outlets to list");
        }
        if (quiet.isChecked()) {
            selectedTags.add("quiet");
            Log.d("Add", "quiet to list");
        }
        for (String element : selectedTags) {
            Log.d("element", element);
        }
        boolean isSaved = true;
        //boolean isSaved = Util.sendTags(studySpotName, selectedTags); // uncomment out later
        if (isSaved) {
            Log.d("true", "tags were successfully added to database");
        } else {
            Log.d("false", "some error occurred while adding tags to database");
        }
    }
    public void saveReview(android.view.View view){
        final EditText studySpotNewReview = (EditText) findViewById(R.id.WriteAReviewText);
        String newReview = studySpotNewReview.getText().toString();
        Log.d("New Review", newReview);
        boolean sendReview = true; //remove later
        //boolean sendReview = Util.sendReview(studySpotName, newReview); //uncomment out later
        if (sendReview) {
            Log.d("true", "new review was successfully added to database");
        } else {
            Log.d("false", "some error occurred while adding review to database");
        }
    }
}