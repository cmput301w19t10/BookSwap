package com.example.bookswap;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Reviews for users
 */
public class Review implements Parcelable {

    private String rating;
    private String comment;

    /**
     * constrcytor
     * @param comment a comment in review
     * @param rating a rating in review
     */
    public Review(String comment, String rating){
        this.comment = comment;
        this.rating = rating;
    }

    /**
     * set rating
     * @param rating a rating in review
     */
    public void setRating(String rating){
        this.rating = rating;
    }

    /**
     * set comment
     * @param comment a comment in review
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * get rating
     * @return rating
     */
    public String getRating(){
        return this.rating;
    }

    /**
     * get comment
     * @return comment
     */
    public String getComment(){
        return this.comment;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rating);
        dest.writeString(comment);
    }

    /**
     * constructor for parcel
     * @param source
     */
    protected Review(Parcel source){
        rating = source.readString();
        comment = source.readString();
    }

    /**
     * creator for parcel
     */
    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel source) {
            return new Review(source);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };
}
