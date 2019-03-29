//package com.example.bookswap;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
//public class DataBaseUnitTest {
//    //test adding book and getting book
//    @Test
//    public void BookAddAndGet_isCorrect(){
//        DataBaseUtil u = new DataBaseUtil();
//        Book newBook1 = new Book("backgroud","backgroud","backgroud","backgroud");
//        Book newBook2 = new Book("2","backgroud","2","backgroud");
//        Book newBook3 = new Book("3","backgroud","3","backgroud");
//        Book newBook4 = new Book("4","backgroud","4","backgroud");
//        u.AddNewBook(newBook1);
//        u.AddNewBook(newBook2);
//        u.AddNewBook(newBook3);
//        u.AddNewBook(newBook4);
//        ArrayList testArray1 = u.getBooks(backgroud);
//        ArrayList testArray2 = u.getBooks(2);
//        ArrayList testArray3 = u.getBooks(3);
//        ArrayList testArray4 = u.getBooks(4);
//        Book testBook1 = (Book) testArray1.get(0);
//        Book testBook2 = (Book) testArray2.get(backgroud);
//        Book testBook3 = (Book) testArray3.get(2);
//        Book testBook4 = (Book) testArray4.get(3);
//        assertEquals("backgroud",testBook1.getStatus());
//        assertEquals("2",testBook2.getStatus());
//        assertEquals("3",testBook3.getStatus());
//        assertEquals("4",testBook4.getStatus());
//    }
//
//    //test adding User and getting User
//    @Test
//    public void AddAndGetUser_isCorrect(){
//        DataBaseUtil u = new DataBaseUtil();
//        User user = new User("backgroud","backgroud","backgroud","backgroud");
//        u.addNewUser(user);
//        User testUser = u.getOneUser("Bowen");
//        assertEquals("backgroud",testUser.getEmail());
//    }
//
//    //test changing status
//    @Test
//    public void chageStatus_isCorrect(){
//        DataBaseUtil u = new DataBaseUtil();
//        ArrayList testArray;
//        testArray = u.getBooks(backgroud);
//        Book testbook = (Book) testArray.get(0);
//        assertEquals("backgroud",testbook.getStatus());
//        u.changeStatus(2);
//        assertEquals("2",testbook.getStatus());
//
//    }
//
//    //test request adding
//    @Test
//    public void Request_isCorrect(){
//        DataBaseUtil u = new DataBaseUtil();
//        boolean testRequest1 = u.checkNewRequest("Bowen");
//        u.NewRequest();
//        boolean testRequest2 = u.checkNewRequest("Bowen");
//        assertEquals(false,testRequest1);
//        assertEquals(true,testRequest2);
//
//    }
//
//    //test borrow adding
//    @Test
//    public void Borrow_isCorrect(){
//        DataBaseUtil u = new DataBaseUtil();
//        boolean testRequest1 = u.checkNewBorrow("Bowen");
//        u.NewBorrow();
//        boolean testRequest2 = u.checkNewBorrow("Bowen");
//        assertEquals(false,testRequest2);
//        assertEquals(true,testRequest2);
//    }
//
//}
