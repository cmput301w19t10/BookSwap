package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BSearchActivity extends AppCompatActivity {
    private Book book;
    private OSearchAdapter adapter;
    private ArrayList<Book> availableList = new ArrayList<>();
    private ListView myAvailableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_search);
        Intent intent = getIntent();
        adapter = new OSearchAdapter(this,0,availableList);
        myAvailableList = findViewById(R.id.search_bookList);

        // On click listener to find if a list item is tapped
        myAvailableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * On click of an item, starts up an activity with result and passing some information
             * @param parent parent activity
             * @param view current view provided from android
             * @param position index of the item being clicked
             * @param id id of the item being clicked
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = availableList.get(position);
                Intent intent = new Intent(BSearchActivity.this, EditBookActivity.class);
                System.out.println(position);
                intent.putExtra("BookInformation", book);
                intent.putExtra("Index", position);
                //startActivityForResult(intent, EDIT_BOOK_REQUEST);

            }
        });
    }
    /**
     * create the search menu Ui
     * @param menu the menu for search person
     * @return always true to enable creating menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_osearch, menu);

        MenuItem searchItem = menu.findItem(R.id.search_book);
        SearchView searchView = (SearchView)searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}