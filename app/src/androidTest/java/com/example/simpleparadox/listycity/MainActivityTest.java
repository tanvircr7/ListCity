package com.example.simpleparadox.listycity;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * Test class for MainActivity. All the UI tests are written here. Robotium test framework is used
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest{

    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{

        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }
    /**
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    @Test
    public void checkListItemClicks(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Khulna");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Khulna", 1, 1000));

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView listView = activity.cityList;
        String city1 = (String) listView.getItemAtPosition(0);
        assertEquals("Khulna", city1);


        String string1 = listView.getItemAtPosition(0).toString();
        solo.clickOnText(string1);
        solo.assertCurrentActivity("Wrong Activity", ShowActivity.class);

        solo.clickOnButton("GO BACK");
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);


        solo.clickOnButton("ClEAR ALL");
        assertFalse(solo.searchText("Khulna"));

    }




    @Test
    public void checkActivitySwitching(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Khulna");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Khulna", 1, 2000));

        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Dhaka");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Dhaka", 1, 2000));

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView listView = activity.cityList;
        String string1 = listView.getItemAtPosition(0).toString();
        solo.clickOnText(string1);
        solo.assertCurrentActivity("Wrong Activity", ShowActivity.class);
        String text = ((TextView) solo.getView(R.id.textview)).getText().toString();
        assertEquals(string1, text);
        solo.clickOnButton("GO BACK");
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

        String string2 = listView.getItemAtPosition(1).toString();
        solo.clickOnText(string2);
        solo.assertCurrentActivity("Wrong Activity", ShowActivity.class);
        text = ((TextView) solo.getView(R.id.textview)).getText().toString();
        assertEquals(string2, text);
        solo.clickOnButton("GO BACK");
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }

    /**
     * Close activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}