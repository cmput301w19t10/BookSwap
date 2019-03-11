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

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * every owner exist a list which book have borrower want to borrow
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





    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequested);



        adapter = new ORequestedAdapter(this, 0, requestedList);
        display_listview = (ListView) findViewById(R.id.main_listview);

//
        DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        u.testAllInfoBook__3(new DataBaseUtil.getNewBook(){
            @Override
            public void getNewBook(Book a){
                if(true) {
                    Log.d(TAG,"nimama");
                    requestedList.add(a);
                    Book testbook = new Book("nihao","nihao","nihao","niha");
                    requestedList.add(testbook);
                }
                display_listview.setAdapter(adapter);
            }
        });

//        Book testbook = new Book("nihao","nihao","nihao","niha");
//        requestedList.add(testbook);
//        adapter = new ORequestedAdapter(this, 0, requestedList);
//        display_listview.setAdapter(adapter);






//        adapter = new ORequestedAdapter(this, 0, requestedList);
//        display_listview = (ListView) findViewById(R.id.main_listview);
//        display_listview.setAdapter(adapter);


        /**
         * about passing the percel item
         * learn that from https://www.youtube.com/watch?v=WBbsvqSu0is
         */
        display_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = requestedList.get(position);
                Intent intent = new Intent(ORequestedActivity.this , ViewBookActivity.class);
                intent.putExtra("BookInformation", book);
//                intent.putExtra("Index", position+"");
                Log.d(TAG,"nimama");
                startActivity(intent);

            }
        });







    }











}
