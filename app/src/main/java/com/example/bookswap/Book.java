package com.example.bookswap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String status;
    private String isbn;
    private String description;
    private String owner;
    private byte[] image;


    public void writeToParcel(Parcel out, int flag){
        out.writeString(title);
        out.writeString(author);
        out.writeString(status);
        out.writeString(isbn);
        out.writeString(description);
        out.writeString(owner);
        if (image != null) {
            out.writeInt(image.length);
            out.writeByteArray(image);
        }
    }


    public Book(String title, String author, String status, String description, Bitmap bmp){
        this.title = title;
        this.author = author;
        this.status = status;
        this.description = description;
        setImage(bmp);
    }

    public Book(String title, String author, String status, String description){
        this.title = title;
        this.author = author;
        this.status = status;
        this.description = description;
    }


    public Book(Parcel parcel){
        title = parcel.readString();
        author = parcel.readString();
        status = parcel.readString();
        isbn = parcel.readString();
        description = parcel.readString();
        owner = parcel.readString();
        this.image = new byte[parcel.readInt()];
        parcel.readByteArray(this.image);

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

    public String getOwner() {
        return owner;
    }

    public Bitmap getImage() {
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        return bmp;
    }

    public void setImage(Bitmap bmp){
        if (bmp != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            image = byteArray;
        }
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setStatus(String status){
        this.status = status;

    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setOwner(String  owner){
        this.owner = owner;
    }

    // required for parcelable
    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
