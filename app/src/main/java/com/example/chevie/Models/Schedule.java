package com.example.chevie.Models;

/**
 * Model for a schedule
 */
public class Schedule {

    private String mHomeTeam;
    private String mAwayTeam;
    private String mDate;
    private String mForcastDesc;
    private int mHigh;
    private int mLow;
    private String mStadium;
    private String mTeamOneLogo;
    private String mOneOffensiveSch;
    private String mOneDefensiveSch;
    private int mOneByeWeek;
    private String mTeamTwoLogo;
    private String mTwoOffensiveSch;
    private String mTwoDefensiveSch;
    private int mTwoByeWeek;

    /**
     * Constructor for the Schedule model
     * @param mHomeTeam
     * @param mAwayTeam
     * @param mDate
     * @param mForcastDesc
     * @param mHigh
     * @param mLow
     * @param mStadium
     * @param mTeamOneLogo
     * @param mOneOffensiveSch
     * @param mOneDefensiveSch
     * @param mOneByeWeek
     * @param mTeamTwoLogo
     * @param mTwoOffensiveSch
     * @param mTwoDefensiveSch
     * @param mTwoByeWeek
     */
    public Schedule(String mHomeTeam, String mAwayTeam, String mDate, String mForcastDesc,
                    int mHigh, int mLow, String mStadium, String mTeamOneLogo, String mOneOffensiveSch,
                    String mOneDefensiveSch, int mOneByeWeek,String mTeamTwoLogo,
                    String mTwoOffensiveSch, String mTwoDefensiveSch, int mTwoByeWeek) {
        this.mHomeTeam = mHomeTeam;
        this.mAwayTeam = mAwayTeam;
        this.mDate = mDate;
        this.mForcastDesc = mForcastDesc;
        this.mHigh = mHigh;
        this.mLow = mLow;
        this.mStadium = mStadium;
        this.mTeamOneLogo = mTeamOneLogo;
        this.mOneOffensiveSch = mOneOffensiveSch;
        this.mOneDefensiveSch = mOneDefensiveSch;
        this.mOneByeWeek = mOneByeWeek;
        this.mTeamTwoLogo = mTeamTwoLogo;
        this.mTwoOffensiveSch = mTwoOffensiveSch;
        this.mTwoDefensiveSch = mTwoDefensiveSch;
        this.mTwoByeWeek = mTwoByeWeek;
    }

    //Getter

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

    public String getmTeamOneLogo() {
        return mTeamOneLogo;
    }

    public String getmOneOffensiveSch() {
        return mOneOffensiveSch;
    }

    public String getmOneDefensiveSch() {
        return mOneDefensiveSch;
    }

    public int getmOneByeWeek() {
        return mOneByeWeek;
    }

    public String getmTeamTwoLogo() {
        return mTeamTwoLogo;
    }

    public String getmTwoOffensiveSch() {
        return mTwoOffensiveSch;
    }

    public String getmTwoDefensiveSch() {
        return mTwoDefensiveSch;
    }

    public int getmTwoByeWeek() {
        return mTwoByeWeek;
    }
}
