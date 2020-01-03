package com.example.chevie.Models;

/**
 * Model for the currenTimeFrame
 */
public class currentTimeFrame {

    private String mCurrentSeason;
    private String mCurrentWeek;

    /**
     * Constructor for the currentTimeFrame
     * @param currentSeason
     * @param currentWeek
     */
    public currentTimeFrame(String currentSeason, String currentWeek){
        mCurrentSeason = currentSeason;
        mCurrentWeek = currentWeek;
    }

    public String getmCurrentSeason() {
        return mCurrentSeason;
    }

    public String getmCurrentWeek() {
        return mCurrentWeek;
    }
}
