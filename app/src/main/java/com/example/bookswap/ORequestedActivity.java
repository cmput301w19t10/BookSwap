package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.core.executor.DefaultTaskExecutor;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * every owner exist a list which book have borrower want to borrow
 */
public class ORequestedActivity extends Activity {

    private ListView display_listview;
    private TextView title;
    //The book of request list will be connect with the database in the cloud
    private  ArrayList<Book> request_Book_list = new ArrayList<Book>();
    private Button dialog;





    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baccept);
        display_listview = (ListView) findViewById(R.id.main_listview);
        dialog = (Button) findViewById(R.id.dialog);

        DataBaseUtil u = new DataBaseUtil("Bowen");
        request_Book_list = u.getBooks("Available");
        Log.d(TAG,"Bowen "+request_Book_list.size()+" ");

        dialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO when click the item can enter this pages
                /**
                 * the dialog window resourse from:https://blog.csdn.net/qq_35698774/article/details/79779238
                 * for slove the parameter problem for dialog :https://blog.csdn.net/u010416101/article/details/41308197?utm_source=blogxgwz6
                 * This block of code is using for create a alertdialog to show : do owner make sure borrow the book
                 */
                AlertDialog alertDialog = new AlertDialog.Builder(ORequestedActivity.this)
                        .setTitle("Notice")
                        .setMessage("Yifu" + " are you sure to borrow this book from " +
                                "Danli")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ORequestedActivity.this, "this is yes button", Toast.LENGTH_SHORT).show();

                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ORequestedActivity.this, "this is no button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        //TODO when click the item can enter this pages
        /**
         * the dialog window resourse from:https://blog.csdn.net/qq_35698774/article/details/79779238
         * This block of code is using for create a alertdialog to show : do owner make sure borrow the book
         */

    }











}
