package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * each borrower exist a list that the wait for owner agree to borrow his book
 */
public class BAcceptActivity extends Activity {

    private ListView display_listview;
    private TextView title;
    //accept_list will be connect with the database in the cloud
    private ArrayList<Book> accept_list= new ArrayList<Book>();
    private static final int ADD_BOOK_REQUEST = 1;
    private static final int EDIT_BOOK_REQUEST = 2;
    private Button dialog;
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


}
