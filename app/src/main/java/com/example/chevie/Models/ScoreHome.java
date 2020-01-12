package com.example.chevie.Models;

/**
 * Model for the home score
 */
public class ScoreHome {

    //Initializers
    private String mGameKey;
    private int mWeek;
    private String mDate;
    private String mHomeTeam;
    private String mAwayTeam;
    private int mHomeScore;
    private int mHomeQtr1;
    private int mHomeQtr2;
    private int mHomeQtr3;
    private int mHomeQtr4;
    private int mAwayScore;
    private int mAwayQtr1;
    private int mAwayQtr2;
    private int mAwayQtr3;
    private int mAwayQtr4;


    /**
     * Constructor for the ScoreHome;
     * @param mGameKey
     * @param mWeek
     * @param mDate
     * @param mHomeTeam
     * @param mAwayTeam
     * @param mHomeScore
     * @param mHomeQtr1
     * @param mHomeQtr2
     * @param mHomeQtr3
     * @param mHomeQtr4
     * @param mAwayScore
     * @param mAwayQtr1
     * @param mAwayQtr2
     * @param mAwayQtr3
     * @param mAwayQtr4
     */
    public ScoreHome(String mGameKey, int mWeek, String mDate, String mHomeTeam, String mAwayTeam,
                     int mHomeScore, int mHomeQtr1, int mHomeQtr2, int mHomeQtr3, int mHomeQtr4,
                     int mAwayScore, int mAwayQtr1, int mAwayQtr2, int mAwayQtr3, int mAwayQtr4) {
        this.mGameKey = mGameKey;
        this.mWeek = mWeek;
        this.mDate = mDate;
        this.mHomeTeam = mHomeTeam;
        this.mAwayTeam = mAwayTeam;
        this.mHomeScore = mHomeScore;
        this.mHomeQtr1 = mHomeQtr1;
        this.mHomeQtr2 = mHomeQtr2;
        this.mHomeQtr3 = mHomeQtr3;
        this.mHomeQtr4 = mHomeQtr4;
        this.mAwayScore = mAwayScore;
        this.mAwayQtr1 = mAwayQtr1;
        this.mAwayQtr2 = mAwayQtr2;
        this.mAwayQtr3 = mAwayQtr3;
        this.mAwayQtr4 = mAwayQtr4;
    }

    //Getters

    public String getmGameKey() {
        return mGameKey;
    }

    public int getmWeek() {
        return mWeek;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmHomeTeam() {
        return mHomeTeam;
    }

    public String getmAwayTeam() {
        return mAwayTeam;
    }

    public int getmHomeScore() {
        return mHomeScore;
    }

    public int getmHomeQtr1() {
        return mHomeQtr1;
    }

    public int getmHomeQtr2() {
        return mHomeQtr2;
    }

    public int getmHomeQtr3() {
        return mHomeQtr3;
    }

    public int getmHomeQtr4() {
        return mHomeQtr4;
    }

    public int getmAwayScore() {
        return mAwayScore;
    }

    public int getmAwayQtr1() {
        return mAwayQtr1;
    }

    public int getmAwayQtr2() {
        return mAwayQtr2;
    }

    public int getmAwayQtr3() {
        return mAwayQtr3;
    }

    public int getmAwayQtr4() {
        return mAwayQtr4;
    }
}
