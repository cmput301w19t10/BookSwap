package com.example.bookswap;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ViewBookActivity extends AppCompatActivity {

    private static final String FILENAME = "AvailableBooks.sav";

    private TextView vTitle;
    private TextView vAuthor;
    private TextView vDescription;
    private TextView vStatus;
    private TextView tvISBN;
    private ImageButton imageButton;
    private static int BOOK_PHOTO_RESULT = 1;

    private Book book;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);
        // TODO: view image as larger size
        imageButton = findViewById(R.id.bookCover);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (book.getImageUrl() != null) {
                    final Dialog d = new Dialog(getApplicationContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    d.setCancelable(true);
                    d.setContentView(R.layout.fullscreen_image);
                    ImageView imageView = d.findViewById(R.id.fs_image);
                    Picasso.get()
                            .load(book.getImageUrl())
                            .into(imageView);
                    d.show();
                }

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
        imageButton = ((ImageButton)findViewById(R.id.bookCover));
        tvISBN = findViewById(R.id.tvISBN);

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
        String isbnText = "ISBN: " + String.valueOf(book.getISBN());
        tvISBN.setText(isbnText);
        //imageView.setImageBitmap(book.getImage());

        Picasso.get()
                .load(book.getImageUrl())
                .into(imageButton);


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
