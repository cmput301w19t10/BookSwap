package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * For owner page , when owner click the viewrequest button
 * then the owner can view who want to Accept this book(it is a requested user list)
 */
public class ORequestedUserActivity extends AppCompatActivity {

    private ListView display_listview;
    private TextView title;
    //user_list will be connect with book from the database in the cloud
    private ArrayList<User> request_User_list = new ArrayList<User>();
    private ArrayList<Book> requestedList = new ArrayList<Book>();
    private ArrayList<String>UserList = new ArrayList<>();
    private ORequestedUsersAdapter adapter;


    /**
     * starting for activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequesteduser);
        display_listview = (ListView) findViewById(R.id.main_listview);

        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("Owner Requested UserList");

        /**
         * how to get parcel for a book
         * resource from:https://www.youtube.com/watch?v=WBbsvqSu0is
         */
        Intent intent = getIntent();
        final Book book = intent.getParcelableExtra("index");




        adapter = new ORequestedUsersAdapter(this,book,UserList);

        /**
         * link the database
         */

        DataBaseUtil u = new DataBaseUtil("Bowen");
        u.getBookBorrower(book,new DataBaseUtil.getBorrowerList(){
            /**
             * load database information into the local arraylist
             * @param value
             */
            @Override
            public void getBorrower(String value){
                UserList.add(value);
                display_listview.setAdapter(adapter);
            }
        });


    }






}
