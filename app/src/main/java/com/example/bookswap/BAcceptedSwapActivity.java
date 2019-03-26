package com.example.bookswap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccepted_swap);
        time = (TextView) findViewById(R.id.time_text);
        date = (TextView) findViewById(R.id.date_text);
        comment = (TextView) findViewById(R.id.commont_text) ;
        swap = (Button) findViewById(R.id.swap);
        back = (Button) findViewById(R.id.back);



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
                        BAcceptedSwapActivity.this,timeSetListener,
                        hour,minute,
                        DateFormat.is24HourFormat(BAcceptedSwapActivity.this)
                       );

                timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timeDialog.show();

            }
        });

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hourOfDay = hourOfDay;

                String stringtime = hourOfDay + ":" + minute;
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
                        BAcceptedSwapActivity.this,
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

        String stringcomment = comment.getText().toString();
        swapclass.setComment(stringcomment);


        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapclass.setBorrowerPermit(true);
//                TODO map activity
//                Intent intentmap = new Intent();
//                startActivity(intentmap);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback = new Intent(BAcceptedSwapActivity.this,BAcceptActivity.class);
                startActivity(intentback);
            }
        });
    }
}
