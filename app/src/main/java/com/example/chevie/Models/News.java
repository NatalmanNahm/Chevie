package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for news to be displayed for each sport type
 */

public class News implements Parcelable {

    //Initializers
    private int mNewsPlayerId;
    private String mPlayerShortName;
    private String mSource;
    private String mTimeShared;
    private String mTitle;
    private String mContent;
    private String mPlayerPic;

    /**
     * Constructor for the news
     * Object
     * @param playerId
     * @param shortName
     * @param source
     * @param timeShared
     * @param title
     * @param content
     * @param playerPic
     */
    public News(int playerId, String shortName, String source, String timeShared, String title, String content, String playerPic){
        mNewsPlayerId = playerId;
        mPlayerShortName = shortName;
        mSource = source;
        mTimeShared = timeShared;
        mTitle = title;
        mContent = content;
        mPlayerPic = playerPic;
    }

    //Read from Parcel
    private News (Parcel parcel){
        mNewsPlayerId = parcel.readInt();
        mPlayerShortName = parcel.readString();
        mSource = parcel.readString();
        mTimeShared = parcel.readString();
        mTitle = parcel.readString();
        mContent = parcel.readString();
        mPlayerPic = parcel.readString();
    }

    public int getmNewsPlayerId() {
        return mNewsPlayerId;
    }

    public String getmPlayerShortName() {
        return mPlayerShortName;
    }

    public String getmSource() {
        return mSource;
    }

    public String getmTimeShared() {
        return mTimeShared;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public String getmPlayerPic() {
        return mPlayerPic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Write to parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mNewsPlayerId);
        dest.writeString(mPlayerShortName);
        dest.writeString(mSource);
        dest.writeString(mTimeShared);
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeString(mPlayerPic);
    }

    /**
     * Creating a parcel to be used to in case we need to use it on saveInstance
     */
    public static Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>(){

        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[0];
        }
    };
}
