package com.example.bookswap;
import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)

public class ORequestUserActivityTest extends ActivityTestRule<ORequestedUserActivity> {

    private Solo solo;


    public ORequestUserActivityTest() {
        super(ORequestedUserActivity.class);
    }

    @Rule
    public ActivityTestRule<ORequestedUserActivity> rule =
            new ActivityTestRule<>(ORequestedUserActivity.class, true, true
            );

    @Before
    public void setUp() {
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    @Test
    public void checkbook() {
        solo.assertCurrentActivity("wrong activity", ORequestedUserActivity.class);
        assertTrue(solo.searchText("title"));
        assertTrue(solo.searchText("yifu1"));
        assertTrue(solo.searchText("yifu2"));
    }
}
