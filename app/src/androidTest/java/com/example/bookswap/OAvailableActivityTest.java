package com.example.bookswap;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class OAvailableActivityTest extends ActivityTestRule<OAvailableActivity> {
    private Solo solo;
    public OAvailableActivityTest(){
        super(OAvailableActivity.class);
    }

    @Rule
    public ActivityTestRule<OAvailableActivity> rule =
            new ActivityTestRule<>(OAvailableActivity.class,true,true);
    @Before
    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(),rule.getActivity());
    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    // @Test
    //public void checkAvailable(){

    //}



}
