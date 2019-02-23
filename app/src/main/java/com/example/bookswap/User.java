package com.example.bookswap;

public class User {
    private String name;
    private String phone_number;
    private String address;
    private String email;

    public User(String name, String phone_number, String email, String address){
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone_number(){
        return this.phone_number;
    }

    public String getAddress(){
        return this.address;
    }

    public String getEmail(){
        return this.email;
    }
}
