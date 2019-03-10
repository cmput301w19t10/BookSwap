package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * activity for testing different parts of the app
 */
public class MainActivity extends AppCompatActivity {

    /**
     * create this activity
     * create a button for going to login
     * create a button for going to profile
     * create a button for going to book
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button go_login = findViewById(R.id.login);
        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelfProfileActivity.class));
            }
        });

        Button go_profile = findViewById(R.id.profile);
        go_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelfProfileActivity.class));
            }
        });
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
