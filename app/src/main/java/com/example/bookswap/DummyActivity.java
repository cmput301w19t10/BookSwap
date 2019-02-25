package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * called when the user tap the BOOK button
     *
     * @param view
     */
    public void Dummy(View view){
        //Do something in response to button
        Intent intent = new Intent(this, OwnerActivity.class);
        startActivity(intent);
    }
}
