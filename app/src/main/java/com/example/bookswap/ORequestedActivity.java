package com.example.bookswap;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.core.executor.DefaultTaskExecutor;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
 * every owner exist a list which book have borrower want to borrow
 */
public class ORequestedActivity extends Activity {

    private ListView display_listview;
    private TextView title;
    private static final int ADD_BOOK_REQUEST = 1;
    private static final int EDIT_BOOK_REQUEST = 2;
    //The book of request list will be connect with the database in the cloud
    private ArrayList<Book> requestedList = new ArrayList<>();
    private ORequestedAdapter adapter;
    private Button dialog;

    //private DataBaseUtil u = new DataBaseUtil("Bowen");



    @Override
    protected void onCreate(Bundle savedInstanceState){
//        getSupportActionBar().show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orequested);
        Intent intentpas = getIntent();




        Log.d(TAG,"apple"+requestedList.size()+"");



        //addListenerForSingleValueEvent() (might be better)
        //addValueEventListener
        adapter = new ORequestedAdapter(this, 0, requestedList);
        display_listview = (ListView) findViewById(R.id.main_listview);

        //display_listview.setAdapter(adapter);



        dialog = (Button) findViewById(R.id.dialog);


        DataBaseUtil u;
        u = new DataBaseUtil("Bowen");
        u.testAllInfoBook__2(new DataBaseUtil.getNewBook(){
            @Override
            public void getNewBook(Book a){
                //Book newBook = new Book("1","1","1","1");
                requestedList.add(a);
                display_listview.setAdapter(adapter);
            }
        });


//        u.testAllInfoBook(new DataBaseUtil.GetBooksArray(){
//            @Override
//            public void onBookReceived(ArrayList<Book> a){
//                //Book newBook = new Book("1","1","1","1");
//                requestedList = a;
//                display_listview.setAdapter(adapter);
//            }
//        });



//        u.bookUniKey(new DataBaseUtil.OnDataReceiveCallBack() {
//            @Override
//            public void onDataReceived(ArrayList<String> arry) {
//                int size = arry.size();
//                for (int a = 0;a < size;a++){
//                    Log.i("the key value is: ", arry.get(a));
//                }
//                Book book = new Book("1","1","1","1");
//                requestedList.add(book);
//                Log.i("the key value is:","Test 5 HHHHHHH");
//                //adapter = new ORequestedAdapter(this, 0, requestedList);
//                display_listview.setAdapter(adapter);
//            }
//        });

//        adapter = new ORequestedAdapter(this, 0, requestedList);
//        display_listview.setAdapter(adapter);
        //ArrayList<Book> n = u.getBooks("Available");
        Log.i("the key value is:","Test 2 HHHHHHH");

        display_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = requestedList.get(position);
                Intent intent = new Intent(ORequestedActivity.this , EditBookActivity.class);
                intent.putExtra("BookInformation", book);
                intent.putExtra("Index", position+"");
                startActivityForResult(intent, EDIT_BOOK_REQUEST);
            }
        });




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
