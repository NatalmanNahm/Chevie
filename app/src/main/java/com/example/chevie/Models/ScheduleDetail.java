package com.example.chevie.Models;

/**
 * Schedule detail model
 */
public class ScheduleDetail {

    private String mHomeTeam;
    private String mAwayTeam;
    private String mDate;
    private String mTime;
    private String mForcastDesc;
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
     * Constructor for the Schedule Detail model
     * @param mHomeTeam
     * @param mAwayTeam
     * @param mDate
     * @param mTime
     * @param mForcastDesc
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
    public ScheduleDetail(String mHomeTeam, String mAwayTeam, String mDate, String mTime,
                          String mForcastDesc, String mStadium, String mTeamOneLogo,
                          String mOneOffensiveSch, String mOneDefensiveSch, int mOneByeWeek,
                          String mTeamTwoLogo, String mTwoOffensiveSch, String mTwoDefensiveSch,
                          int mTwoByeWeek) {
        this.mHomeTeam = mHomeTeam;
        this.mAwayTeam = mAwayTeam;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mForcastDesc = mForcastDesc;
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

    //Getters

    public String getmHomeTeam() {
        return mHomeTeam;
    }

    public String getmAwayTeam() {
        return mAwayTeam;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmForcastDesc() {
        return mForcastDesc;
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
