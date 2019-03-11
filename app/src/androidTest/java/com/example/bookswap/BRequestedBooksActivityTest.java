package com.example.bookswap;
import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.ListView;

import android.support.test.runner.AndroidJUnit4;

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
public class BRequestedBooksActivityTest extends ActivityTestRule<BRequestedBooksActivity>{
    private Solo solo;

    public BRequestedBooksActivityTest(){
        super(BRequestedBooksActivity.class);
    }

    @Rule
    public ActivityTestRule<BRequestedBooksActivity> rule =new ActivityTestRule<>(BRequestedBooksActivity.class,true,true);

    @Before
    public void setUp(){
        solo = new Solo(getInstrumentation(), rule.getActivity());

    }
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    @Test
    public void checkBook(){
        solo.assertCurrentActivity("Wrong Activity",BRequestedBooksActivity.class);
        assertTrue(solo.searchText("title"));
    }

}
