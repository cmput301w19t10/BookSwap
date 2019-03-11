package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * the main interface for borrower
 */
public class BorrowerActivity extends AppCompatActivity {
//    private Button accepted;
//    private Button requested;
//    private Button available;
//    private Button borrowed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrower);
        Intent intent = getIntent();
        Button requested = (Button) findViewById(R.id.Borrower_requested_btn);
        Button available = (Button) findViewById(R.id.Borrower_available_btn);
        requested.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BorrowerActivity.this, BRequestedBooksActivity.class);
                startActivity(intent);
            }
        });
        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BorrowerActivity.this, BAvailableActivity.class);
                startActivity(intent);
            }
        });
    }

}
