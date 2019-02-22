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
    public void BookAddAndGet_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        Book newBook = new Book("1","1","1","1","1");
        u.AddNewBook(newBook);
        ArrayList testArray;
        testArray = u.getBooks(1);
        assertEquals("1",testArray.get(0));
    }

    @Test
    public void AddandGetUserPassword_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        u.addPassword("1","1");
        String testPassword = u.getPassword("1");
        assertEquals("1",testPassword);
    }

    @Test
    public void AddandGetUserEmail_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        u.addEmail("1","1");
        String testEmail = u.getEmail("1");
        assertEquals("1",testEmail);
    }

    @Test
    public void AddandGetUserAddress_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        u.addAddress("1","1");
        String testAddress = u.getAddress("1");
        assertEquals("1",testAddress);
    }

    @Test
    public void AddandGetUserPhone_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        u.addPhone("1","1");
        String testPhone = u.getPhone("1");
        assertEquals("1",testPhone);
    }

    @Test
    public void chageStatus_isCorrect(){
        DataBaseUtil u = new DataBaseUtil();
        u.changeStatus(2);
        ArrayList testArray;
        testArray = u.getBooks(1);
        assertEquals(2,testArray.get(2));
    }
}
