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
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//
    private EditText efirstName;
    private EditText elastName;
    private EditText eemailAddress;
    private EditText epassWord;

    private Button singUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        efirstName = findViewById(R.id.firstName);
        elastName = findViewById(R.id.lastName);
        eemailAddress = findViewById(R.id.emailAddress);
        epassWord = findViewById(R.id.password);
        singUp = findViewById(R.id.signUp);


        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean reDirect = true;
                String firstName = efirstName.getText().toString();
                String lastName = elastName.getText().toString();
                String emailAddress = eemailAddress.getText().toString();
                String passWord = epassWord.getText().toString();

                pattern = Pattern.compile(emailPattern);
                emailMatcher = pattern.matcher(emailAddress.toString());

                if(firstName.toString().isEmpty() || lastName.toString().isEmpty() || emailAddress.toString().isEmpty() || passWord.toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please complete all fields before submitting!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!emailMatcher.matches()){
                    Toast.makeText(RegistrationActivity.this, "Please enter a correct email before before submitting!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(passWord.length() < 8 || passWord.length() > 32){
                    Toast.makeText(RegistrationActivity.this, "Password entered was in the wrong format!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                userValidation returns true only when given the same email and password
//                but user can still register with an email that exists in our database if they enter a different
//                password
                validateUser(firstName, lastName, emailAddress, passWord);
            }
        });
        
    }

    public void validateUser(String firstname, String lastname, String username, String password) {
        new Thread() {
            @Override
            public void run() {
                boolean res = Util.registerUser(firstname, lastname, username, password);
                if (!res) {
                    runOnUiThread(() -> Toast.makeText(RegistrationActivity.this, "Please enter an email that is not associated with an already registered user!", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(RegistrationActivity.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, MapsActivity.class);
                        intent.putExtra("username", username); //send the username
                        startActivity(intent);
                    });
                }
            }
        }.start();
    }
}