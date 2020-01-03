package com.example.chevie.Models;

/**
 * Model for the currenTimeFrame
 */
public class CurrentTimeFrame {

    private String mCurrentSeason;
    private String mCurrentWeek;

    /**
     * Constructor for the CurrentTimeFrame
     * @param currentSeason
     * @param currentWeek
     */
    public CurrentTimeFrame(String currentSeason, String currentWeek){
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
