package com.example.studyspotapplication;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//https://www.youtube.com/watch?v=LCrhddpsgKU
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class LoginPageActivity extends AppCompatActivity {
    //define UI elements
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //set connection between UI components and private variables
        eName = findViewById(R.id.userName);
        ePassword = findViewById(R.id.password);
        eLogin = findViewById(R.id.btnLogin);
        eGuest = findViewById(R.id.continueAsGuest);
        //LOGIN BUTTON CLICKED
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save the user inputted username and password
                String inputName = eName.getText().toString();
                String inputPass = ePassword.getText().toString();
                //check if the user didn't input a name or password
                if(inputName.isEmpty() || inputPass.isEmpty()){
                    //display error message
                    Toast.makeText(LoginPageActivity.this, "Username or Password is missing. Please try again.", Toast.LENGTH_SHORT).show();
                }else{//otherwise, validate against database
                    validateUser(inputName, inputPass);
                }
            }
        });

        eGuest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //TODO: REDIRECT TO NEW ACTIVITY [GUEST MAP PAGE]

                Intent intent = new Intent(LoginPageActivity.this, GuestMapsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void validateUser(String username, String password) {
        new Thread() {
            @Override
            public void run() {
                boolean res = Util.loginUser(username, password);
                if (!res) {
                    runOnUiThread(() -> Toast.makeText(LoginPageActivity.this, "The Username or Password you entered was incorrect. Please try again.", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(LoginPageActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                        //TODO: REDIRECT TO NEW ACTIVITY [AUTHENTICATED MAP PAGE]
                        Intent intent = new Intent(LoginPageActivity.this, MapsActivity.class);
                        intent.putExtra("username", username); //send the username
                        startActivity(intent);
                    });
                }
            }
        }.start();
    }
}