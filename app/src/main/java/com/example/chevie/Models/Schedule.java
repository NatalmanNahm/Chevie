package com.example.chevie.Models;

/**
 * Model for a schedule
 */
public class Schedule {

    private EventHome mEventHome;
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
     * Constructor for Schedule Object
     * @param mEventHome
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
    public Schedule(EventHome mEventHome, String mStadium, String mTeamOneLogo,
                    String mOneOffensiveSch, String mOneDefensiveSch, int mOneByeWeek,
                    String mTeamTwoLogo, String mTwoOffensiveSch, String mTwoDefensiveSch,
                    int mTwoByeWeek) {
        this.mEventHome = mEventHome;
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

    public EventHome getmEventHome() {
        return mEventHome;
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
