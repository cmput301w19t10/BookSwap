package com.example.bookswap;

import java.util.ArrayList;

class Book {
    private String name;
    private ArrayList<User> request_list = new ArrayList<User>();

    public Book(String name) {
        this.name = name;
    }
}
