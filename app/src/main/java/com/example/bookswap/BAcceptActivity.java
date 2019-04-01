package com.example.bookswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookswap.barcode.BarcodeScannerActivity;

import java.util.ArrayList;

/**
 * each borrower exist a list that the wait for owner agree to Accept his book
 */
public class BAcceptActivity extends AppCompatActivity {

    private ListView display_listview;
    private TextView title;
    private ArrayList<Book> accept_list= new ArrayList<Book>();
    private BAcceptedAdapter adapter;
    private static final int SCAN = 1;
    private DataBaseUtil u;


    /**
     * running for activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccept);
        display_listview = (ListView) findViewById(R.id.main_listview);
        adapter = new BAcceptedAdapter(this, 0, accept_list);

        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("BorrowAcceptList");

        // for offline UI test
//        if (getIntent().getBooleanExtra("TEST", false)) {
//            Book book = getIntent().getParcelableExtra("Book");
//            accept_list.add(book);
//        } else{
            u = new DataBaseUtil("Bowen");
            u.getBorrowerBook(new DataBaseUtil.getNewBook() {
                /**
                 * get the requestedlist from database and then load it into the local listview
                 *
                 * @param a
                 */
                @Override
                public void getNewBook(Book a) {

                    if (a.getStatus().equals("Accepted")) {
                        accept_list.add(a);
                    }
                    display_listview.setAdapter(adapter);
                }
            });
//        }


    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * about how to make a menu
     * resourse:https://www.youtube.com/watch?v=oh4YOj9VkVE
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

    /**
     * after scan book barcode and then go to this book's viewbook activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SCAN ) {
            if (resultCode == RESULT_OK){
                if (data != null) {
                    String barcode = data.getStringExtra("ISBN");
                    boolean flag = false;
                    for (int i = 0 ; i < accept_list.size(); i++) {
                        if (accept_list.get(i).getISBN().equals(barcode)) {
                            Intent intent = new Intent(BAcceptActivity.this, ViewBookActivity.class);
                            intent.putExtra("book", accept_list.get(i));
                            flag = true;
                            startActivity(intent);
                        }
                    }

                    if (!flag){
                        Toast.makeText(BAcceptActivity.this,"No this book in Borrower list",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    /**
     * when back to BAcceptActivity
     * refresh the accept_list and display it
     */
    @Override
    protected void onRestart(){
        super.onRestart();
        accept_list.clear();
        u.getBorrowerBook(new DataBaseUtil.getNewBook() {
            /**
             * get the requestedlist from database and then load it into the local listview
             *
             * @param a
             */
            @Override
            public void getNewBook(Book a) {

                if (a.getStatus().equals("Accepted")) {
                    accept_list.add(a);
                }
                adapter = new BAcceptedAdapter(BAcceptActivity.this, 0, accept_list);
                display_listview.setAdapter(adapter);
            }
        });
    }







}
