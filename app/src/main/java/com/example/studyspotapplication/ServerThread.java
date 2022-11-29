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

    public ServerThread(String input) {
        this.input = input;
        this.output = "";
        this.done = false;
        this.start();
    }

    public void run() {
        InputStream is = null;

        try {
            URL url = new URL("http://10.25.149.239:8080/StudySpotServer/FormServlet");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            conn.connect();
            Log.d("myTag", "connected");

            int code = conn.getResponseCode();
            Log.d("myTag", "The response is: " + code);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }
            conn.disconnect();
            this.done = true;
        } catch (Exception e) {
            Log.e("myTag", "Http connection failed: ", e);
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
