package com.example.chevie.Models;

/**
 * Model for the currenTimeFrame
 */
public class TimeFrame {

    private String mCurrentSeason;
    private int mCurrentWeek;

    /**
     * Constructor for the TimeFrame
     * @param currentSeason
     * @param currentWeek
     */
    public TimeFrame(String currentSeason, int currentWeek){
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
