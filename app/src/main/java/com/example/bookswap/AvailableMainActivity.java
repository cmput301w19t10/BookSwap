package com.example.bookswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class AvailableMainActivity extends AppCompatActivity {

    private ArrayList<Book> availableList = new ArrayList<>();//copied into memory
    //private AvailableAdapter adapter; // initialize adapter.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_list);
        Intent intentAva = getIntent();
    }

    /**
     * display the add icon in List activity
     * @param menu menu item of add icon
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_available, menu);
        return true;
    }

    /**
     * when owner tap the add icon button,
     * go to the add available book information activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create: //run NoteActivity in new note mode
                startActivity(new Intent(this, BookActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }





}
