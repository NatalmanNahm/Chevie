package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for a schedule
 */
public class Schedule implements Parcelable {

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

    protected Schedule(Parcel in) {
        mEventHome = in.readParcelable(EventHome.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mEventHome, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };

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
