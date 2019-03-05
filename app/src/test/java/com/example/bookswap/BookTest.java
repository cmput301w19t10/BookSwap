package com.example.bookswap;


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


}