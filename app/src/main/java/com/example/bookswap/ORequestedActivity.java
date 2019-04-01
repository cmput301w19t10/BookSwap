package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * For owner page , when owner click the request button
 * then the owner can view which books are be requested.(it is a requested list)
 */
public class ORequestedActivity extends AppCompatActivity {

    private ListView display_listview;
    private TextView title;
    private ArrayList<Book> requestedList = new ArrayList<>();
    private ORequestedAdapter adapter;
    private Button dialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataBaseUtil u;


    /**
     * oncreate activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequested);

        /**
         * hwo to change actionbar title
         * resource:https://stackoverflow.com/questions/3438276/how-to-change-the-text-on-the-action-bar
         */
        getSupportActionBar().setTitle("Owner Requested List");


        adapter = new ORequestedAdapter(this, 0, requestedList);
        display_listview = (ListView) findViewById(R.id.main_listview);

        //For offline UI test
        if (getIntent().getBooleanExtra("TEST", false)) {
            Book book = getIntent().getParcelableExtra("Book");
            requestedList.add(book);
            display_listview.setAdapter(adapter);

        } else {
            MyUser myUser = MyUser.getInstance();
            u = new DataBaseUtil(myUser.getName());
            u.getBorrowerBook(new DataBaseUtil.getNewBook() {
                /**
                 * get the requestedlist from database and then load it into the local listview
                 *
                 * @param a
                 */
                @Override
                public void getNewBook(Book a) {
                    if (a.getStatus().equals("Requested")) {
                        requestedList.add(a);
                    }
                    display_listview.setAdapter(adapter);
                }
            });
        }

        /**
         * how to set swipe refresh layout
         * resourse:https://www.youtube.com/watch?v=KLrq8nQeIn8
         //         */
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.Swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestedList.clear();
                MyUser myUser = MyUser.getInstance();
                u = new DataBaseUtil(myUser.getName());
                u.getBorrowerBook(new DataBaseUtil.getNewBook() {
                    /**
                     * get the requestedlist from database and then load it into the local listview
                     *
                     * @param a
                     */
                    @Override
                    public void getNewBook(Book a) {
                        if (a.getStatus().equals("Requested")) {
                            requestedList.add(a);
                        }
                        display_listview.setAdapter(adapter);
                        display_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            /**
                             * click
                             * @param parent
                             * @param view
                             * @param position
                             * @param id
                             */
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Book book = requestedList.get(position);
                                Intent intent = new Intent(ORequestedActivity.this, ViewBookActivity.class);
                                intent.putExtra("book", book);
                                intent.putExtra("Index", position + "");
                                startActivity(intent);
                            }
                        });

                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        display_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * On click of an item, starts up an activity with result and passing some information
             *
             * @param parent   parent activity
             * @param view     current view provided from android
             * @param position index of the item being clicked
             * @param id       id of the item being clicked
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = requestedList.get(position);
                Intent intent = new Intent(ORequestedActivity.this, ViewBookActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
        });


    }

    /**
     * when back to BAcceptActivity
     * refresh the accept_list and display it
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        requestedList.clear();
        MyUser myUser = MyUser.getInstance();
        u = new DataBaseUtil(myUser.getName());
        u.getBorrowerBook(new DataBaseUtil.getNewBook() {
            /**
             * get the requestedlist from database and then load it into the local listview
             *
             * @param a
             */
            @Override
            public void getNewBook(Book a) {
                if (a.getStatus().equals("Requested")) {
                    requestedList.add(a);
                }
                display_listview.setAdapter(adapter);
                display_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    /**
                     * click
                     * @param parent
                     * @param view
                     * @param position
                     * @param id
                     */
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Book book = requestedList.get(position);
                        Intent intent = new Intent(ORequestedActivity.this, ViewBookActivity.class);
                        intent.putExtra("book", book);
                        intent.putExtra("Index", position + "");
                        startActivity(intent);
                    }
                });

            }
        });
    }

}
