package com.example.studyspotapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//https://www.youtube.com/watch?v=LCrhddpsgKU
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


public class LoginPageActivity extends AppCompatActivity {
    //define UI elements
    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private TextView eGuest;
    //temp username and password
    private String TestUserName = "Admin";
    private String TestPassword = "12345678";

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
                    //TEST USING ADMIN
                    if(!userValidation(inputName, inputPass)){ //incorrect/cant find in database
                        Toast.makeText(LoginPageActivity.this, "The Username or Password you entered was incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                    }else{//user input is found in database
                        Toast.makeText(LoginPageActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                        //TODO: REDIRECT TO NEW ACTIVITY [HOME PAGE]
                        Intent intent = new Intent(LoginPageActivity.this, MapsActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

        eGuest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //TODO: REDIRECT TO NEW ACTIVITY [HOME PAGE]
                Intent intent = new Intent(LoginPageActivity.this, GuestMapsActivity.class);
                startActivity(intent);
            }
        });
    }
    //VALIDATING USERNAME INPUT AND PASSWORD --> need to change to retrieve from database of user info
    private boolean userValidation(String username, String password){
        //for the database retrieval
        if(username.equals(TestUserName) && password.equals(TestPassword)){
            return true;
        }else{
            return false;
        }
    }
}