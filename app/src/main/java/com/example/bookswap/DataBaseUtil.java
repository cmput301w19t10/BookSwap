package com.example.bowenhu.bookswap;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class DataBaseUtil {


    private String BookKey;
    private String BookTitle;
    private String BookDes;
    private String BookISBN;
    private String BookStatus;

    private ArrayList<String> bookUniKeyList = new ArrayList<String>();
    private DatabaseReference BookDatabase;
    private DatabaseReference UserDatabase;


    public DataBaseUtil(){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }

    public ArrayList<Book> getOwnerBooks(int status){
        ArrayList<Book> outArray = new ArrayList<>();
        Book abook = aBookinfo(1);
        outArray.add(abook);
        return outArray;
    }

    // use this function if you want to get book info from firebase as a owner
    private Book aBookinfo(int index){


        String title = getBookTitle(bookUniKeyList.get(index));
        String description = getBookDes(bookUniKeyList.get(index));
        String ISBN = getBookISBN(bookUniKeyList.get(index));
        String status = getBookStatus(bookUniKeyList.get(index));
        Book aBook = new Book("1","1","1","33333","321321");


//
//        aBook =
//        aBook.add(title);
//        aBook.add(status);
//        aBook.add(description);
//        aBook.add(ISBN);
        return aBook;
    }

    // get all book's unikeya and return that array
    private ArrayList<String> bookUniKey(){
        String bookUniKey;
        bookUniKey = UserDatabase.child("Bowen").child("Book").getKey();
        bookUniKeyList.add(bookUniKey);
        return bookUniKeyList;
    }

    // get book title
    private String getBookTitle(String bookUniKey){
        //String BookName;
        DatabaseReference refBookName = UserDatabase.child("Book").child(bookUniKey).child("Title");
        refBookName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookTitle = dataSnapshot.getValue(String.class);
                Log.i(TAG, dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
        return BookTitle;
    }

    // get book description
    private String getBookDes(String bookUniKey){
        return BookDes;
    }

    // get book ISBN
    private String getBookISBN(String bookUniKey){
        return BookISBN;
    }

    //get book status
    private String getBookStatus(String bookUniKey){
        return BookStatus;
    }



    // use this function if you want to add a new book (unfinished)

    public void AddNewBook(Book book){
//        UUID number = UUID.randomUUID();
//        String BookKey = number.toString();
//        this.BookKey = BookKey;
//        BookDatabase.child(BookKey).child("Title").setValue(book.getTitle());
//        BookOwner(book.getOwner());
//        BookDescription(book.getDes());
    }

    // save all book information to Firebase
    // save the bookname
    private void BookName(String BookName) {
        //public UUID number = UUID.randomUUID();
        //public String BookKey = number.toString();
        BookDatabase.child(BookKey).child("Title").setValue(BookName);
    }

    // save the bookowner
    private void BookOwner(String name) {
        BookDatabase.child(BookKey).child("Owner").setValue(name);
    }

    // save the borrower
    private void BookBorrower(String name) {
        BookDatabase.child(BookKey).child("Borrower").child(name).setValue(name);
    }

    // save the description
    private void BookDescription(String description){
        BookDatabase.child(BookKey).child("Description").setValue(description);
    }

    // save the ISBN
    private void BookISBN(String ISBN){
        BookDatabase.child(BookKey).child("ISBN").setValue(ISBN);
    }

    // save the status
    private void BookStatus(){
        BookDatabase.child(BookKey).child("Status").setValue("Available");
    }

    // save the photo
    private void BookPhoto(){
    }

    // save book to user info
    private void UserBook(String name,String BookName){
        UserDatabase.child(name).child("Book").child(BookKey).setValue(BookName);
    }





}

