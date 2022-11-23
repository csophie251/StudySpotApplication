package com.example.studyspotapplication;
import android.util.Log;
import android.widget.TextView;

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

    public static String retrieveStudySpotAddress(String name) {
        //Returns address of study spot name in json

        return "testAddress";
    }

    public static String retrieveStudySpotTimesOpen(String name) {
        //Returns open times of study spot name in json
        return "testOpenTimes";
    }

    public static Double retrieveStudySpotRating(String name) {
        //Returns average rating
        return 5.0;
    }
    public static Double sendRating(String name, Double rating) {
//        sends new user rating
       // updates average rating in databse,
     //   sends back updated average rating
        return 4.0;
    }

    public static boolean sendTags(String name, ArrayList<String> tags) {
//        Stores tags in database
//        Return true/false if successful
        return true;
    }
    public static ArrayList<String> retrieveReviews(String name) {
//        Returns arraylist of all reviews for given study spot name
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("GREat PLACE!");
        arr.add("loves it here!");
        arr.add("it was okay");
        arr.add("not enough lighting");

        return arr;
    }
    public static boolean sendReview(String name, String review) {
//        Add review to the arraylist of reviews for given study spot
//        Return true/false if successful
        return false;
    }
}
