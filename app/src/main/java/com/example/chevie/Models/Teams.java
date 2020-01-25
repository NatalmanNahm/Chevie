package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for Teams
 */
public class Teams implements Parcelable {

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


    protected Teams(Parcel in) {
        mTeamName = in.readString();
        mTeamLogo = in.readString();
        mPrimaryColor = in.readString();
        mTeamCity = in.readString();
        mTeamFullName = in.readString();
        mByeWeek = in.readInt();
        mConference = in.readString();
        mDivision = in.readString();
        mDefensive = in.readString();
        mOffensive = in.readString();
        mTeamKey = in.readString();
        mHeadCoach = in.readString();
        mOffensiveCoord = in.readString();
        mDefensiveCoord = in.readString();
        mSpecTeamCoach = in.readString();
        mStadiumName = in.readString();
        mStadiumCity = in.readString();
        mStadiumState = in.readString();
        mStadiumCapacity = in.readInt();
        mStadiumSurface = in.readString();
        mLat = in.readInt();
        mLong = in.readInt();
    }

    public static final Creator<Teams> CREATOR = new Creator<Teams>() {
        @Override
        public Teams createFromParcel(Parcel in) {
            return new Teams(in);
        }

        @Override
        public Teams[] newArray(int size) {
            return new Teams[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTeamName);
        dest.writeString(mTeamLogo);
        dest.writeString(mPrimaryColor);
        dest.writeString(mTeamCity);
        dest.writeString(mTeamFullName);
        dest.writeInt(mByeWeek);
        dest.writeString(mConference);
        dest.writeString(mDivision);
        dest.writeString(mDefensive);
        dest.writeString(mOffensive);
        dest.writeString(mTeamKey);
        dest.writeString(mHeadCoach);
        dest.writeString(mOffensiveCoord);
        dest.writeString(mDefensiveCoord);
        dest.writeString(mSpecTeamCoach);
        dest.writeString(mStadiumName);
        dest.writeString(mStadiumCity);
        dest.writeString(mStadiumState);
        dest.writeInt(mStadiumCapacity);
        dest.writeString(mStadiumSurface);
        dest.writeInt(mLat);
        dest.writeInt(mLong);
    }
}
