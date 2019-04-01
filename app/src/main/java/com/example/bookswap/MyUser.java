package com.example.bookswap;

import android.os.Parcelable;

/**
 * singleton class saving current user name
 */
public class MyUser extends User{

    private volatile static MyUser instance;

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
    public static MyUser getInstance() {
        if (instance == null){
            synchronized (MyUser.class){
                if (instance == null){
                    instance = new MyUser();
                }
            }
        }

        return instance;
    }

}