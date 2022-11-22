package com.example.studyspotapplication;
import android.util.Log;

import java.util.ArrayList;

public class Util {

    public String sendMessage(String input) {
        ServerThread st = new ServerThread(input);
        while (!st.done) {
            Log.d("myTag", "");
        }
        Log.d("myTag", st.output);
        return st.output;
    }

    public String retrieveStudySpotAddress(String name) {
        //Returns address of study spot name in json

        return "";
    }

    public String retrieveStudySpotTimesOpen(String name) {
        //Returns open times of study spot name in json
        return "";
    }

    public Double retrieveStudySpotRating(String name) {
        //Returns average rating
        return 0.0;
    }

    public boolean sendTags(ArrayList<String> tags) {
//        Stores tags in database
//        Return true/false if successful
        return false;
    }
    public ArrayList<String> RetrieveReviews(String name) {
//        Returns arraylist of all reviews for given study spot name
        return new ArrayList<String>();
    }
    public boolean sendReview(String name) {
//        Add review to the arraylist of reviews for given study spot
//        Return true/false if successful
        return false;
    }
}
