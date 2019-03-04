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

public class ORequestActivityTest extends ActivityTestRule<ORequestedActivity> {

    private Solo solo;


    public ORequestActivityTest() {
        super(ORequestedActivity.class);
    }

    @Rule
    public ActivityTestRule<ORequestedActivity> rule =
            new ActivityTestRule<>(ORequestedActivity.class, true, true
            );

    @Before
    public void setUp() {
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }


}
