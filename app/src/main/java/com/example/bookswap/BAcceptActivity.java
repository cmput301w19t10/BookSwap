package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * each borrower exist backgroud list that the wait for owner agree to borrow his book
 */
public class BAcceptActivity extends AppCompatActivity {

    private ListView display_listview;
    private TextView title;
    //accept_list will be connect with the database in the cloud
    private ArrayList<Book> accept_list= new ArrayList<Book>();
    private static final int ADD_BOOK_REQUEST = 1;
    private static final int EDIT_BOOK_REQUEST = 2;
    private BAcceptedAdapter adapter;


    /**
     * running for activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccept);
        display_listview = (ListView) findViewById(R.id.main_listview);
        adapter = new BAcceptedAdapter(this, 0, accept_list);

        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("BorrowAcceptList");

        // for offline UI test
        if (getIntent().getBooleanExtra("TEST", false)) {
            Book book = getIntent().getParcelableExtra("Book");
            accept_list.add(book);
        } else{
            DataBaseUtil u;
            u = new DataBaseUtil("Bowen");
            u.getBorrowerBook(new DataBaseUtil.getNewBook() {
                /**
                 * get the requestedlist from database and then load it into the local listview
                 *
                 * @param a
                 */
                @Override
                public void getNewBook(Book a) {

                    if (a.getStatus().equals("Requested")) {
                        accept_list.add(a);
                    }
                    display_listview.setAdapter(adapter);
                }
            });
        }


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
