package com.example.bookswap;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * swap class used for trading books from one person to another
 */
public class Swap {
    private LatLng location;
    private String comment;
    private String borrowname;
    private String ownername;
    private String time;
    private Book book;
    private String date;
    private boolean borrowerPermit;
    private boolean ownerPermit;

    /**
     * empty constructor
     */
    public Swap(){}


    /**
     * set up a book for the swap
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * geo location of the swap
     * @param location
     */
    public void setLocation(LatLng location){
        this.location = location;
    }

    /**
     * set up a time to meet, in string format
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * short comment on meetup
     * @param Comment
     */
    public void setComment(String Comment){
        this.comment = Comment;
    }

    /**
     * set a borrower
     * @param BorrowerName
     */
    public void setBorrower(String BorrowerName){
        this.borrowname = BorrowerName;
    }

    /**
     * set the owner of a book in a swap
     * @param OwnerName
     */
    public void setOwner(String OwnerName){
        this.ownername = OwnerName;
    }

    /**
     * set a date of meetup, as string format
     * @param Date
     */
    public void setDate(String Date){
        this.date = Date;
    }

    public void setBorrowerPermit(boolean borrowerPermit){
        this.borrowerPermit = borrowerPermit;
    }

    public void setOwnerPermit(boolean ownerPermit){
        this.ownerPermit = ownerPermit;
    }

    public LatLng getLocation() {
        return location;
    }
    public String getComment(){
        return comment;
    }

    //Should reuturn date type
    public String getDate(){
        return date;
    }

    public String getOwner(){
        return ownername;
    }

    public String getBorrower(){
        return borrowname;
    }

    public String getTime() {
        return time;
    }


    public Book getBook(){
        return book;
    }

    public boolean isBorrowerPermit() {
        return borrowerPermit;
    }

    public boolean isOwnerPermit() {
        return ownerPermit;
    }

    public boolean checkBorrowerOwnerPermit(){
        return borrowerPermit && ownerPermit;
    }

}
