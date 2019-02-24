package com.example.bookswap;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Requested_List_test {
    private Requested_list list = new Requested_list();
    private User user1 = new User("bowen") ;
    private User user2 = new User("yifu") ;

    @Test
    public void t_accept(){
        list.add(user1);
        list.add(user2);
        list.accept(1);
        assertTrue(list.size() == 0);
        list.clear();
    }

    @Test
    public void t_cancel(){
        list.add(user1);
        list.add(user2);
        list.cancel(0);
        assertTrue(list.size() == 1);
        list.clear();
    }

    @Test
    public void t_clear(){
        list.add(user1);
        list.add(user2);
        list.clear();
        assertTrue(list.size() == 0);
        list.clear();
    }

    @Test
    public void t_add(){
        list.add(user1);
        list.add(user2);
        assertTrue(list.size() == 2);
        list.clear();
    }

    @Test
    public void t_size(){
        list.add(user1);
        list.add(user2);
        assertTrue(list.size() == list.Userlist.size());
        list.clear();
    }
}
