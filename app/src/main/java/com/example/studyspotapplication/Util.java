package com.example.studyspotapplication;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {
    private static String SERVER = "https://mythic-tenure-340409.wn.r.appspot.com/form";
    private static String LOCAL = "http://10.26.1.222:8080/StudySpotServer/form";
    private static String API_POINT = LOCAL;

    public static String sendMessage(String input) {
        String output = "";
        try {
            URL url = new URL(API_POINT);
            Log.i("myTag", "Start better API call");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.i("myTag", "Connection started with input:\n" + input);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(input);
            out.flush();
            out.close();

            Log.d("myTag", "Before connection.");
            conn.connect();
            Log.d("myTag", "Connected. The response is: " + conn.getResponseCode());
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }
            conn.disconnect();
            Log.d("myTag", "Disconnect with output:\n" + output);
        } catch (Exception e) {
            Log.e("myTag", "Http connection failed: " + e, e);
        }
        return output;
    }

    public static StudySpot retrieveStudySpot(String name) {
        Log.d("myTag", ">> retrieveStudySpot start");
        String json = String.format("{\n" +
                "    \"type\": \"studySpot\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\"\n" +
                "    }\n" +
                "}", name);
        String ss = sendMessage(json);
        Log.d("myTag", ">> retrieveStudySpot after send message");
        if (ss.equals("null") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        StudySpotData studySpot = gson.fromJson(ss, StudySpotData.class);
        StudySpot newSS = new StudySpot(studySpot);
        Log.d("myTag", ">> retrieveStudySpot after gson");
//        ssMap.put(name, newSS);
        return newSS;
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
        if (ss.equals("null") || ss == null) {
            // handle error!!!!!!
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, double.class);
    }
    public static String sendRating(String username, String name, Double rating) {
        // TODO sends new user rating
        // updates average rating in database,
        // sends back updated average rating
        return "0.0";
    }

    public static Boolean sendTags(String username, String name, ArrayList<String> tags) {
//        Stores tags in database
//        Return true/false if successful
        boolean busy = tags.contains("Busy");
        boolean quiet = tags.contains("Quiet");
        boolean outlets = tags.contains("Outlets");
        String json = String.format(
                "{\n" +
                "    \"type\": \"sendTags\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\",\n" +
                "        \"busy\": %b,\n" +
                "        \"quiet\": %b,\n" +
                "        \"outlets\": %b\n" +
                "    }\n" +
                "}", name, busy, quiet, outlets);
        String ss = sendMessage(json);
        if (ss.equals("null") || ss == null) {
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
                "    \"type\": \"getReviews\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\"\n" +
                "    }\n" +
                "}", name);
        String ss = sendMessage(json);
        if (ss.equals("null") || ss == null) {
            // handle error!
            return null;
        }
        ArrayList<String> reviews = new Gson().fromJson(ss, new TypeToken<List<String>>() {}.getType());
        return reviews;
    }

    public static Boolean sendReview(String name, String review) {
//        Add review to the arraylist of reviews for given study spot
//        Return true/false if successful
        String json = String.format(
                "{\n" +
                "    \"type\": \"sendReview\",\n" +
                "    \"data\": {\n" +
                "        \"name\": \"%s\",\n" +
                "        \"review\": \"%s\"\n" +
                "    }\n" +
                "}", name, review);
        String ss = sendMessage(json);
        if (ss.equals("null") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, boolean.class);
    }
    public static Boolean registerUser(String firstName, String lastName, String email, String password) {
        String json = String.format(
                "{\n" +
                "    \"type\": \"register\",\n" +
                "    \"data\": {\n" +
                "        \"firstName\": \"%s\",\n" +
                "        \"lastName\": \"%s\",\n" +
                "        \"email\": \"%s\",\n" +
                "        \"password\": \"%s\"\n" +
                "    }\n" +
                "}", firstName, lastName, email, password);

        String ss = sendMessage(json);
        if(ss.equals("null") || ss == null){
            System.out.println("An error occurred in sending message: Null or empty value");
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, boolean.class);
    }
    public static Boolean loginUser(String email, String password) {
        String json = String.format(
                "{\n" +
                        "    \"type\": \"login\",\n" +
                        "    \"data\": {\n" +
                        "        \"email\": \"%s\",\n" +
                        "        \"password\": \"%s\"\n" +
                        "    }\n" +
                        "}", email, password);
        String ss = sendMessage(json);
        if(ss.equals("null") || ss == null){
            System.out.println("An error occurred in sending message: Null or empty value");
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(ss, boolean.class);
    }

    public static ArrayList<StudySpot> retrieveAllStudySpots() {
        String json = String.format("{\n" +
                "    \"type\": \"allStudySpots\",\n" +
                "    \"data\": {}\n" +
                "}");
        String ss = sendMessage(json);
        if (ss.equals("null") || ss == null) {
            // handle error!
            return null;
        }
        Gson gson = new Gson();
        List<StudySpotData> spotsData = new Gson().fromJson(ss, new TypeToken<List<StudySpotData>>() {}.getType());
        ArrayList<StudySpot> spots = new ArrayList<StudySpot>();
        for(StudySpotData cur : spotsData) {
            spots.add(new StudySpot(cur));
        }
        return spots;
    }
}


class Reviews {
    ArrayList<String> reviews;
}