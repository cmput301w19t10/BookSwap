package com.example.bookswap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bookswap.barcode.BarcodeScannerActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * OAvailableActivity represents the list view on the main screen
 * also should load from database, selecting an available book(list of item), and
 * add a new available book
 *
 * @see OAvailableAdapter
 * @see EditBookActivity
 */

public class OAvailableActivity extends AppCompatActivity {

    private static final String FILENAME = "AvailableBooks.sav"; // save file name
    //ID of request codes to add/edit
    private static final int ADD_BOOK_REQUEST = 1;
    private static final int EDIT_BOOK_REQUEST = 2;
    private static final int SCAN_BOOK_REQUEST = 3;

    DataBaseUtil util = new DataBaseUtil("no one");
    private ArrayList<Book> availableList = new ArrayList<>();//copied into memory
    private OAvailableAdapter adapter; // initialize adapter.
    private ListView oldAvailableList;
    private ProgressDialog pd;
    /**
     * On create of the activity override
     * loads the file, sets up the adapter, loads file into array list, sets on click listener for
     * recordings in the listview
     * @param savedInstanceState Android bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_list);
        Intent intentAva = getIntent();
        //loadFromFile();
        adapter = new OAvailableAdapter(this, 0, availableList);
        oldAvailableList = findViewById(R.id.mainAvailableList);
        util.getOwnerBook(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book aBook) {
                if (aBook.getStatus().equals("Available")){
                    availableList.add(aBook);
                    oldAvailableList.setAdapter(adapter);
                }
            }
        });



        // On click listener to find if a list item is tapped
        oldAvailableList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
                Intent intent = new Intent(OAvailableActivity.this, EditBookActivity.class);
                System.out.println(position);
                intent.putExtra("BookInformation", book);
                intent.putExtra("Index", position);
                startActivityForResult(intent, EDIT_BOOK_REQUEST);

            }
        });
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
                startActivityForResult(new Intent(this, EditBookActivity.class), ADD_BOOK_REQUEST);
                break;
            case R.id.scan_add:
                startActivityForResult(new Intent(this, BarcodeScannerActivity.class), SCAN_BOOK_REQUEST);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * saves the current list into a json file using Google Gson
     */
    private void saveInFile() {
        try {
            FileWriter out = new FileWriter(new File(getFilesDir(), FILENAME));
            Gson gson = new Gson();
            gson.toJson(availableList, out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the list stored in the save file using Google Gson
     */
    private void loadFromFile() {
        try {
            FileReader in = new FileReader(new File(getFilesDir(), FILENAME));
            Gson gson = new Gson();

            Type listtype = new TypeToken<ArrayList<Book>>(){}.getType();
            availableList = gson.fromJson(in, listtype);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * On activity result, called after the EditBookActivity ends.
     * Has 2 cases, one adds a new item to the list; the other handles editing/deleting of an
     * existing item.
     *
     * Regardless of case, calls saveInFile() and updates adapter display after.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_BOOK_REQUEST) { // adding a new record
            if (resultCode == Activity.RESULT_OK) {
                Book book = data.getParcelableExtra("Book");
                availableList.add(book);
                util.addNewBook(book);
            }
        } else if (requestCode == EDIT_BOOK_REQUEST) { // editing (and possible deletion)
            if (resultCode == Activity.RESULT_OK) {
                int index = data.getIntExtra("Index", 0);

                //if user tapped delete
                if (data.getBooleanExtra("delete", false)) {
                    util.deleteBook(availableList.get(index));
                    availableList.remove(index);
                } else{ // not delete case
                    Book book = data.getParcelableExtra("Book");
                    availableList.set(index, book);
                }
            }
        }
        // update adapter, save to file
        adapter.notifyDataSetChanged();

        if (requestCode == SCAN_BOOK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String isbn = data.getStringExtra("ISBN");
                new JsonTask().execute("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn);

            }
        }
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(OAvailableActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
            Log.d("ERICHI", result);
        }
    }
}

