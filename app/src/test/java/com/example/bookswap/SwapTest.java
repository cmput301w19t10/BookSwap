package com.example.bookswap;


import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class SwapTest {

    @Test
    public void checkSettingAndGettingLocation(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setLocation("1");
        String location = test.getLocation();
        assertEquals("1",location);
    }

    @Test
    public void checkSettingAndGettingComment(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setComment("1");
        String comment = test.getComment();
        assertEquals("1",comment);
    }

    public void checkDate(){
        Book testBook = new Book();
        Swap test = new Swap();
        Date date = new Date("2019-01-01");
        test.setDate(date);
        Date testDate = test.getDate();
        assertEquals(date,testDate);
    }

    public void checkBorrower(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setBorrower("1");
        String borrower = test.getBorrower();
        assertEquals("1",borrower);
    }

    public void checkOwner(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setOwner("1");
        String Owner = test.getOwner();
        assertEquals("1",Owner);
    }
}
