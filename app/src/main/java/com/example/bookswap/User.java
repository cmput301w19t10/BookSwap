package com.example.bookswap;

class User {
    private String name;

    //User module need to compete
    //or do it by other person to compete

    /**
     * retrun the user name
     * @return name
     */
    public String getname() {
        return name;
    }


    /**
     * constructor to build the user class
     * @param name
     */
    public  User(String name) {
        this.name = name;
    }
}
