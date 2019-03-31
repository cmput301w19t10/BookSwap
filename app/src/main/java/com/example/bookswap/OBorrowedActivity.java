package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class OBorrowedActivity extends AppCompatActivity {
    private ListView borrowedBooks;
    private ArrayList<Book> bro_book = new ArrayList<Book>();
    private ArrayAdapter<Book> adapter;
    private ArrayList<Boolean> swapList = new ArrayList<>();
    private DataBaseUtil u;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oborrowed);
        borrowedBooks = (ListView)findViewById(R.id.OBB_listview);
        Intent intent = getIntent();
    }
    @Override
    protected void onStart() {
        super.onStart();
        bro_book.clear();
        swapList.clear();

        adapter = new OBorrowedAdapter(this, bro_book, swapList);

        u = new DataBaseUtil("Bowen");
        Log.d("fragment","noone");
        u.getOwnerBook(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(final Book aBook) {
                //need to change to owner's name not bowen
                if (aBook.getStatus().equals("Borrowed")){
                    bro_book.add(aBook);



//                    for(int i = 0; i < bro_book.size();i++){
//                        Book element = aBook;
                    u.getSwap(aBook,new DataBaseUtil.getSwapInfo() {
                        @Override
                        public void getSwapInfo(Swap swap) {
                            if (swap != null){
                                swapList.add(true);
                                Log.d("godplz","title: "+aBook.getTitle()+"At swapinfo != null, and swapList.size = "+swapList.size());
                            }
                            if (swap == null){
                                swapList.add(true);
                                Log.d("godplz","title: "+aBook.getTitle()+"At swapinfo == null, and swapList.size = "+swapList.size());
                            }
                            //swapList.add(false);
                            borrowedBooks.setAdapter(adapter);
                        }
                    });
//                    }
//                    borrowedBooks.setAdapter(adapter);
                    //Log.d("fragment", aBook.getAuthor());
                    Log.d("fragment","loop"+ bro_book.size());
                }
                //borrowedBooks.setAdapter(adapter);
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
            case R.id.item_scan:
                Toast.makeText(this,"scan!!!",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

//    /**
//     * when back to BAcceptActivity
//     * refresh the accept_list and display it
//     */
//    @Override
//    protected void onRestart(){
//        super.onRestart();
//        bro_book.clear();
//        swapList.clear();
//        u.getBorrowerBook(new DataBaseUtil.getNewBook() {
//            @Override
//            public void getNewBook(Book aBook) {
//                if (aBook.getStatus().equals("Borrowed") ){
//                    bro_book.add(aBook);
//                    u.getReturnstatus(aBook, new DataBaseUtil.returnStatus() {
//                        @Override
//                        public void getReturnStatus(Boolean value) {
//                            if (value){
//                                swapList.add(true);
//                                Log.d("godplz","At swapinfo != null, and swapList.size = "+swapList.size());
//                            }
//                            if(!value){
//                                swapList.add(false);
//                                Log.d("godplz","At return == null, and swapList.size = "+swapList.size());
//
//                            }
//                            //swapList.add(false);
//                            adapter = new OBorrowedAdapter(OBorrowedActivity.this, bro_book, swapList);
//                            borrowedBooks.setAdapter(adapter);
//                        }
//                    });
//                    Log.d("fragment","loop"+ bro_book.size());
//                }
//
//            }
//        });
//        borrowedBooks.setAdapter(adapter);
//    }
}
