package com.example.bookswap;

import android.util.Log;

import java.util.ArrayList;

public class MyCallBackImp implements  MyCallback {

    @Override
    public void onCallback(ArrayList<String> value) {
        Log.i("BOWEN", " BOWEN " + value.get(0));
    }

}
