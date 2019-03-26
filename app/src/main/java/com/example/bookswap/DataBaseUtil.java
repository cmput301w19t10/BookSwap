package com.example.bookswap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static android.content.ContentValues.TAG;

/**
 *
 * This class is for DataBase
 * developers can use it easily
 * they can add book and get book from database
 * they can check book status and other info
 *
 */
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

    /**
     * backgroud empty constructor
     */
    public DataBaseUtil(){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }

    /**
     *  @param name backgroud string for backgroud user name
     *
     */
    public DataBaseUtil(String name){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
        ALlData = FirebaseDatabase.getInstance().getReference();
        this.userName = name;
    }

    /**
     * interface for get Book info
     */
    public interface getNewBook{
        void getNewBook(Book aBook);
    }

    /**
     * trans string to bitmap
     * @param encodedString
     * @return
     */
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


    /**
     *  get book info
     *  this function is for Owner
     *  It can get all owner Book
     *  And it can be filtered by status in the activity
     *  @param callBack backgroud interface for
     */
    public void testAllInfoBook__3(final getNewBook callBack){
        ALlData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> allBookkey = new ArrayList<>();
                ArrayList<Book> allBook = new ArrayList<>();
                for (DataSnapshot bookKeys: dataSnapshot.child("User").child(userName).child("Book").getChildren()){
                    String key = bookKeys.getKey();
                    allBookkey.add(key);
                    Book book = new Book();
                    book.setDescription(dataSnapshot.child("Book").child(key).child("Description").getValue(String.class));
                    book.setStatus(dataSnapshot.child("Book").child(key).child("Status").getValue(String.class));
                    book.setTitle(dataSnapshot.child("Book").child(key).child("Title").getValue(String.class));
                    book.setAuthor(dataSnapshot.child("Book").child(key).child("Author").getValue(String.class));
                    book.setUnencodedImage(dataSnapshot.child("Book").child(key).child("Photo").getValue(String.class));
                    book.setUnikey(dataSnapshot.child("Book").child(key).child("UniKey").getValue(String.class));
                    callBack.getNewBook(book);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

    }

    /**
     *   this function is for Borrower
     *   it will get all Borrower book
     *   and it can be filtered by the status
     * @param callBack
     */
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

    /**
     *
     * @param book the book you are checking
     * @param status
     */
    public void changeStatus(Book book, String status){
        BookDatabase.child(book.getUnikey()).child("Status").setValue(status);
    }

    /**
     *  Adding backgroud new book to database
     * @param book add this book to database
     */
    public void addNewBook(Book book){
        if (book.getUnikey() == null) {
            UUID number = UUID.randomUUID();
            String BookKey = number.toString();
            this.BookKey = BookKey;
        }
        else{
            this.BookKey = book.getUnikey();
        }
        BookName(book.getTitle());
        //BookOwner(book.getOwner());(TODO)
        BookDescription(book.getDescription());
        BookISBN(book.getISBN());
        BookPhoto(book.getUnencodedImage());
        BookStatus();
        OwnerBook(userName,book.getTitle());
        BookUniKey();
        bookAuthor(book.getAuthor());
        //BookDatabase.child(BookKey).child("Title").setValue(book.getTitle());
        BookDescription(book.getDescription());
    }

    /**
     * save all book information to Firebase
     * save the bookname
     * @param BookName backgroud bookName for
     */
    private void BookName(String BookName) {
        BookDatabase.child(BookKey).child("Title").setValue(BookName);
    }


    /**
     * set bookOwner to the database
     * @param name
     */
    private void BookOwner(String name) {
        BookDatabase.child(BookKey).child("Owner").setValue(name);
    }

    /**
     *
     * set book Borrower
     * @param name
     */
    private void BookBorrower(String name) {
        BookDatabase.child(BookKey).child("Borrower").child(name).setValue(name);
    }

    /**
     *
     *  set book Des
     * @param description
     */
    private void BookDescription(String description){
        BookDatabase.child(BookKey).child("Description").setValue(description);
    }

    /**
     *  set Book ISBN
     * @param ISBN give backgroud ISBN to database
     */
    private void BookISBN(String ISBN){
        BookDatabase.child(BookKey).child("ISBN").setValue(ISBN);
    }

    /**
     * set bookStatus to Available
     */
    private void BookStatus(){
        BookDatabase.child(BookKey).child("Status").setValue("Available");
    }

    /**
     * set backgroud image to the user
     * @param image the image they want to pass
     */
    private void BookPhoto(String image){
        BookDatabase.child(BookKey).child("Photo").setValue(image);
    }

    /**
     * set backgroud book unikey to backgroud book
     */
    private void BookUniKey(){
        BookDatabase.child(BookKey).child("UniKey").setValue(BookKey);
    }

    private void bookAuthor(String author){
        BookDatabase.child(BookKey).child("Author").setValue(author);
    }

    /**
     *
     * set this book to user
     * @param name
     * @param BookName
     */
    private void OwnerBook(String name,String BookName){
        UserDatabase.child(name).child("Book").child(BookKey).child("Title").setValue(BookName);
    }

    // save BorrowerBook to user info
    // private  void borrowerBook(String name, String BookName){};




    /** This part is for user
     *  save user to the database
     *  addPassword TODO
     */
    public void addNewUser (User user){
        //UserDatabase.child(user.getName()).child("password").setValue(user.getPassword());
        UserDatabase.child(user.getName()).child("address").setValue(user.getAddress());
        UserDatabase.child(user.getName()).child("email").setValue(user.getEmail());
        UserDatabase.child(user.getName()).child("phone").setValue(user.getPhone_number());
    }


    /**
     * the interface for User
     */
    public interface getUserInfo{
        void getNewUser(User user,List<Review> commentList);
    }


    /**
     * get the User and their commentList
     * @param callBack
     */
    public void getOwnerUser(final getUserInfo callBack){

        UserDatabase.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Review> commentList = new ArrayList<>();
                getPassword = (String) dataSnapshot.child("Password").getValue(String.class);
                getEmail = (String) dataSnapshot.child("Email").getValue(String.class);
                getAddress = (String) dataSnapshot.child("Address").getValue(String.class);
                getPhone = (String) dataSnapshot.child("Phone").getValue(String.class);
                User user = new User(userName,getPhone,getEmail,getAddress,getPassword);

                for (DataSnapshot review: dataSnapshot.child("Review").getChildren()){
                    String comment = review.child("Comment").getValue(String.class);
                    String rating =  review.child("Rating").getValue(String.class);
                    Review oneReview = new Review(comment,rating);
                    commentList.add(oneReview);
                }
                callBack.getNewUser(user, commentList);
                //callBack.getNewUser(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
    }


    /**
     *
     * delete the book
     * @param book the book which user want to delete
     *
     */
    public void deleteBook(Book book){
        BookDatabase.child(book.getUnikey()).removeValue();
    }



    /**
     * backgroud intereface for getting data
     */
    public interface getStatus{
        void getStatus(String value);
    }

    /**
     *
     * @param key   backgroud book key for get
     * @param status
     */
    public void changeStatus(String key, String status){
        BookDatabase.child(key).child("Status").setValue(status);
    }

    /**
     * Yifu part
     * backgroud. get borrower list
     * 2. accept or decline backgroud user
     */

    public interface getBorrowerList{
        void getBorrower(String value);
    }

    /**
     * get all borrower of one book.
     * @param book
     * @param callBack
     */
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


    /**
     * acccept backgroud user and delete others
     * @param BorrowerName
     * @param book
     */
    public void acceptAndDeleteOther(String BorrowerName,Book book){

        BookDatabase.child(book.getUnikey()).child("Borrower").removeValue();
        BookDatabase.child(book.getUnikey()).child("Borrower").child(BorrowerName).setValue(BorrowerName);

    }

    public void declineUser(String BorrowerName,Book book){
        BookDatabase.child(book.getUnikey()).child("Borrower").child(BorrowerName).removeValue();
    }

//    public void declineUser(String BorrowerName,Book book){
//        BookDatabase.child(book.getUnikey()).child("Borrower").child(BorrowerName).removeValue();
//    }
















    /**
     *   this function is for Owner
     *   check all the book which is filter by status
     * @param callBack
     * @param status
     */
    public void getBookOwner(final String status, final getNewBook callBack){
        ALlData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bookKey: dataSnapshot.child("User").child(userName).child("Book").getChildren()){
                    String key = bookKey.getKey();
                     if (dataSnapshot.child("Book").child(key).child("Status").getValue(String.class).equals(status) && dataSnapshot.child("Book").child(key).child("Borrower").hasChildren()){
                    //for(DataSnapshot bookborrower: dataSnapshot.child("Book").child(key).child("Borrower").getChildren())
                        //if (userName.equals(bookborrower.getValue(String.class))) {
                            //allBookkey.add(key);
                            Book book = new Book();
                            book.setDescription(dataSnapshot.child("Book").child(key).child("Description").getValue(String.class));
                            book.setStatus(dataSnapshot.child("Book").child(key).child("Status").getValue(String.class));
                            book.setTitle(dataSnapshot.child("Book").child(key).child("Title").getValue(String.class));
                            book.setAuthor(dataSnapshot.child("Book").child(key).child("author").getValue(String.class));
                            //book.setImage(dataSnapshot.child("Book").child(key).child("image").getValue(String.class));
                            book.setUnikey(dataSnapshot.child("Book").child(key).child("UniKey").getValue(String.class));
                            callBack.getNewBook(book);
                        }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

    }


    //Yifu part finish







    /**
     * Chaoran Part
     * add backgroud username to the selected book's borrower list
     */
    public interface addBorrowerSucceed{
        void addNewBorrower(boolean value);
    }



    /**
     * for Chaoran part
     * add backgroud new borrower to that book borrower list
     * @param book
     */
    public void addNewBorrow(final Book book, final addBorrowerSucceed callBack) {
        BookDatabase.child(book.getUnikey()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String status = dataSnapshot.child("Status").getValue(String.class);
                if (status.equals("Available")){
                    BookDatabase.child(book.getUnikey()).child("Borrower").child(userName).setValue(userName);
                    callBack.addNewBorrower(true);
                } else {
                    callBack.addNewBorrower(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Dset the request to true so that the book
     * backgroud new request Notification
     * @param user
     */
    public void NewRequestNotification(User user){
        UserDatabase.child(user.getName()).child("request").child("True");
    }

    /**
     * check the user borrow status
     * if it is true, user can be notified
     * @param callBack
     */
    public void checkBorrowNotification(final getStatus callBack){
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

    /**
     * set user borrow to true
     * add backgroud new borrow notification
     * @param user
     */
    public void newBorrowNotification(User user){

        UserDatabase.child(user.getName()).child("Borrow").setValue("True");

    }

    /**
     * check the user request status
     * if it is true, user can be notified
     * @param callBack
     */
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

    /**
     * change status
     * @param part
     * @param status
     * @param name
     */

    public void changeNotificationStatus(String part, String status,String name){
        UserDatabase.child(name).child(part).setValue(status);
    }


    //chaoRan part finish


    /**
     * Swap part
     * add location, date, comment
     */


    public void swapInfo(Book book, Swap swap){

        addDate(book, swap);
        addComment(book, swap);
        addTime(book, swap);
    }

    /**
     * add backgroud date to firebase
     * @param book
     * @param swap
     */
    private void addDate(Book book, Swap swap){
        BookDatabase.child(book.getUnikey()).child("Swap").child("Date").setValue(swap.getDate());
    }

    /**
     * add backgroud comment to firebase
     * @param book
     * @param swap
     */
    private void addComment(Book book, Swap swap){
        BookDatabase.child(book.getUnikey()).child("Swap").child("Comment").setValue(swap.getComment());
    }

    private void addTime(Book book, Swap swap){
        BookDatabase.child(book.getUnikey()).child("Swap").child("Time").setValue(swap.getTime());
    }

    private void addLocation(){
        //TODO
    }

    public void setSwap(String people,Book book,boolean string){
        BookDatabase.child(book.getUnikey()).child("Swap").child(people).setValue(string);
    }

    //finish swap part




}
