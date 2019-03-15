package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)

public class BacceptActivityTest extends ActivityTestRule<BAcceptActivity> {

    private Solo solo;


    public BacceptActivityTest() {
        super(BAcceptActivity.class);
    }

    @Rule
    public ActivityTestRule<BAcceptActivity> rule =
            new ActivityTestRule<>(BAcceptActivity.class, true, false
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
    public void checkopen() {
        solo.assertCurrentActivity("wrong activity", BAcceptActivity.class);
        assertTrue(solo.searchText("title"));
        assertTrue(solo.searchText("author"));
    }

    @Test
    public void checkdialog() {
        solo.assertCurrentActivity("wrong activity", BAcceptActivity.class);
        solo.clickOnButton("Swap");
        assertTrue(solo.searchText("Yes"));
        assertTrue(solo.searchText("No"));
    }

    @After
    public void end(){
        finishActivity();
    }

}
