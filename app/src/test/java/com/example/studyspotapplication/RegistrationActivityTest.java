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
        String password = "password";

        Util testClass = new Util();
        Boolean result = testClass.registerUser(firstName, lastName, email, password);
        assertEquals(false, result);
    }
    @Test
    public void newValidInput(){
        String firstName = "Testing";
        String lastName = "Testing";
        String email = "newemail@usc.edu";
        String password = "12345678";

        Util testClass = new Util();
        Boolean result = testClass.registerUser(firstName, lastName, email, password);
        assertEquals(true, result);
    }
}
