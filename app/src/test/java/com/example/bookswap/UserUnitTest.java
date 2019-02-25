package com.example.bookswap;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserUnitTest {

    @Test
    public void getName_isCorrect(){
        User user = new User( "Stupid", "12346567", "hzhong1@ualberta.ca", "askudhaouhydfushfg");
        assertEquals(user.getName(), "Stupid");
    }

    @Test
    public void getPhone_number_isCorrect(){
        User user = new User("Stupid", "12346567", "hzhong1@ualberta.ca", "askudhaouhydfushfg");
        assertEquals(user.getPhone_number(), "12346567");
    }

    @Test
    public void getAddress_isCorrect(){
        User user = new User("Stupid", "12346567", "hzhong1@ualberta.ca", "askudhaouhydfushfg");
        assertEquals(user.getAddress(),"askudhaouhydfushfg");
    }

    @Test
    public void getEmail_isCorrect(){
        User user = new User("Stupid", "12346567", "hzhong1@ualberta.ca", "askudhaouhydfushfg");
        assertEquals(user.getEmail(), "hzhong1@ualberta.ca");
    }
}
