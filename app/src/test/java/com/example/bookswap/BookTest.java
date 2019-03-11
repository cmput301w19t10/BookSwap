package com.example.bookswap;


import android.support.v4.media.AudioAttributesCompat;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void GetTitle(){
        Book book = new Book();
        String title = "A test";
        book.setTitle(title);
        assertEquals(title, book.getTitle());
    }

    @Test
    public void GetAuthor(){
        Book book = new Book();
        String author = "author nobody";
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }
    @Test
    public void GetStatus(){
        Book book = new Book();
        String status = "available";
        book.setStatus(status);
        assertEquals(status, book.getStatus());
    }
    @Test
    public void GetDescription(){
        Book book = new Book();
        String description = "Umm... Interesting";
        book.setDescription(description);
        assertEquals(description, book.getDescription());

    }

    @Test
    public void GetOwner(){
        Book book = new Book();
        String owner = "John Doe";
        book.setOwner(owner);
        assertEquals(owner,book.getOwner());
    }

    @Test
    public void GetISBN(){
        Book book = new Book();
        String isbn = "G123456789";
        book.setISBN(isbn);
        assertEquals(isbn,book.getISBN());
    }


}
