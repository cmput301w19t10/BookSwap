package com.example.bookswap;

import android.database.DatabaseUtils;
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
    private String BookAuthor;
    private String userName;

    private String getPassword;
    private String getEmail;
    private String getPhone;
    private String getAddress;



    private ArrayList<String> bookUniKeyList = new ArrayList<String>();
    private DatabaseReference BookDatabase;
    private DatabaseReference UserDatabase;

    public DataBaseUtil(){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }

     DataBaseUtil(String userName){
        UserDatabase = FirebaseDatabase.getInstance().getReference("User");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
        this.userName = userName;
    }

    //This part is for book
    //the Status should be int (TODO)
    public ArrayList<Book> getBooks(String status,ArrayList<String> temp){
        ArrayList<Book> outArray = new ArrayList<>();
        bookUniKey(userName);
        int UniKeySize = temp.size();
        Log.i("apple","bowen" + temp.size());
        for (int i = 0; i < UniKeySize; i++){
            Book abook = aBookinfo(i,temp);
            //Log.d("Bowen",abook.getStatus().toString());
            if (abook.getStatus() == status) {
                outArray.add(abook);
            }
        }
        return outArray;
    }

    // use this function if you want to get book info from firebase as a owner
    private Book aBookinfo(int index,ArrayList<String> temp){
//
//        String BookTitle = getBookTitle(bookUniKeyList.get(index));
//        String BookDes = getBookDes(bookUniKeyList.get(index));
//        String BookISBN = getBookISBN(bookUniKeyList.get(index));
//        String BookStatus = getBookStatus(bookUniKeyList.get(index));
//        String BookAuthor = getBookAuthour(bookUniKeyList.get(index));

        String BookTitle = getBookTitle(temp.get(index));
        String BookDes = getBookDes(temp.get(index));
        //String BookISBN = getBookISBN(temp.get(index));
        String BookStatus = getBookStatus(temp.get(index));
        String BookAuthor = getBookAuthour(temp.get(index));
        Book aBook = new Book(BookTitle,BookAuthor,BookStatus,BookDes);

        return aBook;
    }

    // get all book's unikeya and return that array
    private void bookUniKey(String name){
        //String bookUniKey;
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://all-acticity.firebaseio.com/User/Bowen/Book");
        DatabaseReference User = UserDatabase.child(name);
        final ArrayList<String> temp = new ArrayList<>();
        //addListenerForSingleValueEvent() (might be better)
        //addValueEventListener
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    BookKey = child.getKey();
                    Log.i("MainActivity", BookKey);
                    Log.i("MainActivity",""+temp.size());
                    temp.add(BookKey);

                }
                setBookUniKeyList(temp);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("MainActivity", "onCancelled", firebaseError.toException());
            }
        });
    }

    private void setBookUniKeyList(ArrayList<String> array){
        Log.i("MainActivity","Return size: "+array.size());
        this.bookUniKeyList = array;
    }

    // get book title
    private String getBookTitle(String bookUniKey){
        DatabaseReference refBookName = BookDatabase.child(bookUniKey).child("Title");
        refBookName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookTitle = dataSnapshot.getValue(String.class);
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
        DatabaseReference refBookName = BookDatabase.child(bookUniKey).child("Description");
        refBookName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookDes = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        return BookDes;
    }

    // get book ISBN
    private String getBookISBN(String bookUniKey){
        DatabaseReference refBookName = UserDatabase.child(bookUniKey).child("ISBN");
        refBookName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookISBN = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        return BookISBN;
    }

    //get book status
    private String getBookStatus(String bookUniKey){
        DatabaseReference refBookName = UserDatabase.child(bookUniKey).child("Status");
        refBookName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookStatus = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        return BookStatus;
    }

    //get book author
    private String getBookAuthour(String bookUniKey){
        DatabaseReference refBookName = UserDatabase.child(bookUniKey).child("author");
        refBookName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BookAuthor = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        return BookAuthor;
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
    private void BookPhoto(){
    }

    // save OwnerBook to user info
    private void OwnerBook(String name,String BookName){
        UserDatabase.child(name).child("OwnerBook").child(BookKey).child("Title").setValue(BookName);
    }

    // save BorrowerBook to user info
    private  void borrowerBook(String name, String BookName){};


    // This part is for user
    // save user to the database
    // addPassword TODO
    public void addNewUser (User user){
        addEmail(user.getName(),user.getEmail());
        addAddress(user.getName(),user.getAddress());
        addPhone(user.getName(),user.getPhone_number());
    }
    // save the password
    private void addPassword (String name, String password) {
        UserDatabase.child(name).child("password").setValue(password);
    }
    // save the email
    private void addEmail (String name, String email) {
        UserDatabase.child(name).child("email").setValue(email);
    }
    // save the address
    private void addAddress (String name, String address) {
        UserDatabase.child(name).child("address").setValue(address);
    }
    //save the phone number
    private void addPhone (String name, String phone) {
        UserDatabase.child(name).child("phone").setValue(phone);
    }






    // get user info from data
    public User getOneUser(String name){
        getPassword(name);
        getEmail(name);
        getPhone(name);
        getAddress(name);
        User user = new User(name,getPhone,getEmail,getAddress);
        return user;
    }
    // get the password
    private void getPassword(String name){
        //String getName;
        DatabaseReference User = UserDatabase.child(name).child("password");
        User.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getPassword = (String) dataSnapshot.getValue().toString();
                //Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
        //return getName;
    }

    // get the email
    private void getEmail(String name){
        DatabaseReference User = UserDatabase.child(name).child("email");
        User.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getEmail = (String) dataSnapshot.getValue().toString();
                //Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
        //return "1";
    }

    // get the address
    private void getAddress(String name){
        DatabaseReference User = UserDatabase.child(name).child("address");
        User.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getAddress = (String) dataSnapshot.getValue().toString();
                //Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
        //return "1";
    }

    // get the phone number
    private void getPhone(String name){
        DatabaseReference User = UserDatabase.child(name).child("phone");
        User.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getPhone = (String) dataSnapshot.getValue().toString();
                //Log.i(TAG, dataSnapshot.child("ZNAME").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });
        //return "1";
    }







    // This part is for Swap book and change book status
    // change book status
    public void changeStatus(String key, int status){
        BookDatabase.child(key).child("Status").setValue("Available");
    }

    // if there is a new request, this method can assign true to "request"
    // and user can be notified
    // int would be better TODO
    public void NewRequest(User user){
        UserDatabase.child(user.getName()).child("request").child("True");
    }

    // get the value of "Request"
    public boolean checkNewRequest(String name){
        //TODO implement stub
        return true;
    }

    // if there is a new borrow, this method can assign true to "borrow"
    // and user can be notified
    public void NewBorrow(User user){
        UserDatabase.child(user.getName()).child("request").child("True");
    }

    // get the value of "Borrow"
    public boolean checkNewBorrow(String name){
        //TODO implement stub
        return true;
    }
}

