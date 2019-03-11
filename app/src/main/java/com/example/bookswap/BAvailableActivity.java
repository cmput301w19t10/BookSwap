package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class BAvailableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bavailable);
        Intent intent = getIntent();
        Button dummy_book = findViewById(R.id.BAB_dummy_btn);
        dummy_book.setOnClickListener();
    }
}
