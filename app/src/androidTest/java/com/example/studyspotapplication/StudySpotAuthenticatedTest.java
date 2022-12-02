package com.example.studyspotapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import android.widget.CheckBox;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class StudySpotAuthenticatedTest {
    @Test
    public void testSaveReview(){
        boolean actual = Util.sendReview("username", "name", "new review");
        assertTrue(actual);
        ArrayList<Review> actualList = Util.retrieveReviews("Leavey Library");
        assertTrue(actualList.contains("new test review"));
    }



}