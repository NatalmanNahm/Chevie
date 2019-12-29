package com.example.chevie.Models;

/**
 * Model for each player profile
 */
public class PlayerProfile {

    //Initializer
    private String mPlayerImg;
    private String mShortName;

    /**
     * Constructor for the player Profile object
     * @param image
     * @param shortName
     */
    public PlayerProfile(String image, String shortName){
        mPlayerImg = image;
        mShortName = shortName;
    }

    public String getmPlayerImg() {
        return mPlayerImg;
    }

    public String getmShortName() {
        return mShortName;
    }
}
