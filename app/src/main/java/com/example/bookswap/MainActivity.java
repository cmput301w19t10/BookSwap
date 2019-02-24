package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a intent for jump to the main part
        Button accept_button = (Button) findViewById(R.id.accept);
        accept_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_accept = new Intent(MainActivity.this, BAcceptActivity.class);
                startActivity(intent_accept);
            }
        });

        //create a intent for jump to the main part
        Button request_button = (Button) findViewById(R.id.request);
        request_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_request = new Intent(MainActivity.this, ORequestedUserActivity.class);
                startActivity(intent_request);
                Log.d(TAG,"hello0000000000");
            }
        });

    }





}
