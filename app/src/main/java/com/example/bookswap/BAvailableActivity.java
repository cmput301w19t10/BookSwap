package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BAvailableActivity extends AppCompatActivity {
    private ListView availableBooks;
    private ArrayList<Book> ava_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bavailable);
        Intent intent = getIntent();
//        Button dummy_book = findViewById(R.id.BAB_dummy_btn);
//        dummy_book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Book abook = new Book("asdfhaskdjfhak", "adsfa", "fasdfasdf", "asdjfhakjdfhlaksdfhlkahjdsfhakldsfhaksdjfhskdajlfhaskdljfhlaskjdfa", "baba");
//                Intent intent = new Intent(BAvailableActivity.this, BRequestedBooksActivity.class);
//                intent.putExtra("abook",abook);
//                startActivity(intent);
//            }
//        });
    }
    protected void onStart() {

        super.onStart();
        //todo display requested book list




        adapter = new BAvailableAdapter(this, ava_book);

        DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        u.getBorrowerBok(new DataBaseUtil.getNewBook(){
            @Override
            public void getNewBook(Book a){
                if(true) {
                    ava_book.add(a);
                }
                availableBooks.setAdapter(adapter);
            }
        });
        Book abook = new Book("asdfhaskdjfhak", "adsfa", "fasdfasdf", "asdjfhakjdfhlaksdfhlkahjdsfhakldsfhaksdjfhskdajlfhaskdljfhlaskjdfa", "baba");
        ava_book.add(abook);
        availableBooks.setAdapter(adapter);
        //todo: onclick listener: once select a book
        //todo: once clicked start activity: list user requested
    }
}
