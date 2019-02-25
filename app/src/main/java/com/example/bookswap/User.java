package com.example.bookswap;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {

    private String name;
    private int imageId;
    private String phone_number;
    private String address;
    private String email;
    private ArrayList<Review> owner_reviews;
    private ArrayList<Review> borrower_reviews;



    public User(String name, String phone_number, String email, String address){
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }
    public int getImageId(){
        return this.imageId;
    }


    public String getPhone_number(){
        return this.phone_number;
    }

    public String getAddress(){
        return this.address;
    }

    public String getEmail(){
        return this.email;
    }

    public ArrayList<Review> getOwnerReviews(){
        return this.owner_reviews;
    }

    public ArrayList<Review> getBorrowerReviews(){
        return this.borrower_reviews;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone_number);
        dest.writeString(address);
    }

    protected User(Parcel source){
        imageId = source.readInt();
        name = source.readString();
        email = source.readString();
        phone_number = source.readString();
        address = source.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
