package com.example.bookswap;

import java.util.Date;
//Unimplemented class
public class Swap {
    private String location;
    private String comment;
    private String borrowname;
    private String ownername;
    private Date date;
    private boolean borrowerPermit;
    private boolean ownerPermit;


    public Swap(){}

    //TODO find how google maps stores GPS location
    public void setLocation(String Location){
        this.location = Location;
    }

    public void setComment(String Comment){
        this.comment = Comment;
    }

    public void setBorrower(String BorrowerName){
        this.borrowname = BorrowerName;
    }

    public void setOwner(String OwnerName){
        this.ownername = OwnerName;
    }

    public void setDate(Date Date){
        this.date = Date;
    }

    public void setBorrowerPermit(boolean borrowerPermit){
        this.borrowerPermit = borrowerPermit;
    }

    public void setOwnerPermit(boolean ownerPermit){
        this.ownerPermit = ownerPermit;
    }

    public String getLocation(){
        return location;
        }

    public String getComment(){
        return comment;
    }

    //Should reuturn date type
    public Date getDate(){
        return date;
    }

    public String getOwner(){
        return ownername;
    }

    public String getBorrower(){
        return borrowname;
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

