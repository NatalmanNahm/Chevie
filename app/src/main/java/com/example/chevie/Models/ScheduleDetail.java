package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Schedule detail model
 */
public class ScheduleDetail implements Parcelable {

    private String mHomeTeam;
    private String mAwayTeam;
    private String mDate;
    private String mTime;
    private String mStadium;
    private String mTeamOneLogo;
    private String mOneOffensiveSch;
    private String mOneDefensiveSch;
    private int mOneByeWeek;
    private String mTeamTwoLogo;
    private String mTwoOffensiveSch;
    private String mTwoDefensiveSch;
    private int mTwoByeWeek;


    /**
     * Constructor for the Schedule Detail model
     * @param mHomeTeam
     * @param mAwayTeam
     * @param mDate
     * @param mTime
     * @param mStadium
     * @param mTeamOneLogo
     * @param mOneOffensiveSch
     * @param mOneDefensiveSch
     * @param mOneByeWeek
     * @param mTeamTwoLogo
     * @param mTwoOffensiveSch
     * @param mTwoDefensiveSch
     * @param mTwoByeWeek
     */
    public ScheduleDetail(String mHomeTeam, String mAwayTeam, String mDate, String mTime,
                          String mStadium, String mTeamOneLogo,
                          String mOneOffensiveSch, String mOneDefensiveSch, int mOneByeWeek,
                          String mTeamTwoLogo, String mTwoOffensiveSch, String mTwoDefensiveSch,
                          int mTwoByeWeek) {
        this.mHomeTeam = mHomeTeam;
        this.mAwayTeam = mAwayTeam;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mStadium = mStadium;
        this.mTeamOneLogo = mTeamOneLogo;
        this.mOneOffensiveSch = mOneOffensiveSch;
        this.mOneDefensiveSch = mOneDefensiveSch;
        this.mOneByeWeek = mOneByeWeek;
        this.mTeamTwoLogo = mTeamTwoLogo;
        this.mTwoOffensiveSch = mTwoOffensiveSch;
        this.mTwoDefensiveSch = mTwoDefensiveSch;
        this.mTwoByeWeek = mTwoByeWeek;
    }

    //Getters

    protected ScheduleDetail(Parcel in) {
        mHomeTeam = in.readString();
        mAwayTeam = in.readString();
        mDate = in.readString();
        mTime = in.readString();
        mStadium = in.readString();
        mTeamOneLogo = in.readString();
        mOneOffensiveSch = in.readString();
        mOneDefensiveSch = in.readString();
        mOneByeWeek = in.readInt();
        mTeamTwoLogo = in.readString();
        mTwoOffensiveSch = in.readString();
        mTwoDefensiveSch = in.readString();
        mTwoByeWeek = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHomeTeam);
        dest.writeString(mAwayTeam);
        dest.writeString(mDate);
        dest.writeString(mTime);
        dest.writeString(mStadium);
        dest.writeString(mTeamOneLogo);
        dest.writeString(mOneOffensiveSch);
        dest.writeString(mOneDefensiveSch);
        dest.writeInt(mOneByeWeek);
        dest.writeString(mTeamTwoLogo);
        dest.writeString(mTwoOffensiveSch);
        dest.writeString(mTwoDefensiveSch);
        dest.writeInt(mTwoByeWeek);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduleDetail> CREATOR = new Creator<ScheduleDetail>() {
        @Override
        public ScheduleDetail createFromParcel(Parcel in) {
            return new ScheduleDetail(in);
        }

        @Override
        public ScheduleDetail[] newArray(int size) {
            return new ScheduleDetail[size];
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

    public String getmTime() {
        return mTime;
    }

    public String getmStadium() {
        return mStadium;
    }

    public String getmTeamOneLogo() {
        return mTeamOneLogo;
    }

    public String getmOneOffensiveSch() {
        return mOneOffensiveSch;
    }

    public String getmOneDefensiveSch() {
        return mOneDefensiveSch;
    }

    public int getmOneByeWeek() {
        return mOneByeWeek;
    }

    public String getmTeamTwoLogo() {
        return mTeamTwoLogo;
    }

    public String getmTwoOffensiveSch() {
        return mTwoOffensiveSch;
    }

    public String getmTwoDefensiveSch() {
        return mTwoDefensiveSch;
    }

    public int getmTwoByeWeek() {
        return mTwoByeWeek;
    }
}
