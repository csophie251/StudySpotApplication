package com.example.studyspotapplication;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerThread extends Thread {
    String input;
    String output;
    boolean done;
    private static String SERVER = "https://mythic-tenure-340409.wn.r.appspot.com/form";
    private static String LOCAL = "http://10.26.1.222:8080/StudySpotServer/form";
    private static String API_POINT = LOCAL;

    public ServerThread(String input) {
        this.input = input;
        this.output = "";
        this.done = false;
        this.start();
    }

    public void run() {
        InputStream is = null;

        try {
            URL url = new URL(API_POINT);
            Log.i("myTag", "Start API call");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.i("myTag", "Connection started");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(input);
            osw.flush();
            osw.close();
            os.close();  //don't forget to close the OutputStream
            Log.i("myTag", "Write call data");
            Log.i("myTag", input);
            conn.connect();
            Log.d("myTag", "Connect to server");

            int code = conn.getResponseCode();
            Log.d("myTag", "The response is: " + code);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }
            conn.disconnect();
            Log.d("myTag", "Disconnect");
            Log.d("myTag", output);
            this.done = true;
            Log.d("myTag", String.valueOf(this.done));
            Log.d("myTag", "");
        } catch (Exception e) {
            Log.e("myTag", "Http connection failed: " + e.toString(), e);
        }
    }

    // TODO: sends a message
    public int sendMessage(JSONObject json) {
        return 0;
    }

    // TODO: gets a message
    public JSONObject readMessage() {
        return null;
    }

}
