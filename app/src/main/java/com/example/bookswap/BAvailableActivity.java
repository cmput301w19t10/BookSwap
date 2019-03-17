package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * Activity to display available books
 * Using a custom adapter
 * @see BAvailableAdapter
 */
public class BAvailableActivity extends AppCompatActivity {
    private ListView availableBooks;
    private ArrayList<Book> ava_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bavailable);
        ava_book.clear();
        availableBooks = (ListView) findViewById(R.id.BAB_listview);
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

        //todo display available book list

        ava_book.clear();



        adapter = new BAvailableAdapter(this, ava_book);

        DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        Log.d("fragment","noone");
        u.testAllInfoBook__3(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book aBook) {
                if (aBook.getStatus()!= null && aBook.getStatus().equals("Available")){
                    ava_book.add(aBook);
                    availableBooks.setAdapter(adapter);
                    Log.d("fragment","loop");
                }
            }
        });
//        u.getBorrowerBook(new DataBaseUtil.getNewBook(){
////            @Override
////            public void getNewBook(Book a){
////                if(true) {
////                    ava_book.add(a);
////                }
////                availableBooks.setAdapter(adapter);
////            }
////        });

        //Book abook = new Book("asdfhaskdjfhak", "adsfa", "fasdfasdf", "asdjfhakjdfhlaksdfhlkahjdsfhakldsfhaksdjfhskdajlfhaskdljfhlaskjdfa", "baba");
        //ava_book.add(abook);
        availableBooks.setAdapter(adapter);
        //todo: onclick listener: once select a book
        availableBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(BAvailableActivity.this, BRequestActivity.class);
//                Book testing = new Book();
//                testing = ava_book.get(position);
//                String message = testing.getTitle();
//                if (testing.getTitle() == "test2"){
//                    Log.d("success","good job");
//                }
//                else{
//                    Log.d("fail",message);
//                }
                intent.putExtra("book", ava_book.get(position));
                startActivity(intent);
            }
        });
        //todo: once clicked start activity: list user requested
    }
}

