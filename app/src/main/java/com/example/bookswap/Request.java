package com.example.bookswap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Button request = (Button) findViewById(R.id.Request_Request_button);
        //todo: load from
        //Todo: Check if user had already booked this book. If booked, button unclickable.

        request.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Todo： If book
            }
        });
    }
    @Override
    protected void onStart() {

        super.onStart();
        //todo: on click listener make a request on this book(chang book status).
            //todo: start make request function
    }
    public boolean makeRequest(){
        //todo: check book status see if it is requested or available
        //todo if available change to requested
        //todo: if not requested or available request fails return false
        //todo: if requested or available: add user name to book attribute's borrower list return true
        return true;
    }
}
