package com.example.bookswap;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class BAcceptedSwapActivity extends AppCompatActivity {
    private TextView time;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView comment;
    private Button confirm;
    private Button back;
    private Swap swapclass = new Swap();
    private Book swapingBook;
    private TextView bookinfo;
    private TextView author;
    private TextView title;
    private Button locationBut;
    private boolean result;
    private DataBaseUtil u;


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
        bookinfo = (TextView) findViewById(R.id.bookInfo);
        comment = (TextView) findViewById(R.id.comment_text_b) ;
        confirm = (Button) findViewById(R.id.confirm);
        back = (Button) findViewById(R.id.back);
        locationBut = (Button) findViewById(R.id.locationButton);

        Intent intent = getIntent();
        swapingBook = intent.getParcelableExtra("book");
        String infoDisplay = swapingBook.getTitle() + " by " + swapingBook.getAuthor();
        infoDisplay = infoDisplay.substring(0, Math.min(infoDisplay.length(), 40));
        bookinfo.setText(infoDisplay);

        bookinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BAcceptedSwapActivity.this,ViewBookActivity.class);
                intent.putExtra("book", swapingBook);
                startActivity(intent);
            }
        });


        u = new DataBaseUtil("Bowen");
        u.getSwap(swapingBook,new DataBaseUtil.getSwapInfo(){
            @Override
            public void getSwapInfo(Swap swap) {
                swapclass = swap;
                date.setText(swap.getDate());
                time.setText(swap.getTime());
                comment.setText(swap.getComment());
            }
        });



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.changeSwapStatus(swapingBook,"Borrower","True");
                result = false;
                while(!result) {
                    try {
                        Thread.sleep (500);

                        u.checkSwapStatus(swapingBook,new DataBaseUtil.bool() {
                            @Override
                            public void getBool(String checkswap){
                                if (checkswap.equals("True")) {
                                    result = true;
                                }
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                finish();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        locationBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BAcceptedSwapActivity.this, MapViewActivity.class);
                LatLng point = swapclass.getLocation();
                if (point == null){
                    Toast.makeText(getApplicationContext(), "Fatal error, improper location", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("point", point);
                    startActivity(intent);
                }
            }
        });


    }


    /**
     * make a dialog
     * resourse:https://www.cnblogs.com/gzdaijie/p/5222191.html
     */
    private void showNormalDialog(){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(BAcceptedSwapActivity.this);
        normalDialog.setTitle("Wait for Owner confiem");
        normalDialog.setMessage("Waiting....?");
        normalDialog.setPositiveButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        normalDialog.show();
    }
}
