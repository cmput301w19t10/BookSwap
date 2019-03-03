package com.example.bookswap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String status;
    private String isbn;
    private String description;
    private String image;


    public void writeToParcel(Parcel out, int flag){
        out.writeString(title);
        out.writeString(author);
        out.writeString(status);
        out.writeString(isbn);
        out.writeString(description);
        if (image != null) {
            out.writeString(image);
        }
    }

    //temporary use
    public Book(String title, String author, String status, String description, Bitmap bmp){
        this.title = title;
        this.author = author;
        this.status = status;
        this.description = description;
        setImage(bmp);
    }


    public Book(Parcel parcel){
        title = parcel.readString();
        author = parcel.readString();
        status = parcel.readString();
        isbn = parcel.readString();
        description = parcel.readString();
        image = parcel.readString();
    }

    public Book(){}


    /**
     * return newly populated object
     */
    public static final Parcelable.Creator<Book> CREATOR
            = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel parcel) {
            return new Book(parcel);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[0];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getISBN() {
        return isbn;
    }


    // Storing bitmap as String:
    // https://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    public Bitmap getImage() {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bmp;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void setImage(Bitmap bmp){
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            this.image = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setStatus(String author){

    }

    public void setDescription(String description){
        this.description = description;
    }


    // required for parcelable
    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
