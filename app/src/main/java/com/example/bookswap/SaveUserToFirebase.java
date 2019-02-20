package com.example.bowenhu.bookswap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SaveUserToFirebase {


    static DatabaseReference UserDatabase = FirebaseDatabase.getInstance().getReference("User");

    public static void addPassword (String name, String password) {
        UserDatabase.child(name).child("password").setValue(password);
    }

    public static void addEmail (String name, String email) {
        UserDatabase.child(name).child("email").setValue(email);
    }

    public static void addAddress (String name, String address) {
        UserDatabase.child(name).child("address").setValue(address);
    }

    public static void addPhone (String name, String phone) {
        UserDatabase.child(name).child("phone").setValue(phone);
    }

}
