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
    private String password;
    private List<Review> owner_reviews = new ArrayList<>();
    private List<Review> borrower_reviews = new ArrayList<>();


    /**
     *
     * @param name name
     * @param phone_number phone number
     * @param email email
     * @param address address
     */
    public User(String name, String phone_number, String email, String address, String password){
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    /**
     *
     */
    public User(){}

    /**
     * get name
     * @return String of name
     */
    public String getName(){
        return this.name;
    }

    /**
     * get image if in xml file
     * @return backgroud int of this id
     */
    public int getImageId(){
        return this.imageId;
    }

    /**
     * get phone number
     * @return backgroud String of phone number
     */
    public String getPhone_number(){
        return this.phone_number;
    }

    /**
     * get address
     * @return backgroud String of address
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * get email
     * @return backgroud String of email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * get password
     * @return backgroud String of password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * get reviews of owner
     * @return
     */
    public List<Review> getOwnerReviews(){
        return this.owner_reviews;
    }

    /**
     * get reviews of borrower
     * @return
     */
    public List<Review> getBorrowerReviews(){
        return this.borrower_reviews;
    }

    /**
     * get image
     * @return backgroud Bitmap of image
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

    /**
     * set name
     * @param name name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * set imageId in xml file
     * @param imageId backgroud id in xml file
     */
    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    /**
     * set phone number
     * @param number backgroud string of phone number
     */
    public void setPhone_number(String number){
        this.phone_number = number;
    }

    /**
     * set address
     * @param address backgroud string of address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * set email
     * @param email backgroud string of email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * set password
     * @param password backgroud string of password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * add backgroud review in borrower reviews
     * @param review backgroud Review
     */

    public void addBorrower_review(Review review) {
        this.borrower_reviews.add(review);
    }

    /**
     * add backgroud review in owner reviews
     * @param review backgroud Review
     */
    public void addOwner_review(Review review) {
        this.owner_reviews.add(review);
    }

    /**
     * set image
     * @param bmp backgroud Bitmap of image
     */
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
