package com.example.bookswap;

import java.util.ArrayList;

public class User_list {
    protected ArrayList<User> User_list;

    /**
     * constructor
     * set a new User list
     */
    public User_list() {
        User_list = new ArrayList<User>();
    }

    /**
     * add a user into the user list
     * @param user
     */
    public void add(User user){
        User_list.add(user);
    }

    /**
     * remove all the elements in the user list
     */
    public void clear(){
        User_list.clear();
    }

    /**
     * return the size of the user list
     * @return
     */
    public int size(){
        return User_list.size();
    }

    /**
     * remove the specific index of user in the user list
     * @param index
     */
    public void cancel(int index){
        User_list.remove(index);
    }

    /**
     * accept the specific index of user and change its status
     * then remove all the elements for the user list
     * @param index
     */
    public void accept(int index){
        User accept_user = User_list.get(index);
        change_status(accept_user);

        User_list.clear();
    }

    /**
     * change the user status from wait to accept
     * @param user
     */
    public void change_status(User user){
        //TODO
    }
}
