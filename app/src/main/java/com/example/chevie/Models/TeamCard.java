package com.example.chevie.Models;
/**
 * Model for a team card
 */
public class TeamCard {

    private String mTeamOneLogo;
    private String mTeamTwoLogo;
    private String mPrimaryColor;
    private String mOneOffensiveSch;
    private String mTwoOffensiveSch;
    private String mOneDefensiveSch;
    private String mTwoDefensiveSch;
    private int mOneByeWeek;
    private int mTwoByeWeek;


    /**
     * Constructor for the TeamCard Object
     * @param teamOneLogo
     * @param teamTwoLogo
     * @param primaryColor
     * @param offensiveOne
     * @param offensiveTwo
     * @param defensiveOne
     * @param defensiveTwo
     * @param byeWeekOne
     * @param byeWeekTwo
     */
    public TeamCard(String teamOneLogo, String teamTwoLogo, String primaryColor,
                    String offensiveOne, String offensiveTwo, String defensiveOne,
                    String defensiveTwo, int byeWeekOne, int byeWeekTwo){

        mTeamOneLogo = teamOneLogo;
        mTeamTwoLogo = teamTwoLogo;
        mPrimaryColor = primaryColor;
        mOneOffensiveSch = offensiveOne;
        mTwoOffensiveSch = offensiveTwo;
        mOneDefensiveSch = defensiveOne;
        mTwoDefensiveSch = defensiveTwo;
        mOneByeWeek = byeWeekOne;
        mTwoByeWeek = byeWeekTwo;
    }

    public String getmTeamOneLogo() {
        return mTeamOneLogo;
    }

    public String getmTeamTwoLogo() {
        return mTeamTwoLogo;
    }

    public String getmPrimaryColor() {
        return mPrimaryColor;
    }

    public String getmOneOffensiveSch() {
        return mOneOffensiveSch;
    }

    public String getmTwoOffensiveSch() {
        return mTwoOffensiveSch;
    }

    public String getmOneDefensiveSch() {
        return mOneDefensiveSch;
    }

    public String getmTwoDefensiveSch() {
        return mTwoDefensiveSch;
    }

    public int getmOneByeWeek() {
        return mOneByeWeek;
    }

    public int getmTwoByeWeek() {
        return mTwoByeWeek;
    }
}
