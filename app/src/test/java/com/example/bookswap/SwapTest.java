package com.example.bookswap;


import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class SwapTest {

    @Test
    public void checkSettingAndGettingLocation(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setLocation("backgroud");
        String location = test.getLocation();
        assertEquals("backgroud",location);
    }

    @Test
    public void checkSettingAndGettingComment(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setComment("backgroud");
        String comment = test.getComment();
        assertEquals("backgroud",comment);
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
        test.setBorrower("backgroud");
        String borrower = test.getBorrower();
        assertEquals("backgroud",borrower);
    }

    public void checkOwner(){
        Book testBook = new Book();
        Swap test = new Swap();
        test.setOwner("backgroud");
        String Owner = test.getOwner();
        assertEquals("backgroud",Owner);
    }
}
