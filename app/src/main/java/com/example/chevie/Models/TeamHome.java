package com.example.chevie.Models;
/**
 * Model for team on the Home Page
 */
public class TeamHome {

    private String mTeamShort;
    private String mCity;
    private String mName;
    private String mConference;
    private String mDivision;
    private String mHeadCoach;
    private int mByeWeek;
    private String mOffensive;
    private String mDefensive;
    private String mPrimaryColor;
    private String mLogo;
    private String mStadium;

    /**
     * Constructor for the TeamHome Object
     * @param mTeamShort
     * @param mCity
     * @param mName
     * @param mConference
     * @param mDivision
     * @param mHeadCoach
     * @param mByeWeek
     * @param mOffensive
     * @param mDefensive
     * @param mPrimaryColor
     * @param mLogo
     * @param mStadium
     */
    public TeamHome(String mTeamShort, String mCity, String mName, String mConference,
                    String mDivision, String mHeadCoach, int mByeWeek, String mOffensive,
                    String mDefensive, String mPrimaryColor, String mLogo, String mStadium) {
        this.mTeamShort = mTeamShort;
        this.mCity = mCity;
        this.mName = mName;
        this.mConference = mConference;
        this.mDivision = mDivision;
        this.mHeadCoach = mHeadCoach;
        this.mByeWeek = mByeWeek;
        this.mOffensive = mOffensive;
        this.mDefensive = mDefensive;
        this.mPrimaryColor = mPrimaryColor;
        this.mLogo = mLogo;
        this.mStadium = mStadium;
    }


    //Getters

    public String getmTeamShort() {
        return mTeamShort;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmName() {
        return mName;
    }

    public String getmConference() {
        return mConference;
    }

    public String getmDivision() {
        return mDivision;
    }

    public String getmHeadCoach() {
        return mHeadCoach;
    }

    public int getmByeWeek() {
        return mByeWeek;
    }

    public String getmOffensive() {
        return mOffensive;
    }

    public String getmDefensive() {
        return mDefensive;
    }

    public String getmPrimaryColor() {
        return mPrimaryColor;
    }

    public String getmLogo() {
        return mLogo;
    }

    public String getmStadium() {
        return mStadium;
    }
}
