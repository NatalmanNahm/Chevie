package com.example.chevie.Models;

/**
 * Model for each player profile
 */
public class PlayerProfile {

    //Initializer
    private String mPlayerImg;
    private String mShortName;
    private int mAge;
    private String mPosition;

    /**
     * Constructor for the player Profile object
     * @param mPlayerImg
     * @param mShortName
     * @param mAge
     * @param mPosition
     */
    public PlayerProfile(String mPlayerImg, String mShortName, int mAge, String mPosition) {
        this.mPlayerImg = mPlayerImg;
        this.mShortName = mShortName;
        this.mAge = mAge;
        this.mPosition = mPosition;
    }

    public String getmPlayerImg() {
        return mPlayerImg;
    }

    public String getmShortName() {
        return mShortName;
    }

    public int getmAge() {
        return mAge;
    }

    public String getmPosition() {
        return mPosition;
    }
}
