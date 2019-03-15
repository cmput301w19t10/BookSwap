package com.example.bookswap;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Reviews for users
 */
public class Review implements Parcelable {

    private String rating;
    private String comment;

    public Review(String comment, String rating){
        this.comment = comment;
        this.rating = rating;
    }

    public void setRating(String rating){
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getRating(){
        return this.rating;
    }

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

    protected Review(Parcel source){
        rating = source.readString();
        comment = source.readString();
    }

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
