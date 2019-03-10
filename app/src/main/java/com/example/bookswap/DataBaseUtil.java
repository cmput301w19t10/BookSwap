package com.example.bookswap;

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
//        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
//        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }

    //This part is for book

    public ArrayList<Book> getBooks(int status){
        ArrayList<Book> outArray = new ArrayList<>();
        //Book abook1 = aBookinfo(0);
        return outArray;
    }

    // use this function if you want to get book info from firebase as a owner
    private Book aBookinfo(int index){
//        String BookKey;
//        String BookTitle;
//        String BookDes;
//        String BookISBN;
//        String BookStatus;

//        String title = getBookTitle(bookUniKeyList.get(index));
//        String description = getBookDes(bookUniKeyList.get(index));
//        String ISBN = getBookISBN(bookUniKeyList.get(index));
//        String status = getBookStatus(bookUniKeyList.get(index));
        Book aBook = new Book();

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
        UUID number = UUID.randomUUID();
        String BookKey = number.toString();
        this.BookKey = BookKey;
        //BookDatabase.child(BookKey).child("Title").setValue(book.getTitle());
        //BookDescription(book.getDescription());
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
        //   BookDatabase.child(BookKey).child("Description").setValue(description);
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

    // save OwnerBook to user info
    private void OwnerBook(String name,String BookName){
        UserDatabase.child(name).child("OwnerBook").child(BookKey).setValue(BookName);
    }

    // save BorrowerBook to user info
    private  void borrowerBook(String name, String BookName){};


    // This part is for user
    // save user to the database
    public void addNewUser (User user){}
    // save the password
    private void addPassword (String name, String password) {
        //UserDatabase.child(name).child("password").setValue(password);
    }
    // save the email
    private void addEmail (String name, String email) {
        //UserDatabase.child(name).child("email").setValue(email);
    }
    // save the address
    private void addAddress (String name, String address) {
        //UserDatabase.child(name).child("address").setValue(address);
    }
    //save the phone number
    private void addPhone (String name, String phone) {
        //UserDatabase.child(name).child("phone").setValue(phone);
    }


    // get user info from data
    public User getOneUser(String name){
        User user = new User("1","1","1","1", "1");
        return user;
    }
    // get the password
    private String getPassword(String name){
        //TODO implement stub
        return "1";
    }

    // get the email
    private String getEmail(String name){
        //TODO implement stub
        return "1";
    }

    // get the address
    private String getAddress(String name){
        //TODO implement stub
        return "1";
    }

    // get the phone number
    private String getPhone(String name){
        //TODO implement stub
        return "1";
    }


    // This part is for Swap book and change book status
    // change book status
    public void changeStatus(int status){}

    // if there is a new request, this method can assign true to "request"
    // and user can be notified
    public void NewRequest(){}

    // get the value of "Request"
    public boolean checkNewRequest(String name){
        //TODO implement stub
        return true;
    }

    // if there is a new borrow, this method can assign true to "borrow"
    // and user can be notified
    public void NewBorrow(){}

    // get the value of "Borrow"
    public boolean checkNewBorrow(String name){
        //TODO implement stub
        return true;
    }

}