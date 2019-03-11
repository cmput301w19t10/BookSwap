package com.example.bookswap;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * UI test for use case 1
 */
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

    /**
     * check add available book
     * click add menu to go to edit book activity
     * then at least enter book title and author name
     * click save menu, the book should be saved
     */
    @Test
    public void checkAddAvailable(){
        solo.assertCurrentActivity("WrongActivity",OAvailableActivity.class);
        solo.clickOnView(solo.getView(R.id.action_create));
        solo.assertCurrentActivity("Wrong Activity",EditBookActivity.class);
        solo.enterText((EditText) solo.getView(R.id.etTitle),"test book");
        solo.enterText((EditText) solo.getView(R.id.etAuthor),"test author");
        // TODO: test image button
        solo.clickOnView(solo.getView(R.id.action_save));
        assertTrue(solo.waitForText("test book",1,2000));
        assertTrue(solo.waitForText("test author",1,2000));
    }
    /**
     * check add available book
     * click add menu to go to edit book activity
     * then at least enter book title and author name
     * click save menu, the book should be saved
     * in the available book list activity, click the saved book, then in the edit book activty
     * click delete,the book should be removed from data.
     */

    @Test
    public void checkDeleteAvailable(){
        OAvailableActivity activity = (OAvailableActivity) solo.getCurrentActivity();
        solo.clickOnView(solo.getView(R.id.action_create));
        solo.assertCurrentActivity("Wrong Activity",EditBookActivity.class);
        solo.enterText((EditText) solo.getView(R.id.etTitle),"test book");
        solo.enterText((EditText) solo.getView(R.id.etAuthor),"test author");
        solo.clickOnView(solo.getView(R.id.action_save));
        assertTrue(solo.waitForText("test book",1,2000));
        assertTrue(solo.waitForText("test author",1,2000));

        solo.clickInList(0);
        solo.clickOnView(solo.getView(R.id.action_delete));
        solo.assertCurrentActivity("Wrong Activity", OAvailableActivity.class);
        assertFalse(solo.searchText("test book"));
        assertFalse(solo.searchText("test author"));
    }

    /**
     * check add available book
     * click add menu to go to edit book activity
     * then at least enter book title and author name
     * click save menu, the book should be saved
     * in the available book list activity, click the saved book,then in the edit book activty
     * change the title and description, and click save button
     * the book information should be updated, previous data should no longer exist.
     */

    @Test
    public void checkEditBook(){
        OAvailableActivity activity = (OAvailableActivity) solo.getCurrentActivity();
        solo.clickOnView(solo.getView(R.id.action_create));
        solo.assertCurrentActivity("Wrong Activity",EditBookActivity.class);
        solo.enterText((EditText) solo.getView(R.id.etTitle),"test book");
        solo.enterText((EditText) solo.getView(R.id.etAuthor),"test author");
        solo.enterText((EditText) solo.getView(R.id.etDescription),"test description");
        solo.clickOnView(solo.getView(R.id.action_save));
        assertTrue(solo.waitForText("test book",1,2000));
        assertTrue(solo.waitForText("test author",1,2000));
        assertTrue(solo.waitForText("test description",1,2000));

        solo.clickInList(0);
        solo.clearEditText((EditText) solo.getView(R.id.etTitle));
        solo.clearEditText((EditText) solo.getView(R.id.etDescription));
        solo.enterText((EditText) solo.getView(R.id.etTitle),"Title Test");
        solo.enterText((EditText) solo.getView(R.id.etDescription),"new description");
        solo.clickOnView(solo.getView(R.id.action_save));
        assertFalse(solo.searchText("test book"));
        assertFalse(solo.searchText("test description"));
        assertTrue(solo.waitForText("Title Test",1,2000));
        assertTrue(solo.waitForText("new description",1,2000));

    }

    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
