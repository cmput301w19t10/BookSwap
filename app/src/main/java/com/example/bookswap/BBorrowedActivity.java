package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BBorrowedActivity extends AppCompatActivity {
    private ListView borrowedBooks;
    private ArrayList<Book> bro_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bborrowed);
        borrowedBooks = (ListView)findViewById(R.id.BBB_listview);
        Intent intent = getIntent();

    }

    @Override
    protected void onStart() {
        super.onStart();
        bro_book.clear();
        adapter = new BBorrowedAdapter(this, bro_book);
        DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        Log.d("fragment","noone");
        u.testAllInfoBook__3(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book aBook) {
                if (aBook.getStatus()!= null && aBook.getStatus().equals("Available")){
                    bro_book.add(aBook);
                    borrowedBooks.setAdapter(adapter);
                    Log.d("fragment","loop");
                }
            }
        });
        borrowedBooks.setAdapter(adapter);
    }
}
