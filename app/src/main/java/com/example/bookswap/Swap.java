package com.example.bookswap;

import java.util.Date;
//Unimplemented class
public class Swap {

    public Swap(){}

    //TODO find how google maps stores GPS location
    public void setLocation(String Location){}

    public void setComment(String Comment){}

    public void setBorrower(String BorrowerName){}

    public void setOwner(String OwnerName){}

    public void setDate(Date Date){}

    public String getLocation(){return "1";}

    public String getComment(){return "1";}

    //Should reuturn date type
    public Date getDate(){
        Date date = new Date("2019-01-01");
        return date;
    }

    public String getOwner(){return "1";}

    public String getBorrower(){return  "1";}
}

