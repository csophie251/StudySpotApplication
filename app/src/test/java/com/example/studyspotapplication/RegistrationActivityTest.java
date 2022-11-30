package com.example.studyspotapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//@RunWith(JUnit4::class)
public class RegistrationActivityTest {
    @Test

    public void inputAlreadyInDatabase(){
        String firstName = "Testing";
        String lastName = "Testing";
        String email = "admin@usc.edu";
        String password = "123456";

        RegistrationActivity testClass = new RegistrationActivity();
        Boolean result = testClass.userValidation(firstName, lastName, email, password);
        assertEquals(false, result);
    }

    public void newValidInput(){
        String firstName = "Testing";
        String lastName = "Testing";
        String email = "newemail@usc.edu";
        String password = "123456";

        RegistrationActivity testClass = new RegistrationActivity();
        Boolean result = testClass.userValidation(firstName, lastName, email, password);
        assertEquals(true, result);
    }
}