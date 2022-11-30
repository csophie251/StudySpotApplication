package com.example.studyspotapplication;
import org.junit.Test;
import static org.junit.Assert.*;

import android.media.MediaDrm;

//@RunWith(JUnit4::class)
public class LoginPageActivityTest {
    @Test
    public void validInput(){
        String email = "Admin@gmail.com";
        String password = "123456";

        LoginPageActivity testClass = new LoginPageActivity();
        Boolean result = testClass.userValidation(email, password);
        assertEquals(true, result);
    }
    public void InputNotInDataBase(){
        String email = "bademail@gmail.com";
        String password = "123456";

        LoginPageActivity testClass = new LoginPageActivity();
        Boolean result = testClass.userValidation(email, password);
        assertEquals(false, result);
    }
}