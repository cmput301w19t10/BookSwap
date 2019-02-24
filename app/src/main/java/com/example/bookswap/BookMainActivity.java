package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BookMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_main);

        Intent intent = getIntent();
    }
    /**
     * called when the user tap the Available button
     *
     * @param view
     */

    public void GoToAvailable(View view){
        //Do something in response to button
        Intent intentAva = new Intent(this, AvailableMainActivity.class);
        startActivity(intentAva);
    }

}
