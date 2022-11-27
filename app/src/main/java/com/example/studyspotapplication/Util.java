package com.example.studyspotapplication;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.util.ArrayList;

public class Util {

    public static String sendMessage(String input) {
        ServerThread st = new ServerThread(input);
        while (!st.done) {
            Log.d("myTag", "");
        }
        Log.d("myTag", st.output);
        return st.output;
    }

    public static StudySpot retrieveStudySpot(String name) {
        String json = String.format("{\n" +
                "    \"type\": \"studySpot\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\"\n" +
                "    }\n" +
                "}", name);
        String ss = sendMessage(json);
        if (ss.equals("") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        StudySpotData studySpot = gson.fromJson(ss, StudySpotData.class);
        return new StudySpot(studySpot);
    }

    public static String retrieveStudySpotAddress(String name) {
        // Returns address of study spot name in json
        StudySpot ss = retrieveStudySpot(name);
        if (ss == null) {
            // handle error!
            return null;
        }
        return ss.location;
    }

    public static String retrieveStudySpotTimesOpen(String name) {
        // Returns open times of study spot name in json
        StudySpot ss = retrieveStudySpot(name);
        if (ss == null) {
            // handle error!
            return null;
        }
        return ss.openHours;
    }

    public static Double retrieveStudySpotRating(String name) {
        //Returns average rating
        String json = String.format("{\n" +
                "    \"type\": \"avgReview\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\"\n" +
                "    }\n" +
                "}", name);
        String ss = sendMessage(json);
        if (ss.equals("") || ss == null) {
            // handle error!!!!!!
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, double.class);
    }
    public static Double sendRating(String name, Double rating) {
        // TODO sends new user rating
        // updates average rating in databse,
        // sends back updated average rating
        return 0.0;
    }

    public static Boolean sendTags(String name, ArrayList<String> tags) {
//        Stores tags in database
//        Return true/false if successful
        boolean busy = tags.contains("Busy");
        boolean quiet = tags.contains("Quiet");
        boolean outlets = tags.contains("Outlets");
        String json = String.format(
                "{\n" +
                "    \"type\": \"tags\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\",\n" +
                "        \"busy\": %b,\n" +
                "        \"quiet\": %b,\n" +
                "        \"outlets\": %b\n" +
                "    }\n" +
                "}", name, busy, quiet, outlets);
        String ss = sendMessage(json);
        if (ss.equals("") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, boolean.class);
    }
    public static ArrayList<String> retrieveReviews(String name) {
//        Returns arraylist of all reviews for given study spot name
        String json = String.format(
                "{\n" +
                "    \"type\": \"studySpot\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\"\n" +
                "    }\n" +
                "}", name);
        String ss = sendMessage(json);
        if (ss.equals("") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        Reviews reviews = gson.fromJson(ss, Reviews.class);
        return reviews.reviews;
    }
    public static Boolean sendReview(String name, String review) {
//        Add review to the arraylist of reviews for given study spot
//        Return true/false if successful
        String json = String.format(
                "{\n" +
                "    \"type\": \"sendReview\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\"\n" +
                "        \"review\": \"%s\"\n" +
                "    }\n" +
                "}", name, review);
        String ss = sendMessage(json);
        if (ss.equals("") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, boolean.class);
    }
}

class Reviews {
    ArrayList<String> reviews;
}

// Client Output


// Server Output
// Study Spot
// Array of Study Spots
// Double

// POST
// true/false

//