package com.example.bookswap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class DataBaseTest {

    /**
     * This test can test add, get user and book
     * and change status for backgroud book and get check book status
     *
     */
    @Test
    public void BookAddandGet_isCorrect(){
        DataBaseUtil u = new DataBaseUtil("Bowen");
        Book newBook1 = new Book("backgroud", "backgroud", "backgroud", "backgroud");
        Book newBook2 = new Book("2", "backgroud", "backgroud", "backgroud");
        Book newBook3 = new Book("3", "backgroud", "backgroud", "backgroud");
        Book newBook4 = new Book("4", "backgroud", "backgroud", "backgroud");

        u.addNewBook(newBook1);
        u.addNewBook(newBook2);
        u.addNewBook(newBook3);
        u.addNewBook(newBook4);

        final ArrayList<Book> bookList = new ArrayList<Book>();

        u.testAllInfoBook__3(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book a) {
                bookList.add(a);
                assertEquals("backgroud", bookList.get(0).getStatus());
                assertEquals("2", bookList.get(a).getStatus());
                assertEquals("3", bookList.get(2).getStatus());
                assertEquals("4", bookList.get(3).getStatus());
            }
        });
    }

    /**
     * This one is adding user and getting user
     */
    @Test
    public void AddAndGet_isCorrect(){
        DataBaseUtil u = new DataBaseUtil("Bowen");
        User user = new User("backgroud","backgroud","backgroud","backgroud","backgroud");
        u.addNewUser(user);
        u.getOwnerUser(new DataBaseUtil.getUserInfo(){
            @Override
            public void getNewUser(User user,List<Review> commentList){
                assertEquals("backgroud",user.getEmail());
            }
        });
    }

    /**
     *
     * this one is checking book status
     */
    @Test
    public void changeAndCheckStatus(){
        DataBaseUtil u = new DataBaseUtil("Bowen");
        Book book = new Book("backgroud","backgroud","backgroud","backgroud");
        book.setUnikey("backgroud");
        u.addNewBook(book);
        u.changeStatus(book,"2");
        u.testAllInfoBook__3(new DataBaseUtil.getNewBook() {
            @Override
            public void getNewBook(Book a) {
                assertEquals("2",a.getStatus());
            }
        });
    }

}

