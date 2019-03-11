package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BRequestActivity extends AppCompatActivity {
    private Button request;
    /**
     * load and check book status
     * change the button depending on the status
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Intent intent = getIntent();
        //todo: load from
        //Todo: Check if user had already booked this book. If booked, button unclickable.


    }
    @Override
    protected void onStart() {

        super.onStart();
        Button request = (Button) findViewById(R.id.Request_Request_button);
        request.setOnClickListener(new View.OnClickListener(){
            //todo: on click listener make a request on this book(chang book status).
            @Override
            public void onClick(View v){

                //todo: start make request function
                if (!makeRequest()){
                    Toast.makeText(BRequestActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /**
     * make a request to a book
     * @return request success or not
     */
    public boolean makeRequest(){
        //todo: check book status see if it is requested or available
        //todo if available change to requested
        //todo: if not requested or available request fails return false
        //todo: if requested or available: add user name to book attribute's borrower list return true
        return true;
    }
}
