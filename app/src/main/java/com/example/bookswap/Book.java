package com.example.bookswap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;


/**
 * Book class contains getters and setters for book details
 *
 * @title Title of a book
 * @author Author of a book
 * @status unique string to determine what state the book is in, in regads to swap.
 * @isbn bar code of a book
 * @description description of a book
 * @owner owner of books
 * @image Cover of a book
 *
 * Parcelable object code/learning:
 * https://www.sitepoint.com/transfer-data-between-activities-with-android-parcelable/
 */
public class Book implements Parcelable {
    private String title;
    private String author;
    private String status;
    private String isbn;
    private String description;
    private String owner;
    private String image;
    private String uniKey;

  
    /**
     * writes the current state of the book information to a parcel for use in other activities
     *
     * @param out  parcel object to be outputted for useage
     * @param flag flags (0/1) for Parcelable
     */
    public void writeToParcel(Parcel out, int flag){
        out.writeString(title);
        out.writeString(author);
        out.writeString(status);
        out.writeString(isbn);
        out.writeString(description);
        out.writeString(owner);
        out.writeString(image);
        out.writeString(uniKey);
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
    public Book(String title, String author, String status, String description, String owner){
        this.title = title;
        this.author = author;
        this.status = status;
        this.description = description;
        this.owner = owner;
    }


    public Book(Parcel parcel){
        title = parcel.readString();
        author = parcel.readString();
        status = parcel.readString();
        isbn = parcel.readString();
        description = parcel.readString();
        owner = parcel.readString();
        image = parcel.readString();
        uniKey = parcel.readString();
    }

    public Book(){}


    /**
     * return newly populated book object
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

    /**
     * getter for title
     * @return title of book
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter for author
     * @return author of book
     */
    public String getAuthor() {
        return author;
    }
    /**
     * getter for status
     * @return status of book
     */
    public String getStatus() {
        return status;
    }

  
    /**
     * getter for description
     * @return desceiption of book
     */
    public String getDescription() {
        return description;
    }
    /**
     * getter for ISBN
     * @return ISBN of book
     */
    public String getISBN() {
        return isbn;
    }
    /**
     * getter for owner
     * @return status of owner
     */
    public String getOwner() {
        return owner;
    }
    /**
     * getter for image
     * @return image of book
     *
     * Storing bitmap as String:
     * https://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
     *
     */
    public Bitmap getImage() {
        if (image != null) {
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
        return null;
    }

    public String getUnencodedImage(){
        return image;
    }

    public void setUnencodedImage(String image){
        this.image = image;
    }

    /**
     * setter for image
     * @param bmp book cover owner saved
     */
    public void setImage(Bitmap bmp){
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            this.image = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }
    /**
     * setter for title
     * @param title title of the book owner saved
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * setter for author
     * @param author author of the book owner saved in
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * setter for status
     * @param status the status of the book
     */
    public void setStatus(String status){
        this.status = status;

    }
    /**
     * setter for isbn
     * @param isbn bar code of the book
     */

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * setter for description
     * @param description the description of the book
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * setter for owner
     * @param owner the name of the owner
     */
    public void setOwner(String  owner){
        this.owner = owner;
    }

    public void setUnikey(String s){
        this.uniKey = s;
    }


    public String getUnikey(){
        return uniKey;
    }


    /**
     * required for parcelable
     * @return hashcode of object book
     */
    public int describeContents() {
        return hashCode();
    }
}