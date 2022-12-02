package com.example.studyspotapplication;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginPageActivityTest {
    Util util;

    @Test
    public void validInput(){
        util = new Util();
        assertNotNull(util);
        String email = "admin@usc.edu";
        String password = "password";
        System.out.println(" Result: " + util.loginUser(email, password));
        boolean result = util.loginUser(email, password);
        assertEquals(true, result);
    }
    @Test
    public void InputNotInDataBase(){
        String email = "bademail@gmail.com";
        String password = "123456";

        util = new Util();
        boolean result = util.loginUser(email, password);

        assertEquals(false, result);
    }
}
