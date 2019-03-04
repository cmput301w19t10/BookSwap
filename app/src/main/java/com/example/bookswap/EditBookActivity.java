package com.example.bookswap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Activity that allows the editting and creation UI of new available book information
 * Take owner's inputs on screen and passes the parcel to parent activity
 *
 * @see OAvailableActivity
 */
public class EditBookActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etAuthor;
    private EditText etDescription;
    private EditText etStatus;
    private static int BOOK_PHOTO_RESULT = 1;

    private Book book;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);
        ImageButton photo = findViewById(R.id.bookPhotoButton);
        photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, BOOK_PHOTO_RESULT);
            }
        });
        Intent intent = getIntent();
        if (intent.getParcelableExtra("Book") != null){
            this.book = intent.getParcelableExtra("Book");
            fillText();
        }


    }


    /**
     * Displays the save icon in recording activity
     *
     * @param menu menu item of save icon
     * @return true for android api
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }


    /**
     * Method used when a user taps a menu icon
     * Tapping Save saves the new or updated recording
     * tapping delete deletes the recording from the file.
     * @param item a menu item the user clicked
     * @return Something for android
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_save:
                updateEditText();
                if (isValid()){ //validate input fields are filled
                    saveBook();
                } else { // send user a message to fill in the required fields
                    Toast.makeText(this,"Please fill in fields", Toast.LENGTH_SHORT).show();
                }
                break;

            // deletion case
            case R.id.action_delete:
                Intent retIntent = new Intent(); // intent to return to parent activity (main)
                Intent intent = getIntent(); // get intent sent from parent
                int i = intent.getIntExtra("Index",-1);
                if (i == -1) { // Can't delete an non-existing file
                    Toast.makeText(this,"nothing to delete", Toast.LENGTH_SHORT).show();
                    break;
                }

                // sends a message to confirm deletion, passes intent to parent with delete flags.
                Toast.makeText(this, "deleting book", Toast.LENGTH_SHORT).show();
                retIntent.putExtra("delete", true);
                retIntent.putExtra("Index", i);
                setResult(Activity.RESULT_OK, retIntent);
                finish();
                break;
        }
        return true;
    }




    private void saveBook(){
        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String status = etStatus.getText().toString();
        String description = etDescription.getText().toString();
        ImageButton bView = findViewById(R.id.bookPhotoButton);
        Bitmap image = ((BitmapDrawable) bView.getDrawable()).getBitmap();


        Book book = new Book(title, author, status, description, image);
        Toast.makeText(this,"Book is saved!",Toast.LENGTH_SHORT).show();

        // Setting up the intent to pass back to parent, including the Recording parcel
        Intent bookIntent = new Intent();
        bookIntent.putExtra("Book", book);

        // Special code used to see if it was a previously existing book
        // passes up some information for existing book
        Intent intent = getIntent();
        int i = intent.getIntExtra("Index",0);
        if (i != 0){
            bookIntent.putExtra("Index", i);
        }
        setResult(Activity.RESULT_OK, bookIntent);
        finish();

    }

    /**
     * updates the EditText we're getting from the owner. Called multiple times within class.
     */
    private void updateEditText(){
        etTitle = ((EditText)findViewById(R.id.etTitle));
        etAuthor = ((EditText)findViewById(R.id.etAuthor));
        etStatus = ((EditText)findViewById(R.id.etStatus));
        etDescription = ((EditText)findViewById(R.id.etDescription));
    }

    /**
     * Fills the text-boxes with any pre-existing data.
     */

    private void fillText(){
        //updateEditText();

        etTitle.setText(String.valueOf(book.getTitle()));
        etAuthor.setText(String.valueOf(book.getAuthor()));
        etDescription.setText(String.valueOf(book.getDescription()));
        etStatus.setText("Available");

    }
    private boolean isValid(){
        if (TextUtils.isEmpty(etTitle.getText().toString())){
            return false;
        } else if (TextUtils.isEmpty(etAuthor.getText().toString())){
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ImageButton photo = findViewById(R.id.bookPhotoButton);
                selectedImage = Bitmap.createScaledBitmap(selectedImage, 150,200,false);
                photo.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(EditBookActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(EditBookActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }






}
