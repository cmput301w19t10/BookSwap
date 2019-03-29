package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * For owner page , when owner click the viewrequest button
 * then the owner can view who want to borrow this book(it is a requested user list)
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
    protected void onCreate(Bundle savedInstanceState) {

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


        adapter = new ORequestedUsersAdapter(this, book, UserList);

        /**
         * link the database
         */

        DataBaseUtil u = new DataBaseUtil("Bowen");
        u.getBookBorrower(book, new DataBaseUtil.getBorrowerList() {
            /**
             * load database information into the local arraylist
             *
             * @param value
             */
            @Override
            public void getBorrower(String value) {
                UserList.add(value);
                display_listview.setAdapter(adapter);
            }
        });

    }


    /**
     * Initialize the contents of the Activity's standard options menu.  You
     *
     * about how to produce a menu
     * resourse:https://www.youtube.com/watch?v=oh4YOj9VkVE
     *
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scanbarcode,menu);
        return true;
    }

    /**
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_scan:
                Toast.makeText(this,"scan!!!",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
