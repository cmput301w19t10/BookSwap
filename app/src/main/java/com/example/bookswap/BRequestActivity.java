package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Request a book as a borrower(in progress)
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

        title.setText("Title: "+String.valueOf(book.getTitle()));
        author.setText("Athor: " + String.valueOf(book.getAuthor()));
        description.setText("Description: "+String.valueOf(book.getDescription()));
        owner.setText("Owner: "+String.valueOf(book.getOwner()));
        status.setText("Status: "+String.valueOf(book.getStatus()));
        bookCover.setImageBitmap(book.getImage());

        request.setOnClickListener(new View.OnClickListener(){
            //todo: on click listener make a request on this book(chang book status).
            @Override
            public void onClick(View v){

                //todo: start make request function
                if (!makeRequest()){
                    Toast.makeText(BRequestActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BRequestActivity.this, "Request Succeeded(not really)", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });


    }

    /**
     * make a request to a book
     * @return request success or not
     */
    public boolean makeRequest(){
        DataBaseUtil u;
        u = new DataBaseUtil(String.valueOf(book.getOwner()));
        //getStatus要的String是什么。。title？
        //todo: check book status see if it is requested or available
        //u.getStatus(String.valueOf(book.getTitle()); 红线。。应该怎么call interface？
        //todo if available change to requested


        //好像没有让我加borrower的method？ 现在是private，
        // 并且现在DataUtil里面我是不是应该pass书和名字给你啊
        // 但是现在只要我pass borrower的名字，database并不知道我要request哪本书吧？
        //u.BookBorrower("borrower_1");这个是private 红线
        //todo: if not requested or available request fails return false
        //todo: if requested or available: add user name to book attribute's borrower list return true
        return true;
    }
}
