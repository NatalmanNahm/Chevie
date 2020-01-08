package com.example.chevie.Models;
/**
 * Model for a team card
 */
public class TeamCard {

    private String mTeamOneLogo;
    private String mPrimaryColor;
    private String mOneOffensiveSch;
    private String mOneDefensiveSch;
    private int mOneByeWeek;


    /**
     * Constructor for the TeamCard Object
     * @param teamLogo
     * @param primaryColor
     * @param offensive
     * @param defensive
     * @param byeWeek
     */
    public TeamCard(String teamLogo, String primaryColor, String offensive, String defensive, int byeWeek){
        mTeamOneLogo = teamLogo;
        mPrimaryColor = primaryColor;
        mOneOffensiveSch = offensive;
        mOneDefensiveSch = defensive;
        mOneByeWeek = byeWeek;
    }

    public String getmTeamLogo() {
        return mTeamOneLogo;
    }

    public String getmPrimaryColor() {
        return mPrimaryColor;
    }

    public String getmOneOffensiveSch() {
        return mOneOffensiveSch;
    }

    public String getmDeffensiveSch() {
        return mOneDefensiveSch;
    }

    public int getmOneByeWeek() {
        return mOneByeWeek;
    }
}
