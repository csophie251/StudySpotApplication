package com.example.studyspotapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//@RunWith(JUnit4::class)
public class RegistrationActivityTest {
    @Test

    public void inputAlreadyInDatabase(){
        String firstName = "Suna";
        String lastName = "Yimer";
        String email = "admin@usc.edu";
        String password = "123456";

        LoginPageActivity testClass = new LoginPageActivity();
        Boolean result = testClass.userValidation(email, password);
        assertEquals(false, result);
    }

    public void newValidInput(){
        String firstName = "Suna";
        String lastName = "Yimer";
        String email = "admin@usc.edu";
        String password = "password";

        LoginPageActivity testClass = new LoginPageActivity();
        Boolean result = testClass.userValidation(email, password);
        assertEquals(true, result);
    }
}