package com.example.chevie.Models;

/**
 * This is a model for the all Score
 */

public class AllScore {

    //Initializers
    private ScoreHome mScoreHome;
    private String homeLogo;
    private String awayLogo;

    /**
     * Constructor for the model AllScore
     * @param mScoreHome
     * @param homeLogo
     * @param awayLogo
     */
    public AllScore(ScoreHome mScoreHome, String homeLogo, String awayLogo) {
        this.mScoreHome = mScoreHome;
        this.homeLogo = homeLogo;
        this.awayLogo = awayLogo;
    }

    //Getters

    public ScoreHome getmScoreHome() {
        return mScoreHome;
    }

    public String getHomeLogo() {
        return homeLogo;
    }

    public String getAwayLogo() {
        return awayLogo;
    }

}
