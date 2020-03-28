package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for an event object
 */

public class EventHome implements Parcelable {

    private String mHomeTeam;
    private String mAwayTeam;
    private String mDate;
    private String mForcastDesc;
    private int mHigh;
    private int mLow;
    private String mStadium;
    private String mTime;


    /**
     * Constructor for the EventHome Object
     * @param homeTeam
     * @param awayTeam
     * @param date
     * @param forcastDesc
     * @param high
     * @param low
     * @param stadium
     */
    public EventHome (String homeTeam, String awayTeam, String date, String forcastDesc, int high,
                      int low, String stadium, String time){
        mHomeTeam = homeTeam;
        mAwayTeam = awayTeam;
        mDate = date;
        mForcastDesc = forcastDesc;
        mHigh = high;
        mLow = low;
        mStadium = stadium;
        mTime = time;
    }

    protected EventHome(Parcel in) {
        mHomeTeam = in.readString();
        mAwayTeam = in.readString();
        mDate = in.readString();
        mForcastDesc = in.readString();
        mHigh = in.readInt();
        mLow = in.readInt();
        mStadium = in.readString();
        mTime = in.readString();
    }

    public static final Creator<EventHome> CREATOR = new Creator<EventHome>() {
        @Override
        public EventHome createFromParcel(Parcel in) {
            return new EventHome(in);
        }

        @Override
        public EventHome[] newArray(int size) {
            return new EventHome[size];
        }
    };

    public String getmHomeTeam() {
        return mHomeTeam;
    }

    public String getmAwayTeam() {
        return mAwayTeam;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmForcastDesc() {
        return mForcastDesc;
    }

    public int getmHigh() {
        return mHigh;
    }

    public int getmLow() {
        return mLow;
    }

    public String getmStadium() {
        return mStadium;
    }

    public String getmTime() {
        return mTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHomeTeam);
        dest.writeString(mAwayTeam);
        dest.writeString(mDate);
        dest.writeString(mForcastDesc);
        dest.writeInt(mHigh);
        dest.writeInt(mLow);
        dest.writeString(mStadium);
        dest.writeString(mTime);
    }
}
