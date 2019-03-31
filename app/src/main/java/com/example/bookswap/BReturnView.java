package com.example.bookswap;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
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

import java.util.Calendar;

public class BReturnView extends AppCompatActivity {

    private TextView time;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView comment;
    private Button confirm;
    private Button back;
    private Swap swapclass = new Swap();
    private Book swapingBook;
    private DataBaseUtil u;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("Borrower View Return");

        setContentView(R.layout.activity_breturn_view);
        time = (TextView) findViewById(R.id.time_text);
        date = (TextView) findViewById(R.id.date_text);
        TextView tvBookInfo = (TextView) findViewById(R.id.bookInfo);
        comment = (TextView) findViewById(R.id.comment_text) ;
        confirm = (Button) findViewById(R.id.confirm);
        back = (Button) findViewById(R.id.back);

        Intent intent = getIntent();
        swapingBook = intent.getParcelableExtra("book");
        String infoDisplay = swapingBook.getTitle() + " by " + swapingBook.getAuthor();
        infoDisplay = infoDisplay.substring(0, Math.min(infoDisplay.length(), 40));
        tvBookInfo.setText(infoDisplay);


        u = new DataBaseUtil("Bowen");
        u.getSwap(swapingBook,new DataBaseUtil.getSwapInfo(){
            @Override
            public void getSwapInfo(Swap swap) {
                swapclass = swap;
                date.setText(swapclass.getDate());
                time.setText(swapclass.getTime());
                comment.setText(swapclass.getComment());
            }
        });



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.changeSwapStatus(swapingBook,"Borrower",true);
                showNormalDialog();
                timer();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    /**
     * build timer to make handler can auto
     * check do swapingbook is swap or not
     */
    public void timer(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                u.checkSwapStatus(swapingBook, new DataBaseUtil.swapStatus() {
                    @Override
                    public void getStatus(boolean value) {
                        if(value){
                            u.changeStatus(swapingBook,"Borrowed");
                            u.deleteSwap(swapingBook);
                            u.changeSwapStatus(swapingBook,"Return",false);
                            handler.removeCallbacksAndMessages(null);
                            finish();
                        }
                    }
                });


                handler.postDelayed(this,1000);}
        }, 1000);  //the time is in miliseconds

    }


    /**
     * make a dialog
     * resourse:https://www.cnblogs.com/gzdaijie/p/5222191.html
     */
    private void showNormalDialog(){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(BReturnView.this);
        normalDialog.setTitle("Wait for Owner confiem");
        normalDialog.setMessage("Waiting..");
        normalDialog.setPositiveButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        u.changeSwapStatus(swapingBook,"Borrower",false);
                        handler.removeCallbacksAndMessages(null);
                    }
                });

        normalDialog.show();
    }
}