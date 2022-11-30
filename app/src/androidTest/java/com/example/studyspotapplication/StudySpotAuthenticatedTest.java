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
//    @Test
//    public void testSaveRating(){
//        Double oldRating = Util.retrieveStudySpotRating("Leavey Library");
//        Double userRating = 4.5;
//        Double expectedRating = (oldRating + userRating)/ 2;
//        Double newRating = Util.sendRating("Leavey Library", userRating);
//        assertEquals(expectedRating, newRating);
//    }
//
//    @Test
//    public void testSaveTags(){
//        StudySpotAuthenticatedActivity StudySpotAuthenticatedClass = new StudySpotAuthenticatedActivity();
//        CheckBox busy =  StudySpotAuthenticatedClass.findViewById(R.id.BusyTag);
//        CheckBox quiet =  StudySpotAuthenticatedClass.findViewById(R.id.QuietTag);
//        CheckBox outlets =  StudySpotAuthenticatedClass.findViewById(R.id.OutletTag);
//
//        ArrayList<String> expected1 = new ArrayList<String>();
//        expected1.add("Busy");
//        expected1.add("Outlets");
//        expected1.add("Quiet");
//        boolean actual = Util.sendTags("Leavey Library", expected1);
//        assertTrue(actual);
//        busy.setChecked(true);
//        quiet.setChecked(true);
//        outlets.setChecked(true);
//        ArrayList<String> actualList1 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected1, actualList1);
//
//
//        ArrayList<String> expected2 = new ArrayList<String>();
//        expected2.add("Busy");
//        expected2.add("Outlets");
//        boolean actual2 = Util.sendTags("Leavey Library", expected2);
//        assertTrue(actual2);
//        busy.setChecked(true);
//        quiet.setChecked(false);
//        outlets.setChecked(true);
//        ArrayList<String> actualList2 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected2, actualList2);
//
//
//        ArrayList<String> expected3 = new ArrayList<String>();
//        expected3.add("Busy");
//        expected3.add("Quiet");
//        boolean actual3 = Util.sendTags("Leavey Library", expected3);
//        assertTrue(actual3);
//        busy.setChecked(true);
//        quiet.setChecked(true);
//        outlets.setChecked(false);
//        ArrayList<String> actualList3 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected3, actualList3);
//
//
//        ArrayList<String> expected4 = new ArrayList<String>();
//        expected4.add("Outlets");
//        expected4.add("Quiet");
//        boolean actual4 = Util.sendTags("Leavey Library", expected4);
//        assertTrue(actual4);
//        busy.setChecked(false);
//        quiet.setChecked(true);
//        outlets.setChecked(true);
//        ArrayList<String> actualList4 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected4, actualList4);
//
//        ArrayList<String> expected5 = new ArrayList<String>();
//        expected5.add("Busy");
//        boolean actual5 = Util.sendTags("Leavey Library", expected5);
//        assertTrue(actual5);
//        busy.setChecked(true);
//        quiet.setChecked(false);
//        outlets.setChecked(false);
//        ArrayList<String> actualList5 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected5, actualList5);
//
//
//        ArrayList<String> expected6 = new ArrayList<String>();
//        expected6.add("Outlets");
//        boolean actual6 = Util.sendTags("Leavey Library", expected6);
//        assertTrue(actual6);
//        busy.setChecked(false);
//        quiet.setChecked(false);
//        outlets.setChecked(true);
//        ArrayList<String> actualList6 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected6, actualList6);
//
//
//        ArrayList<String> expected7 = new ArrayList<String>();
//        expected6.add("Quiet");
//        boolean actual7 = Util.sendTags("Leavey Library", expected7);
//        assertTrue(actual7);
//        busy.setChecked(false);
//        quiet.setChecked(true);
//        outlets.setChecked(false);
//        ArrayList<String> actualList7 =  StudySpotAuthenticatedClass.saveTags();
//        assertEquals(expected7, actualList7);
//    }
//
//    @Test
//    public void testSaveReview(){
//        boolean actual = Util.sendReview("Leavey Library", "new test review");
//        assertTrue(actual);
//        ArrayList<String> actualList = Util.retrieveReviews("Leavey Library");
//        assertTrue(actualList.contains("new test review"));
//    }
}