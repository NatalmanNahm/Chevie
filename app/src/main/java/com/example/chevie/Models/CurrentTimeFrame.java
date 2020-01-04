package com.example.chevie.Models;

/**
 * Model for the currenTimeFrame
 */
public class CurrentTimeFrame {

    private String mCurrentSeason;
    private int mCurrentWeek;

    /**
     * Constructor for the CurrentTimeFrame
     * @param currentSeason
     * @param currentWeek
     */
    public CurrentTimeFrame(String currentSeason, int currentWeek){
        mCurrentSeason = currentSeason;
        mCurrentWeek = currentWeek;
    }

    public String getmCurrentSeason() {
        return mCurrentSeason;
    }

    public int getmCurrentWeek() {
        return mCurrentWeek;
    }
}
