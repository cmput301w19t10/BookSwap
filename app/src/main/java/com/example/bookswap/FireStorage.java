
package com.example.bookswap;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * firestorage clase for the storage of images into firebase seperately
 */
public class FireStorage {

    private StorageReference storageRef;
    private DatabaseReference BookDatabase;
    private Uri imgUri;

    public static final String storage_path = "image/";

    /**
     * constructor, sets up some initial instances needed
     */
    public FireStorage(){
        storageRef = FirebaseStorage.getInstance().getReference("Photo");
        BookDatabase = FirebaseDatabase.getInstance().getReference("Book");
    }


    /**
     * adds in a image based on image uri
     * @param book book to add image to
     * @param uri uri of the image.
     */
    public void addImageUri(final Book book, Uri uri) {
        storageRef.child(storage_path + book.getUnikey()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String url = task.getResult().toString();
                        book.setImageUrl(url);
                        BookDatabase.child(book.getUnikey()).child("Photo").setValue(url);
                    }
                });
//                book.setImageUrl(downLoadUrl);
//                BookDatabase.child(book.getUnikey()).child("Photo").setValue(downLoadUrl);
            }
        });
    }
}
