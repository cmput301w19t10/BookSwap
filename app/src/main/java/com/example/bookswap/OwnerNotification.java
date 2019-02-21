package com.example.bookswap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OwnerNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_notification);

    }
    protected void onStart() {

        super.onStart();
        //todo: load user attribute new request if true, display notification/if false hide notification
        //todo:onclicklistener: request button
            //todo: once clicked change user attribute new request to false
            //todo: start activity list books that have been requested
    }

}
