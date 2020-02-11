package com.example.chevie.Models;

/**
 * Model for user
 */

public class User {
    private String mName;
    private String mEmail;

    //Empty Constructor
    public User(){

    }

    /**
     * Constructor for User
     * @param mName
     * @param mEmail
     */
    public User(String mName, String mEmail) {
        this.mName = mName;
        this.mEmail = mEmail;
    }

    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
