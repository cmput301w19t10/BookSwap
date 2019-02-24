package com.example.bookswap;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String status;
    private String isbn;
    private String description;
    private String image;
    //TO DO : Location


    public void writeToParcel(Parcel out, int flag){
        out.writeString(title);
        out.writeString(author);
        out.writeString(isbn);
        out.writeString(description);
        out.writeString(image);

    }

    //temporary use
    public Book(String title, String author, String status, String description){
        this.title = title;
        this.author = author;
        this.status = status;
        this.description = description;
    }

    public Book(String title, String author, String status, String isbn, String description, String image){
        this.title = title;
        this.author = author;
        this.status = status;
        this.isbn = isbn;
        this.description = description;
        this.image = image;
    }


    public Book(Parcel parcel){
        title = parcel.readString();
        author = parcel.readString();
        status = parcel.readString();
        isbn = parcel.readString();
        description =parcel.readString();

    }


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

    public String getImage() {
        return image;
    }


    // required for parcelable
    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
