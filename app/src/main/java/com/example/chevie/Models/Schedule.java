package com.example.chevie.Models;

/**
 * Model for a schedule
 */
public class Schedule {

    private EventHome mEventHome;
    private TeamCard mTeamCardOne;
    private TeamCard mTeamCardTwo;


    /**
     * Constructor for Schedule Object
     * @param mEventHome
     * @param mTeamCardOne
     * @param mTeamCardTwo
     */
    public Schedule(EventHome mEventHome, TeamCard mTeamCardOne, TeamCard mTeamCardTwo) {
        this.mEventHome = mEventHome;
        this.mTeamCardOne = mTeamCardOne;
        this.mTeamCardTwo = mTeamCardTwo;
    }

    public EventHome getmEventHome() {
        return mEventHome;
    }

    public TeamCard getmTeamCardOne() {
        return mTeamCardOne;
    }

    public TeamCard getmTeamCardTwo() {
        return mTeamCardTwo;
    }
}
