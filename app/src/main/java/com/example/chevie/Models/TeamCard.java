package com.example.chevie.Models;
/**
 * Model for a team card
 */
public class TeamCard {

    private String mTeamLogo;
    private String mPrimaryColor;
    private String mOffensiveSch;
    private String mDeffensiveSch;
    private int mByeWeek;


    /**
     * Constructor for the TeamCard Object
     * @param teamLogo
     * @param primaryColor
     * @param offensive
     * @param defensive
     * @param byeWeek
     */
    public TeamCard(String teamLogo, String primaryColor, String offensive, String defensive, int byeWeek){
        mTeamLogo = teamLogo;
        mPrimaryColor = primaryColor;
        mOffensiveSch = offensive;
        mDeffensiveSch = defensive;
        mByeWeek = byeWeek;
    }

    public String getmTeamLogo() {
        return mTeamLogo;
    }

    public String getmPrimaryColor() {
        return mPrimaryColor;
    }

    public String getmOffensiveSch() {
        return mOffensiveSch;
    }

    public String getmDeffensiveSch() {
        return mDeffensiveSch;
    }

    public int getmByeWeek() {
        return mByeWeek;
    }
}
