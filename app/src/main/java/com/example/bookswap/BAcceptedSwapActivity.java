package com.example.bookswap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private TextView author;
    private TextView title;


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
        TextView tvBookInfo = (TextView) findViewById(R.id.bookInfo);
        comment = (TextView) findViewById(R.id.commont_text_o) ;
        swap = (Button) findViewById(R.id.confirm);
        back = (Button) findViewById(R.id.back);

        Intent intent = getIntent();
        swapingBook = intent.getParcelableExtra("book");
        String infoDisplay = swapingBook.getTitle() + " by " + swapingBook.getAuthor();
        infoDisplay = infoDisplay.substring(0, Math.min(infoDisplay.length(), 40));
        tvBookInfo.setText(infoDisplay);


        DataBaseUtil u = new DataBaseUtil("Bowen");
        u.getSwap(swapingBook,new DataBaseUtil.getSwapInfo(){
            @Override
            public void getSwapInfo(Swap swap) {
                date.setText(swap.getDate());
                time.setText(swap.getTime());
                comment.setText(swap.getComment());
            }
        });


        
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
