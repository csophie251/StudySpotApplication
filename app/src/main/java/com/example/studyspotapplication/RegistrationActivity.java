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
                    reDirect = false;
                    Toast.makeText(RegistrationActivity.this, "Please complete all fields before submitting!", Toast.LENGTH_SHORT).show();
                }

                if(!emailMatcher.matches()){
                    reDirect = false;
                    Toast.makeText(RegistrationActivity.this, "Please enter a correct email before before submitting!", Toast.LENGTH_SHORT).show();
                }

//                person who already has an account attempts to register
//                Util util = new Util();
//                if(util.registerUser(firstName.toString(), lastName.toString(), emailAddress.toString(), passWord.toString())){
//                    reDirect = false;
//                    Toast.makeText(RegistrationActivity.this, "Please enter an email that is not associated with an already registered user!", Toast.LENGTH_SHORT).show();
//                }

                if(passWord.length() < 8 && passWord.length() > 32){
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

    public Boolean userValidation(String firstName, String lastName, String email, String password) {
        Util util = new Util();
        return !(util.registerUser(firstName, lastName, email, password));
    }
}