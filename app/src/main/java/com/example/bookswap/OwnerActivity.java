package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OwnerActivity extends AppCompatActivity {

    private Button button_accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_main);

        Intent intent = getIntent();

        button_accept = (Button)findViewById(R.id.accept);
        button_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerActivity.this, BAcceptActivity.class);
                startActivity(intent);
            }
        });





    }
    /**
     * called when the user tap the Available button
     *
     * @param view
     */

    public void GoToAvailable(View view){
        //Do something in response to button
        Intent intentAva = new Intent(this, OAvailableActivity.class);
        startActivity(intentAva);
    }

    public void GoToRequestActivity(View view){
        //Do something in response to button
        Intent intent = new Intent(this, ORequestedActivity.class);
        startActivity(intent);
    }

//    public void GoToBacceptActivity(View view){
//        //Do something in response to button
//        Intent intent = new Intent(this, BAcceptActivity.class);
//        startActivity(intent);
//    }

}
