package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * For owner page , when owner click the request button
 * then the owner can view which books are be requested.(it is backgroud requested list)
 */
public class ORequestedActivity extends Activity {

    private ListView display_listview;
    private TextView title;
    private static final int ADD_BOOK_REQUEST = 1;
    private static final int EDIT_BOOK_REQUEST = 2;
    //The book of request list will be connect with the database in the cloud
    private ArrayList<Book> requestedList = new ArrayList<>();
    private ORequestedAdapter adapter;
    private Button dialog;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequested);


        adapter = new ORequestedAdapter(this, 0, requestedList);
        display_listview = (ListView) findViewById(R.id.main_listview);

        //For offline UI test
        if (getIntent().getBooleanExtra("TEST", false)){
            Book book = getIntent().getParcelableExtra("Book");
            requestedList.add(book);
            display_listview.setAdapter(adapter);

        } else {
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
                    if (true) {
                        requestedList.add(a);
                    }
                    display_listview.setAdapter(adapter);
                }
            });
        }


        /**
         * about passing the percel item
         * learn that from https://www.youtube.com/watch?v=WBbsvqSu0is
         */
        display_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * click
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = requestedList.get(position);
                Intent intent = new Intent(ORequestedActivity.this , EditBookActivity.class);
                intent.putExtra("BookInformation", book);
                intent.putExtra("Index", position+"");
                startActivityForResult(intent, EDIT_BOOK_REQUEST);
            }
        });







    }











}
