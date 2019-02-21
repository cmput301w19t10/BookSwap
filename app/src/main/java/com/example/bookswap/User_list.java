package com.example.bookswap;

import java.util.ArrayList;

public class User_list {
    protected ArrayList<User> User_list;

    public User_list() {
        User_list = new ArrayList<User>();
    }

    public void add(User user){
        User_list.add(user);
    }

    public void clear(){
        User_list.clear();
    }

    public int size(){
        return User_list.size();
    }


    public void cancel(int index){
        User_list.remove(index);
    }

    public void accept(int index){
        User accept_user = User_list.get(index);
        change_status(accept_user);

        User_list.clear();
    }

    public void change_status(User user){
        //TODO
    }
}
