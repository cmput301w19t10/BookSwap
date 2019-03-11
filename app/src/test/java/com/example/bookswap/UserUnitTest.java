package com.example.bookswap;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserUnitTest {
    private User user = new User( "Stupid", "12346567", "hzhong1@ualberta.ca", "askudhaouhydfushfg", "1231231");
    Review review = new Review("nice", "5.0");

    @Test
    public void getName_isCorrect(){
        assertEquals(user.getName(), "Stupid");
    }

    @Test
    public void getPhone_number_isCorrect(){
        assertEquals(user.getPhone_number(), "12346567");
    }

    @Test
    public void getAddress_isCorrect(){
        assertEquals(user.getAddress(),"askudhaouhydfushfg");
    }

    @Test
    public void getEmail_isCorrect(){
        assertEquals(user.getEmail(), "hzhong1@ualberta.ca");
    }

    @Test
    public void getPassword_isCorrect(){
        assertEquals(user.getPassword(), "1231231");
    }


}
