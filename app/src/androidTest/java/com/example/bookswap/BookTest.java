package com.example.bookswap;

import android.graphics.Bitmap;
import android.os.Parcel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


// MUST RUN WITH EMULATOR
public class BookTest {
    @Test
    public void TestWriteConstructParcel() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setStatus("available");
        book.setDescription("some description");
        book.setOwner("Owner");

        Parcel parcel = Parcel.obtain();
        book.writeToParcel(parcel, book.describeContents());
        parcel.setDataPosition(0);

        Book createdFromParcel = Book.CREATOR.createFromParcel(parcel);
        assertEquals(book.getAuthor(), createdFromParcel.getAuthor());
    }

    @Test
    public void GetImage() {
        Bitmap bmp = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Book book = new Book();
        book.setImage(bmp);
        assertTrue(bmp.sameAs(book.getImage()));
    }
}
