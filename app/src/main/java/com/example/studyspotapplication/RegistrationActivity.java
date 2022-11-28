package com.example.studyspotapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    Pattern pattern;
    Matcher emailMatcher;
    Matcher passwordMatcher;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
    private EditText firstName;
    private EditText lastName;
    private EditText emailAddress;
    private EditText passWord;
    private Button singUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailAddress = findViewById(R.id.emailAddress);
        passWord = findViewById(R.id.password);
        singUp = findViewById(R.id.signUp);

        pattern = Pattern.compile(passwordPattern);
        passwordMatcher = pattern.matcher(passWord.toString());

        pattern = Pattern.compile(emailPattern);
        emailMatcher = pattern.matcher(emailAddress.toString());

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean reDirect = true;
                if(firstName.toString().isEmpty() || lastName.toString().isEmpty() || emailAddress.toString().isEmpty() || passWord.toString().isEmpty()){
                    reDirect = false;
                    Toast.makeText(RegistrationActivity.this, "Please complete all fields!", Toast.LENGTH_SHORT).show();
                }
                //need to also ensure that email doesn't already exists in the database
                if(!emailMatcher.matches()){
                    reDirect = false;
                    Toast.makeText(RegistrationActivity.this, "Email entered was in the wrong format!", Toast.LENGTH_SHORT).show();
                }
                if(!passwordMatcher.matches() || passWord.length() < 8 || passWord.length() > 32){
                    reDirect = false;
                    Toast.makeText(RegistrationActivity.this, "Password entered was in the wrong format!", Toast.LENGTH_SHORT).show();
                }
                if(reDirect){
                    goToLoginPage(view);
                }
            }
        });
        
    }

    public void goToLoginPage(android.view.View view){
        Intent intent = new Intent (this, LoginPageActivity.class);
        startActivity(intent);
    }
}