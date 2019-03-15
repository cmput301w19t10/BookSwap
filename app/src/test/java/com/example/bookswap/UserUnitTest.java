package com.example.bookswap;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserUnitTest {
    private User user = new User( "Stupid", "12346567", "hzhong1@ualberta.ca", "askudhaouhydfushfg", "1231231");

    @Test
    public void getName_isCorrect(){
        user.setName("Smart");
        assertEquals(user.getName(), "Smart");
    }

    @Test
    public void getPhone_number_isCorrect(){
        user.setPhone_number("654321");
        assertEquals(user.getPhone_number(), "654321");
    }

    @Test
    public void getAddress_isCorrect(){
        user.setAddress("in heaven");
        assertEquals(user.getAddress(),"in heaven");
    }

    @Test
    public void getEmail_isCorrect(){
        user.setEmail("hzhong2@ualberta.ca");
        assertEquals(user.getEmail(), "hzhong2@ualberta.ca");
    }

    @Test
    public void getPassword_isCorrect(){
        user.setPassword("595542478");
        assertEquals(user.getPassword(), "595542478");
    }

    @Test
    public void getOwnerReviews_isCorrect() {
        Review review = new Review("nice", "5.0");
        user.addOwner_review(review);
        List<Review> list = user.getOwnerReviews();
        assertEquals(list.get(0), review);
    }

    @Test
    public void getBorrowerReviews_isCorrect() {
        Review review = new Review("nice", "5.0");
        user.addBorrower_review(review);
        List<Review> list = user.getBorrowerReviews();
        assertEquals(list.get(0), review);
    }

}
