package com.example.bowenhu.bookswap;

import android.database.DatabaseUtils;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getBookArray_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        ArrayList<ArrayList> testList = u.Book();
        assertTrue(testList.isEmpty());
    }

    public
}