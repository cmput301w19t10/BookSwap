package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * activity for the display of requested books from the owner
 */
public class ORequestedBooksActivity extends AppCompatActivity {
    private ListView requestedbooks;
    private ArrayList<Book> req_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequested_books);
        requestedbooks = (ListView) findViewById(R.id.BRB_listview);
        Intent intent = getIntent();
    }
    /**
     * load date and display a list
     */
    protected void onStart() {

        super.onStart();
        //todo display requested book list

        adapter = new BRequestedBooksAdapter(this, req_book);
        requestedbooks.setAdapter(adapter);
        //todo: onclick listener: once select a book
        //todo: once clicked start activity: list user requested
    }
}
