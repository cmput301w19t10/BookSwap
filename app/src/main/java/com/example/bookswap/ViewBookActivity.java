package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewBookActivity extends AppCompatActivity {

    private static final String FILENAME = "AvailableBooks.sav";



    DataBaseUtil util = new DataBaseUtil("someone");
    private ArrayList<Book> availableList = util.getBooks("Available");//copied into memory
    private ViewBookAdapter adapter; // initialize adapter.

    //private static int BOOK_PHOTO_RESULT = 1;

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        Intent vintent = getIntent();
        adapter = new ViewBookAdapter(this, 0, availableList);

    }


}
