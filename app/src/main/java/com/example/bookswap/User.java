package com.example.bookswap;

public class User {

    private String name;
    private int imageId;
    private String phone_number;
    private String address;
    private String email;

    public User(int imageId, String name, String phone_number, String email, String address){
        this.name = name;
        this.imageId = imageId;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }

    public String getName(){
        return this.name;
    }

    public int getImageId(){
        return this.imageId;
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
