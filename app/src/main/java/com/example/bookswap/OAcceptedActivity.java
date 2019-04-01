package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * activity that shows accepted books by the owner
 * will link to view swap classes and eventually confirmation of a swap
 */
public class OAcceptedActivity extends AppCompatActivity {
    private ListView acceptedBooks;
    private ArrayList<Book> acp_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oaccepted);
        acp_book.clear();
        acceptedBooks = (ListView) findViewById(R.id.OAB_listview);
        Intent intent = getIntent();
    }
    protected void onStart() {

        super.onStart();

        //todo display available book list

        acp_book.clear();


        adapter = new OAcceptedAdapter(this, acp_book);
        MyUser myUser = MyUser.getInstance();
        DataBaseUtil u;
        u = new DataBaseUtil(myUser.getName());
        Log.d("fragment", "noone");
        u.getOwnerBook(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book aBook) {
                if (aBook.getStatus()!=null&&aBook.getStatus().equals("Accepted")) {
                    acp_book.add(aBook);
                    acceptedBooks.setAdapter(adapter);
                    Log.d("fragment", "loop");
                }
            }
        });
        acceptedBooks.setAdapter(adapter);

    }
}
