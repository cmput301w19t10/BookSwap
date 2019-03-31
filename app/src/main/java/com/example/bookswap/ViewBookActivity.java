package com.example.bookswap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ViewBookActivity extends AppCompatActivity {

    private static final String FILENAME = "AvailableBooks.sav";

    private TextView vTitle;
    private TextView vAuthor;
    private TextView vDescription;
    private TextView vStatus;
    private ImageView imageView;
    private static int BOOK_PHOTO_RESULT = 1;

    private Book book;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        ImageView photo = findViewById(R.id.bookCover);
        // TODO: view image as larger size
        photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, BOOK_PHOTO_RESULT);
            }
        });
        Intent intent = getIntent();
        if (intent.getParcelableExtra("book") != null){
            this.book = intent.getParcelableExtra("book");
            fillText();
        }
    }

    /**
     * updates the EditText we're getting from the owner. Called multiple times within class.
     */
    private void updateViewText(){
        vTitle = ((TextView)findViewById(R.id.vTitle));
        vAuthor = ((TextView)findViewById(R.id.vAuthor));
        vStatus = ((TextView)findViewById(R.id.vStatus));
        vDescription = ((TextView)findViewById(R.id.vdescription));
        vDescription.setMovementMethod(new ScrollingMovementMethod());
        imageView = ((ImageView)findViewById(R.id.bookCover));
    }

    /**
     * Fills the text-boxes with any pre-existing data.
     */

    private void fillText(){
        updateViewText();

        vTitle.setText(String.valueOf(book.getTitle()));
        vAuthor.setText(String.valueOf(book.getAuthor()));
        vDescription.setText(String.valueOf(book.getDescription()));
        vStatus.setText(String.valueOf(book.getStatus()));
        imageView.setImageBitmap(book.getImage());


    }
    private boolean isValid(){
        if (TextUtils.isEmpty(vTitle.getText().toString())){
            return false;
        } else if (TextUtils.isEmpty(vAuthor.getText().toString())){
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
                //placeholder, change in future
                selectedImage = Bitmap.createScaledBitmap(selectedImage, 300,500,false);
                photo.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ViewBookActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(ViewBookActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
