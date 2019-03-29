package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

/**
 * Request backgroud book as backgroud borrower(in progress)
 * @author Chaoran
 *
 *
 */
public class BRequestActivity extends AppCompatActivity {
    private Button request;
    private TextView title;
    private TextView author;
    private TextView owner;
    private TextView status;
    private TextView description;
    private ImageView bookCover;
    private Book book;

    /**
     * load and check book status
     * change the button depending on the status
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Intent intent = getIntent();
        book = intent.getParcelableExtra("book");
        title = ((TextView)findViewById(R.id.BR_title));
        author = ((TextView)findViewById(R.id.BR_author));
        status = ((TextView)findViewById(R.id.BR_status));
        description = ((TextView)findViewById(R.id.BR_description));
        owner = ((TextView)findViewById(R.id.BR_owner));
        bookCover = findViewById(R.id.BR_bookCover);
        request = (Button) findViewById(R.id.Request_Request_button);
        //Book abook = intent.getExtras();
        //todo: load from
        //Todo: Check if user had already booked this book. If booked, button unclickable.


    }
    @Override
    protected void onStart() {

        super.onStart();
        //Log.d("here","wenthere");
        title.setText("Title: "+String.valueOf(book.getTitle()));
        author.setText("Athor: " + String.valueOf(book.getAuthor()));
        description.setText("Description: "+String.valueOf(book.getDescription()));
        owner.setText("Owner: "+String.valueOf(book.getOwner()));
        status.setText("Status: "+String.valueOf(book.getStatus()));
        bookCover.setImageBitmap(book.getImage());
        Log.d("bbbook",book.getUnikey());
        request.setOnClickListener(new View.OnClickListener(){
            //todo: on click listener make backgroud request on this book(chang book status).
            @Override
            public void onClick(View v){

                //todo: start make request function
                DataBaseUtil u;
                u = new DataBaseUtil("Bowen");

                u.addNewBorrow(book, new DataBaseUtil.addBorrowerSucceed() {
                    @Override
                    public void addNewBorrower(boolean value) {
                        if (value) {
                            Toast.makeText(BRequestActivity.this, "Request Successful(not really)", Toast.LENGTH_LONG).show();
                            onBackPressed();
                        } else {
                            Toast.makeText(BRequestActivity.this, "Request Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}

