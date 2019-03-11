package com.example.bookswap;
import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)

public class ORequestActivityTest extends ActivityTestRule<ORequestedActivity> {

    private Solo solo;
    private Book book = new Book("title", "author", "Available", "description");
    private ArrayList<Book> requestedList = new ArrayList<Book>();


    public ORequestActivityTest() {
        super(ORequestedActivity.class);
    }

    @Rule
    public ActivityTestRule<ORequestedActivity> rule =
            new ActivityTestRule<>(ORequestedActivity.class, true, false
            );

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.putExtra("TEST", true);
        Book book = new Book("title", "author", "Available", "descp");
        intent.putExtra("Book", book);
        solo = new Solo(getInstrumentation(), rule.getActivity());
        launchActivity(intent);
    }

    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    @Test
    public void checkbook(){
//        DataBaseUtil u = new DataBaseUtil("Test");

        solo.assertCurrentActivity("wrong activity" , ORequestedActivity.class);

        assertTrue(solo.searchText("title"));
    }

    // Bad test, need to think how to do nested activity test without database
    @Test
    public void checkbutton(){
        ORequestedActivity activity = (ORequestedActivity) solo.getCurrentActivity();
        solo.assertCurrentActivity("wrong activity", ORequestedActivity.class);

        solo.clickOnButton("View Request");
        solo.assertCurrentActivity("Wrong Activity", ORequestedUserActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity" , ORequestedActivity.class);
    }

    @After
    public void end() {
        finishActivity();
    }
}
