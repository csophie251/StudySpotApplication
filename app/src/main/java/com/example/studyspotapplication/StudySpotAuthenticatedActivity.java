package com.example.studyspotapplication;

import static java.lang.Double.parseDouble;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudySpotAuthenticatedActivity extends AppCompatActivity {
    public String studySpotName;
    public ArrayList<String> selectedTags;
    public ArrayList<String> reviewsList;
    public String username;
    CheckBox busy;
    CheckBox outlets;
    CheckBox quiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spot_authenticated);
        Intent intent = getIntent();

        studySpotName = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        Log.d("Study Spot Name: ", studySpotName);

        String studySpotRating = "No Rating";
        String studySpotAddress = "No Address";
        String studySpotOpenTimes = "No Openings";
//      if(Util.retrieveStudySpotRating(studySpotName) != null){
//          studySpotRating = Util.retrieveStudySpotRating(studySpotName).toString();
//      }
//      else{
//          Log.d("null:", "null rating");
//      }
        if(Util.retrieveStudySpotAddress(studySpotName) != null){
            studySpotAddress = Util.retrieveStudySpotAddress(studySpotName).toString();
        }
        else{
            Log.d("null:", "null address");
        }
//        if(Util.retrieveStudySpotTimesOpen(studySpotName) != null){
//           studySpotOpenTimes = Util.retrieveStudySpotTimesOpen(studySpotName).toString();
//        }
//        else{
//          Log.d("null:", "null times open");
//        }
        Log.d("Study Spot Information:", (studySpotRating + ", " +studySpotAddress  + ", " + studySpotOpenTimes));

        //Set text
        final TextView name = findViewById(R.id.StudySpotName);
        name.setText(studySpotName);
        final TextView rating = findViewById(R.id.StudySpotRating);
        rating.setText(studySpotRating);
        final TextView location = findViewById(R.id.StudySpotLocation);
        location.setText(studySpotAddress);
        final TextView timesOpen= findViewById(R.id.StudySpotTimesOpen);
        timesOpen.setText(studySpotOpenTimes);
        Log.d("Set Study Info:", "finished");

        //Tags
        selectedTags = new ArrayList<String>();
        busy = findViewById(R.id.BusyTag);
        outlets = findViewById(R.id.OutletTag);
        quiet = findViewById(R.id.QuietTag);

        reviewsList = new ArrayList<String>();

//        if(Util.retrieveReviews(studySpotName) == null || Util.retrieveReviews(studySpotName).isEmpty()){
//            reviewsList.add("No Reviews Yet!");
//        }
//        else{
//            reviewsList = Util.retrieveReviews(studySpotName);
//        }

        final TextView studySpotReviews= findViewById(R.id.PlaceReviewsHere);
        String text = "";
        for(int i =0; i < (int) reviewsList.size(); i++){
            text += reviewsList.get(i);
            text += '\n';
        }
        studySpotReviews.setText(text);

        final ImageView studySpotImage = findViewById(R.id.imageView);
        int image = Images.getImage(studySpotName);
        studySpotImage.setImageResource(image);

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
    }

    public void saveRating(android.view.View view) {
        final RatingBar ratingBar = findViewById(R.id.UserRating);
        Float rating =  ratingBar.getRating();
        Double userRating = parseDouble(rating.toString());
        Log.d("User Rating: ",  userRating.toString());
        Double newRating = Util.sendRating(username, studySpotName, userRating);
        Log.d("Updated Rating: ",  newRating.toString());
        final TextView studySpotRating = findViewById(R.id.StudySpotRating);
        studySpotRating.setText(newRating.toString());
    }
    public ArrayList<String> saveTags(android.view.View view)  {
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
        for (String element : selectedTags) {
            Log.d("element", element);
        }
        boolean isSaved = Util.sendTags(studySpotName, selectedTags);
        if (isSaved) {
            Log.d("true", "tags were successfully added to database");
        } else {
            Log.d("false", "some error occurred while adding tags to database");
        }
        return selectedTags;
    }
    public void saveReview(android.view.View view){
        final EditText studySpotNewReview = findViewById(R.id.WriteAReviewText);
        String newReview = studySpotNewReview.getText().toString();
        Log.d("New Review", newReview);
        boolean sendReview = Util.sendReview(studySpotName, newReview);
        if (sendReview) {
            Log.d("true", "new review was successfully added to database");
        } else {
            Log.d("false", "some error occurred while adding review to database");
        }
    }
}