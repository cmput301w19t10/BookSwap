package com.example.bookswap;

import java.util.ArrayList;

// dummy list for testing and development only
// not for prototype use
public class Accepted_list {
    protected ArrayList<User> Userlist;

    /**
     * constructor
     * set a new User list
     */
    public Accepted_list() {
        Userlist = new ArrayList<User>();
    }

    /**
     * add a user into the user list
     * @param user
     */
    public void add(User user){
        Userlist.add(user);
    }

    /**
     * remove all the elements in the user list
     */
    public void clear(){
        Userlist.clear();
    }

    /**
     * return the size of the user list
     * @return
     */
    public int size(){
        return Userlist.size();
    }

    /**
     * remove the specific index of user in the user list
     * @param index
     */


    /**
     * swap the book for user
     * @param user
     */
    public void swap(User user){
        //TODO
    }
}
