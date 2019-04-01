package com.example.bookswap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

/**
 * the activity for borrower to set up a meet up location and time
 * to return the book to the owner of the book
 */
public class BReturnSet extends AppCompatActivity {
    private TextView time;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView comment;
    private Button swap;
    private Button locat;
    private Swap swapclass = new Swap();
    private TextView bookinfo;
    private Book swapingBook;
    private static final int SET_MAP = 1;
    private DataBaseUtil u;
/*
set up the layout when created
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breturn_set);
        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("Borrower Return Setup");
        time = (TextView) findViewById(R.id.time_text);
        date = (TextView) findViewById(R.id.date_text);
        comment = (TextView) findViewById(R.id.comment_text_o) ;
        swap = (Button) findViewById(R.id.confirm);
        locat = (Button) findViewById(R.id.locationButton);
        bookinfo = (TextView) findViewById(R.id.bookInfo);

        Intent intent = getIntent();
        swapingBook = intent.getParcelableExtra("book");
        swapclass.setBook(swapingBook);

//        Intent intentbook = getIntent();
//        swapingBook = intent.getParcelableExtra("book");
        String infoDisplay = swapingBook.getTitle() + " by " + swapingBook.getAuthor();
        infoDisplay = infoDisplay.substring(0, Math.min(infoDisplay.length(), 40));
        bookinfo.setText(infoDisplay);


        bookinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BReturnSet.this,ViewBookActivity.class);
                intent.putExtra("book", swapingBook);
                startActivity(intent);
            }
        });


        /**
         * https://www.cnblogs.com/huanyou/p/5087044.html
         * create the time select dialog
         */
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar timecal = Calendar.getInstance();
                int hour = timecal.get(Calendar.HOUR);
                int minute = timecal.get(Calendar.MINUTE);

                TimePickerDialog timeDialog = new TimePickerDialog(
                        BReturnSet.this,timeSetListener,
                        hour,minute,
                        DateFormat.is24HourFormat(BReturnSet.this)
                );

                timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timeDialog.show();

            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hour;
                if(hourOfDay < 10){
                    hour = "0"+ hourOfDay;
                }
                else{
                    hour = hourOfDay + "";
                }

                String min;
                if(minute < 10){
                    min = "0" + minute;
                }else{
                    min = ""+minute;
                }


                String stringtime = hour + ":" + min;
                time.setText(stringtime);
                swapclass.setTime(stringtime);
            }
        };


        /**
         * https://www.youtube.com/watch?v=hwe1abDO2Ag
         * create the date select dialog
         */
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar datecal = Calendar.getInstance();
                int year = datecal.get(Calendar.YEAR);
                int month = datecal.get(Calendar.MONTH);
                int day = datecal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        BReturnSet.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;

                String stringdate = year + "-" + month +  "-" + dayOfMonth;
                date.setText(stringdate);
                swapclass.setDate(stringdate);
            }
        };




        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swapclass.getTime() == null || swapclass.getDate() == null) {
                    Toast.makeText(BReturnSet.this, "Please set time and date", Toast.LENGTH_SHORT).show();
                }else if(swapclass.getLocation() == null){
                    Toast.makeText(getApplicationContext(), "Please set up a meetup location", Toast.LENGTH_SHORT).show();
                }else{
                    String stringcomment = comment.getText().toString();
                    if(stringcomment == null){stringcomment = " ";}
                    swapclass.setComment(stringcomment);
                    Log.d("swappy",swapingBook.getTitle() + " ");
                    User myUser = MyUser.getInstance();
                    u = new DataBaseUtil(myUser.getName());
                    u.swapInfo(swapingBook,swapclass);
                    u.changeSwapStatus(swapingBook,"Return",true);

                    finish();}
            }
        });

        locat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BReturnSet.this, MapSelectActivity.class);
                startActivityForResult(intent, SET_MAP);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SET_MAP) {
            if (resultCode == RESULT_OK){
                if (data != null) {
                    LatLng point = data.getParcelableExtra("location");
                    swapclass.setLocation(point);
                }
            }
        }
    }
}