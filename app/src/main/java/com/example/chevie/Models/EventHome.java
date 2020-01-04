package com.example.chevie.Models;

/**
 * Model for an event object
 */

public class EventHome {

    private String mHomeTeam;
    private String mAwayTeam;
    private String mDate;
    private String mForcastDesc;
    private int mHigh;
    private int mLow;
    private String mStadium;


    /**
     * Constructor for the EventHome Object
     * @param homeTeam
     * @param awayTeam
     * @param date
     * @param forcastDesc
     * @param high
     * @param low
     * @param stadium
     */
    public EventHome (String homeTeam, String awayTeam, String date, String forcastDesc, int high,
                      int low, String stadium){
        mHomeTeam = homeTeam;
        mAwayTeam = awayTeam;
        mDate = date;
        mForcastDesc = forcastDesc;
        mHigh = high;
        mLow = low;
        mStadium = stadium;
    }

    public String getmHomeTeam() {
        return mHomeTeam;
    }

    public String getmAwayTeam() {
        return mAwayTeam;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmForcastDesc() {
        return mForcastDesc;
    }

    public int getmHigh() {
        return mHigh;
    }

    public int getmLow() {
        return mLow;
    }

    public String getmStadium() {
        return mStadium;
    }
}
