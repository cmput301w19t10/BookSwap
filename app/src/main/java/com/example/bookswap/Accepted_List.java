package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Accepted_List extends Activity {

    private ListView display_listview;
    private TextView title;
    //user_list will be connect with the database in the cloud
    private User_list list = new User_list();





    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accept_list_layout);
        display_listview = (ListView) findViewById(R.id.main_listview);




        //TODO when click the item can enter this pages
        /**
         * the dialog window resourse from:https://blog.csdn.net/qq_35698774/article/details/79779238
         * This block of code is using for create a alertdialog to show : do owner make sure borrow the book
         */
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage("Yifu" + " are you sure to borrow this book from " +
                "Danli")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Accepted_List.this, "this is yes button", Toast.LENGTH_SHORT).show();

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Accepted_List.this, "this is no button", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alertDialog.show();
    }











}
