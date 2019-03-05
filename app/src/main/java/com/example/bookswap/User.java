package com.example.bookswap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * User contains all its info
 */
public class User implements Parcelable {

    private String name;
    private int imageId;
    private String image = null;
    private String phone_number;
    private String address;
    private String email;
    private String password = "";
    private List<Review> owner_reviews;
    private List<Review> borrower_reviews;


    /**
     *
     * @param name
     * @param phone_number
     * @param email
     * @param address
     */
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

    public String getPassword() {
        return this.password;
    }

    public List<Review> getOwnerReviews(){
        return this.owner_reviews;
    }

    public List<Review> getBorrowerReviews(){
        return this.borrower_reviews;
    }

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

    public void setName(String name){
        this.name = name;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public void setPhone_number(String number){
        this.phone_number = number;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setBorrower_reviews(List<Review> borrower_reviews) {
        this.borrower_reviews = borrower_reviews;
    }

    public void setOwner_reviews(List<Review> owner_reviews) {
        this.owner_reviews = owner_reviews;
    }

    public void setImage(Bitmap bmp){
        if (bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            this.image = Base64.encodeToString(b, Base64.DEFAULT);
        }
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
        dest.writeString(password);
        dest.writeTypedList(owner_reviews);
        dest.writeTypedList(borrower_reviews);
    }

    protected User(Parcel source){
        imageId = source.readInt();
        name = source.readString();
        email = source.readString();
        phone_number = source.readString();
        address = source.readString();
        password = source.readString();
        source.readTypedList(owner_reviews, Review.CREATOR);
        source.readTypedList(borrower_reviews, Review.CREATOR);
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
