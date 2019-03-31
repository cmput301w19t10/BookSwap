package com.example.bookswap;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FireStorage {

    private StorageReference storageRef;
    private DatabaseReference BookDatabase;
    private Uri imgUri;

    public static final String storage_path = "image/";

    public FireStorage(){
        storageRef = FirebaseStorage.getInstance().getReference("Photo");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }


    public void addImageUri(final Book book, Uri uri) {
        storageRef.child(storage_path + book.getUnikey()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downLoadUrl = taskSnapshot.getStorage().getDownloadUrl().toString();
                BookDatabase.child(book.getUnikey()).child("Photo").setValue(downLoadUrl);
            }
        });
    }

}
