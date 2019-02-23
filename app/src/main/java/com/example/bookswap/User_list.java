package com.example.bookswap;

import java.util.ArrayList;

// dummy list for testing and development only
// not for prototype use
public class User_list {
    protected ArrayList<User> Userlist;

    /**
     * constructor
     * set a new User list
     */
    public User_list() {
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
    public void cancel(int index){
        Userlist.remove(index);
    }

    /**
     * accept the specific index of user and change its status
     * then remove all the elements for the user list
     * @param index
     */
    public void accept(int index){
        User accept_user = Userlist.get(index);
        change_status(accept_user);

        Userlist.clear();
    }

    /**
     * change the user status from wait to accept
     * @param user
     */
    public void change_status(User user){
        //TODO
    }
}
