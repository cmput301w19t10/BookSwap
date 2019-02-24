package com.example.bookswap;

import android.app.Activity;
import android.os.Bundle;
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
    private ArrayList<User> request_list = new ArrayList<User>();
    private Button dialog;





    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ORequestedUserActivity_layout);
        display_listview = (ListView) findViewById(R.id.main_listview);


    }











}
