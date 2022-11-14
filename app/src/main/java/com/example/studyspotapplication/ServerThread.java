package com.example.studyspotapplication;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerThread extends Thread {

    private PrintWriter out;

    public ServerThread() {
        this.start();
    }

    public void run() {
        InputStream is = null;

        try {
            URL url = new URL("http://192.168.192.1:8080/HelloWorld/FormServlet");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Log.d("myTag", "connect");

            conn.connect();
            Log.d("myTag", "connected");

            int response = conn.getResponseCode();
            Log.d("myTag", "The response is: " + response);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            PrintStream ps = new PrintStream(new BufferedOutputStream(conn.getOutputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                Log.i("myTag", line);
            }

//            String contentAsString = br.toString() ;
            conn.disconnect();

            Log.i("myTag", line);
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
