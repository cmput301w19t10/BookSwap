package com.example.bookswap;

import android.os.Parcelable;

public class MyUser extends User{

    private volatile static MyUser instance;

    private MyUser(){
        super();
    }

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