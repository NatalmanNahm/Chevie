package com.example.chevie.Models;

/**
 * Model for Teams
 */
public class Teams {

    //initializer
    private String mTeamName;
    private String mTeamLogo;
    private String mDefensive;
    private String mOffensive;

    /**
     * Constructor for the Teams
     * @param mTeamName
     * @param mTeamLogo
     * @param mDefensive
     * @param mOffensive
     */
    public Teams(String mTeamName, String mTeamLogo, String mDefensive, String mOffensive) {
        this.mTeamName = mTeamName;
        this.mTeamLogo = mTeamLogo;
        this.mDefensive = mDefensive;
        this.mOffensive = mOffensive;
    }

    //Getters
    public String getmTeamName() {
        return mTeamName;
    }

    public String getmTeamLogo() {
        return mTeamLogo;
    }

    public String getmDefensive() {
        return mDefensive;
    }

    public String getmOffensive() {
        return mOffensive;
    }
}
