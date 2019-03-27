package com.example.bookswap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BAcceptedSwapActivity extends AppCompatActivity {
    private TextView time;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView comment;
    private Button swap;
    private Button back;
    private Swap swapclass = new Swap();
    private Book swapingBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("Borrower Confirm borrow");

        setContentView(R.layout.activity_baccepted_swap);
        time = (TextView) findViewById(R.id.time_text);
        date = (TextView) findViewById(R.id.date_text);
        comment = (TextView) findViewById(R.id.commont_text) ;
        swap = (Button) findViewById(R.id.confirm);
        back = (Button) findViewById(R.id.back);

        Intent intent = getIntent();
        final Book swapingBook = intent.getParcelableExtra("book");
        swapclass.setBook(swapingBook);

        //TODO load swap class from database
//        date.setText();
//        time.setText();
//        comment.setText();




        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapclass.setBorrowerPermit(true);
                DataBaseUtil u = new DataBaseUtil("Bowen");
                u.swapInfo(swapingBook,swapclass);
                finish();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
