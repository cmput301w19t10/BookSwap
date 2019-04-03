package com.example.bookswap;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookswap.barcode.BarcodeScannerActivity;

import java.util.ArrayList;

/**
 * Borrower borrowed books activity
 * Display the list of books that borrower had borrowed(not returned yet
 */

public class BBorrowedActivity extends AppCompatActivity {
    private ListView borrowedBooks;
    private ArrayList<Book> bro_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;
    private ArrayList<Boolean> swapList = new ArrayList<>();
    private DataBaseUtil u;
    private final int SCAN = 1;

    /**
     * set up the layout
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bborrowed);
        borrowedBooks = (ListView)findViewById(R.id.BBB_listview);
        //Intent intent = getIntent();

    }

    /**
     * When the activities starts
     * setup the arraylist of borrower's borrowed books to the adapter
     * setup the swap return boolean array to the adapter
     */
    @Override
    protected void onStart() {
        super.onStart();
        bro_book.clear();
        swapList.clear();
        adapter = new BBorrowedAdapter(this, bro_book, swapList);
        User myUser = MyUser.getInstance();
        u = new DataBaseUtil(myUser.getName());
        Log.d("fragment","noone");
        u.getBorrowerBook(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book aBook) {
            if (aBook.getStatus().equals("Borrowed") ){
                bro_book.add(aBook);
                u.getReturnstatus(aBook, new DataBaseUtil.returnStatus() {
                        @Override
                        public void getReturnStatus(Boolean value) {
                            if (value){
                                swapList.add(true);
                                Log.d("godplz","At swapinfo != null, and swapList.size = "+swapList.size());
                            }
                           else{
                                swapList.add(false);
                                Log.d("godplz","At return == null, and swapList.size = "+swapList.size());

                            }
                            //swapList.add(false);

                            borrowedBooks.setAdapter(adapter);
                        }
                });
                    Log.d("fragment","loop"+ bro_book.size());
                }

            }
        });
        borrowedBooks.setAdapter(adapter);
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
            case R.id.scan_meun:
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                startActivityForResult(intent, SCAN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SCAN ) {
            if (resultCode == RESULT_OK){
                if (data != null) {
                    String barcode = data.getStringExtra("ISBN");
                    boolean flag = false;
                    for (int i = 0 ; i < bro_book.size(); i++) {
                        if (bro_book.get(i).getISBN() != null){
                            if (bro_book.get(i).getISBN().equals(barcode)) {
                                if (swapList.get(i) == true) {
                                    Intent intent = new Intent(BBorrowedActivity.this, BAcceptedSwapActivity.class);
                                    intent.putExtra("book", bro_book.get(i));
                                    flag = true;
                                    startActivity(intent);
                                }
                            }
                        }
                    }

                    if (!flag){
                        Toast.makeText(BBorrowedActivity.this,"No such book ready for swap",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }



    }
