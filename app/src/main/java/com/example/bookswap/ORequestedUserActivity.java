package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * For owner page , when owner click the viewrequest button
 * then the owner can view who want to borrow this book(it is a requested user list)
 */
public class ORequestedUserActivity extends Activity {

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


        //       sourse https://stackoverflow.com/questions/14814714/update-textview-every-second
//        Thread t = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                DataBaseUtil u = new DataBaseUtil("Bowen");
//                                u.getBookBorrower(book,new DataBaseUtil.getBorrowerList(){
//                                    /**
//                                     * load database information into the local arraylist
//                                     * @param value
//                                     */
//                                    @Override
//                                    public void getBorrower(String value){
//                                        UserList.add(value);
//                                        display_listview.setAdapter(adapter);
//                                    }
//                                });
//
//
//                            }
//
//
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//
//        t.start();
    }

    public void refresh(){
        finish();
        startActivity(getIntent());
    }

//public void loaddata(Book book ) {
//    DataBaseUtil u = new DataBaseUtil("Bowen");
//    u.getBookBorrower(book,new DataBaseUtil.getBorrowerList(){
//        /**
//         * load database information into the local arraylist
//         * @param value
//         */
//        @Override
//        public void getBorrower(String value){
//            UserList.add(value);
//            display_listview.setAdapter(adapter);
//        }
//    });
//
//}

//}



}
