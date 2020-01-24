package com.example.chevie.Models;

/**
 * Model for Teams
 */
public class Teams {

    //initializer
    private String mTeamName;
    private String mTeamLogo;
    private String mPrimaryColor;
    private String mTeamCity;
    private String mTeamFullName;
    private int mByeWeek;
    private String mConference;
    private String mDivision;
    private String mDefensive;
    private String mOffensive;
    private String mTeamKey;
    private String mHeadCoach;
    private String mOffensiveCoord;
    private String mDefensiveCoord;
    private String mSpecTeamCoach;
    private String mStadiumName;
    private String mStadiumCity;
    private String mStadiumState;
    private int mStadiumCapacity;
    private String mStadiumSurface;
    private int mLat;
    private int mLong;

    /**
     * Constructor for teams
     * @param mTeamName
     * @param mTeamLogo
     * @param mPrimaryColor
     * @param mTeamFullName
     * @param mByeWeek
     * @param mConference
     * @param mDivision
     * @param mDefensive
     * @param mOffensive
     * @param mTeamKey
     * @param mHeadCoach
     * @param mOffensiveCoord
     * @param mDefensiveCoord
     * @param mSpecTeamCoach
     * @param mStadiumName
     * @param mStadiumCity
     * @param mStadiumState
     * @param mStadiumCapacity
     * @param mStadiumSurface
     * @param mLat
     * @param mLong
     */
    public Teams(String mTeamName, String mTeamLogo, String mPrimaryColor, String mTeamFullName,
                 String city ,int mByeWeek, String mConference, String mDivision, String mDefensive,
                 String mOffensive, String mTeamKey, String mHeadCoach, String mOffensiveCoord,
                 String mDefensiveCoord, String mSpecTeamCoach, String mStadiumName,
                 String mStadiumCity, String mStadiumState, int mStadiumCapacity,
                 String mStadiumSurface, int mLat, int mLong) {
        this.mTeamName = mTeamName;
        this.mTeamLogo = mTeamLogo;
        this.mPrimaryColor = mPrimaryColor;
        this.mTeamFullName = mTeamFullName;
        this.mTeamCity = city;
        this.mByeWeek = mByeWeek;
        this.mConference = mConference;
        this.mDivision = mDivision;
        this.mDefensive = mDefensive;
        this.mOffensive = mOffensive;
        this.mTeamKey = mTeamKey;
        this.mHeadCoach = mHeadCoach;
        this.mOffensiveCoord = mOffensiveCoord;
        this.mDefensiveCoord = mDefensiveCoord;
        this.mSpecTeamCoach = mSpecTeamCoach;
        this.mStadiumName = mStadiumName;
        this.mStadiumCity = mStadiumCity;
        this.mStadiumState = mStadiumState;
        this.mStadiumCapacity = mStadiumCapacity;
        this.mStadiumSurface = mStadiumSurface;
        this.mLat = mLat;
        this.mLong = mLong;
    }


    //Getters
    public String getmTeamName() {
        return mTeamName;
    }

    public String getmTeamLogo() {
        return mTeamLogo;
    }

    public String getmPrimaryColor() {
        return mPrimaryColor;
    }

    public String getmTeamFullName() {
        return mTeamFullName;
    }

    public String getmTeamCity() {
        return mTeamCity;
    }

    public int getmByeWeek() {
        return mByeWeek;
    }

    public String getmConference() {
        return mConference;
    }

    public String getmDivision() {
        return mDivision;
    }

    public String getmDefensive() {
        return mDefensive;
    }

    public String getmOffensive() {
        return mOffensive;
    }

    public String getmTeamKey() {
        return mTeamKey;
    }

    public String getmHeadCoach() {
        return mHeadCoach;
    }

    public String getmOffensiveCoord() {
        return mOffensiveCoord;
    }

    public String getmDefensiveCoord() {
        return mDefensiveCoord;
    }

    public String getmSpecTeamCoach() {
        return mSpecTeamCoach;
    }

    public String getmStadiumName() {
        return mStadiumName;
    }

    public String getmStadiumCity() {
        return mStadiumCity;
    }

    public String getmStadiumState() {
        return mStadiumState;
    }

    public int getmStadiumCapacity() {
        return mStadiumCapacity;
    }

    public String getmStadiumSurface() {
        return mStadiumSurface;
    }

    public int getmLat() {
        return mLat;
    }

    public int getmLong() {
        return mLong;
    }
}
