package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * every book exist a request list
 */
public class ORequestedUserActivity extends Activity {

    private ListView display_listview;
    private TextView title;
    //user_list will be connect with book from the database in the cloud
    private ArrayList<User> request_User_list = new ArrayList<User>();

    //remember load the database to the book
    private ArrayList<Book> requestedList = new ArrayList<Book>();
    private ArrayList<String>UserList = new ArrayList<>();
    private ORequestedUsersAdapter adapter;






    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequesteduser);
        display_listview = (ListView) findViewById(R.id.main_listview);

        Book book1 = new Book("dsfsdf","dsfsdf","sddsfdsf","sddf",null);
        requestedList.add(book1);
        int position=getIntent().getIntExtra("index",0);
//        int positon=Integer.parseInt(position);
        Book book = requestedList.get(position);
        UserList.add("yifu");
//        Userlist = book.getUserList();


        adapter = new ORequestedUsersAdapter(this,book,UserList);
        display_listview.setAdapter(adapter);


//        for click listview event ,todo.


    }











}
