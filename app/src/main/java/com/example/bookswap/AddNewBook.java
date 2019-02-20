package com.example.bowenhu.bookswap;

import java.util.UUID;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewBook {
    static UUID number = UUID.randomUUID();
    static String BookKey = number.toString();
    static DatabaseReference BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    static DatabaseReference UserDatabase = FirebaseDatabase.getInstance().getReference("User");

    public static void BookName(String BookName) {
        BookDatabase.child(BookKey).child("Title").setValue(BookName);
    }

    public static void BookOwner(String name) {
        BookDatabase.child(BookKey).child("Owner").setValue(name);
    }

    public static void BookBorrower(String name) {
        BookDatabase.child(BookKey).child("Borrower").child(name).setValue(name);
    }

    public static void BookDescription(String description){
        BookDatabase.child(BookKey).child("Description").setValue(description);
    }

    public static void BookISBN(String ISBN){
        BookDatabase.child(BookKey).child("ISBN").setValue(ISBN);
    }

    public static void BookStatus(){
        BookDatabase.child(BookKey).child("Status").setValue("Available");
    }

    public static void BookPhoto(){
    }

    public static void UserBook(String name,String BookName){
        UserDatabase.child(name).child("Book").child(BookKey).setValue(BookName);
    }
}
