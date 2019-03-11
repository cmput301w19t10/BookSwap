package com.example.bookswap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;

public class DataBaseUtil {

    private String BookKey;
    private String BookTitle;
    private String BookDes;
    private String BookISBN;
    private String BookStatus;
    private String BookAuthor;
    private String userName;

    private String getPassword;
    private String getEmail;
    private String getPhone;
    private String getAddress;


    private ArrayList<String> bookUniKeyList = new ArrayList<String>();
    private Book aBook;
    public ArrayList<Book> bookArray = new ArrayList<Book>();
    private DatabaseReference BookDatabase;
    private DatabaseReference UserDatabase;
    private DatabaseReference ALlData;

    //This part is for book
    //the Status should be int (TODO)
    public DataBaseUtil(){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }

    public DataBaseUtil(String name){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
        ALlData = FirebaseDatabase.getInstance().getReference();
        this.userName = name;
    }

    // interface for get Book info
    public interface getNewBook{
        void getNewBook(Book aBook);
    }


    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    // get book info
    // this function is for Owner
    // It can get all owner Book
    // And it can be filtered by status in the activity
    public void testAllInfoBook__3(final getNewBook callBack){
        ALlData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> allBookkey = new ArrayList<>();
                ArrayList<Book> allBook = new ArrayList<>();
                for (DataSnapshot bookKeys: dataSnapshot.child("User").child(userName).child("Book").getChildren()){
                    String key = bookKeys.getKey();
                    allBookkey.add(key);
                    String Des = dataSnapshot.child("Book").child(key).child("Description").getValue(String.class);
                    String Status = dataSnapshot.child("Book").child(key).child("Status").getValue(String.class);
                    String Title = dataSnapshot.child("Book").child(key).child("Title").getValue(String.class);
                    String author = dataSnapshot.child("Book").child(key).child("author").getValue(String.class);
                    String image = dataSnapshot.child("Book").child(key).child("Photo").getValue(String.class);
                    Bitmap tempImage = StringToBitMap(image);
                    Book abook = new Book(Title,"321",Status,"4",tempImage);
                    allBook.add(abook);
                    callBack.getNewBook(abook);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

    }

    // this function is for Borrower
    // it will get all Borrower book
    // and it can be filtered by the status
    public void getBorrowerBook(final getNewBook callBack){
        ALlData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //ArrayList<String> allBookkey = new ArrayList<>();
                ArrayList<Book> allBook = new ArrayList<>();
                for (DataSnapshot bookKey: dataSnapshot.child("Book").getChildren()){
                    String key = bookKey.getKey();
                    for(DataSnapshot bookborrower: dataSnapshot.child("Book").child(key).child("Borrower").getChildren()) {
                        if (userName.equals(bookborrower.getValue(String.class))) {
                            //allBookkey.add(key);
                            Book book = new Book();
                            book.setDescription(dataSnapshot.child("Book").child(key).child("Description").getValue(String.class));
                            book.setStatus(dataSnapshot.child("Book").child(key).child("Status").getValue(String.class));
                            book.setTitle(dataSnapshot.child("Book").child(key).child("Title").getValue(String.class));
                            book.setAuthor(dataSnapshot.child("Book").child(key).child("author").getValue(String.class));
                            //book.setImage(dataSnapshot.child("Book").child(key).child("image").getValue(String.class));
                            book.setUnikey(dataSnapshot.child("Book").child(key).child("UniKey").getValue(String.class));
                            //Book abook = new Book(Title, "321", Status, "4",Unikey);
                            //allBook.add(abook);
                            callBack.getNewBook(book);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

    }


    public void acceptBook(Book book){

    }


    private String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }



    // use this function if you want to add a new book (unfinished)
    public void AddNewBook(Book book){
        UUID number = UUID.randomUUID();
        String BookKey = number.toString();
        this.BookKey = BookKey;
        BookName(book.getTitle());
        //BookOwner(book.getOwner());(TODO)
        BookDescription(book.getDescription());
        BookISBN(book.getISBN());
        String image = BitMapToString(book.getImage());
        BookPhoto(image);
        BookStatus();
        OwnerBook(userName,BookTitle);
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
        BookDatabase.child(BookKey).child("Description").setValue(description);
    }

    // save the ISBN
    private void BookISBN(String ISBN){
        BookDatabase.child(BookKey).child("ISBN").setValue(ISBN);
    }

    // save the status
    // change Available to a int (TODO)
    private void BookStatus(){
        BookDatabase.child(BookKey).child("Status").setValue("Available");
    }

    // save the photo
    private void BookPhoto(String image){
        BookDatabase.child(BookKey).child("Photo").setValue(image);
    }

    private void BookUniKey(){
        BookDatabase.child(BookKey).child("UniKey").setValue(BookKey);
    }

    // save OwnerBook to user info
    private void OwnerBook(String name,String BookName){
        UserDatabase.child(name).child("Book").child(BookKey).child("Title").setValue(BookName);
    }

    // save BorrowerBook to user info
    private  void borrowerBook(String name, String BookName){};




    // This part is for user
    // save user to the database
    // addPassword TODO
    public void addNewUser (User user){
//        addEmail(user.getName(),user.getEmail());
//        addAddress(user.getName(),user.getAddress());
//        addPhone(user.getName(),user.getPhone_number());


        //UserDatabase.child(user.getName()).child("password").setValue(user.getPassword());
        UserDatabase.child(user.getName()).child("address").setValue(user.getAddress());
        UserDatabase.child(user.getName()).child("email").setValue(user.getEmail());
        UserDatabase.child(user.getName()).child("phone").setValue(user.getPhone_number());
    }





    // the interface for User
    public interface getUserInfo{
        void getNewUser(User value);
    }


    // get user info from data
    public void getOwnerUser(final getUserInfo callBack){

        UserDatabase.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getPassword = (String) dataSnapshot.child("Password").getValue(String.class);
                getEmail = (String) dataSnapshot.child("Email").getValue(String.class);
                getAddress = (String) dataSnapshot.child("Address").getValue(String.class);
                getPhone = (String) dataSnapshot.child("Phone").getValue(String.class);
                User user = new User(userName,getPhone,getEmail,getAddress);
                callBack.getNewUser(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }



    public void acceptAndDeleteOther(String BorrowerName,Book book){

        BookDatabase.child(book.getUnikey()).child("Borrow").removeValue();
        BookDatabase.child(book.getUnikey()).child("Borrow").child(BorrowerName).setValue(BorrowerName);

    }

    // the interface for status
    public interface getStatus{
        void getStatus(String value);
    }

    // This part is for Swap book and change book status
    // change book status
    public void changeStatus(String key, String status){
        BookDatabase.child(key).child("Status").setValue(status);
    }

    // if there is a new request, this method can assign true to "request"
    // and user can be notified
    // int would be better TODO
    public void NewRequest(User user){
        UserDatabase.child(user.getName()).child("request").child("True");
    }

    // get the value of "Request"
    public void checkBorrowNotification(final getStatus callBack){
//        TODO implement stub
//        return true;
        UserDatabase.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String status;
                status = dataSnapshot.child("User").child(userName).child("Borrow").getValue(String.class);
                callBack.getStatus(status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    // if there is a new borrow, this method can assign true to "borrow"
    // and user can be notified
    public void NewBorrow(User user){
        UserDatabase.child(user.getName()).child("Borrow").child("True");
    }

    // get the value of "Borrow"
    public void checkRequestNotification(final getStatus callBack){
//        TODO implement stub
//        return true;
        UserDatabase.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String status;
                status = dataSnapshot.child("User").child(userName).child("Request").getValue(String.class);
                callBack.getStatus(status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }



    public interface getBorrowerList{
        void getBorrower(String value);
    }


    public void getBookBorrower(Book book,final getBorrowerList callBack){
        BookDatabase.child(book.getUnikey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot borrower: dataSnapshot.child("Borrower").getChildren()){
                    String borrowerName = borrower.getKey();
                    callBack.getBorrower(borrowerName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





}
