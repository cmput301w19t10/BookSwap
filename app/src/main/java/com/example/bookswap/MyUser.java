package com.example.bookswap;

import android.os.Parcelable;

/**
 * singleton class saving current user name
 */
public class MyUser{

    private volatile static User instance;

    /**
     * private constructor
     */
    private MyUser(){
        super();
    }


    /**
     * get instance of the only instance
     * @return
     */
    public static User getInstance() {
        if (instance == null){
            synchronized (MyUser.class){
                if (instance == null) {
                    instance = new User();
                }
            }
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

}