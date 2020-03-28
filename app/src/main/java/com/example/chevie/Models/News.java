package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for news to be displayed for each sport type
 */

public class News implements Parcelable {

    //Initializers
    private  NewsInfo mNewsInfo;
    private String mPlayerShortName;
    private String mPlayerPic;

    /**
     * Constructor fo the News Model
     * @param mNewsInfo
     * @param mPlayerShortName
     * @param mPlayerPic
     */
    public News(NewsInfo mNewsInfo, String mPlayerShortName, String mPlayerPic) {
        this.mNewsInfo = mNewsInfo;
        this.mPlayerShortName = mPlayerShortName;
        this.mPlayerPic = mPlayerPic;
    }

    protected News(Parcel in) {
        mNewsInfo = in.readParcelable(NewsInfo.class.getClassLoader());
        mPlayerShortName = in.readString();
        mPlayerPic = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mNewsInfo, flags);
        dest.writeString(mPlayerShortName);
        dest.writeString(mPlayerPic);
    }


    public NewsInfo getmNewsInfo() {
        return mNewsInfo;
    }

    public String getmPlayerShortName() {
        return mPlayerShortName;
    }

    public String getmPlayerPic() {
        return mPlayerPic;
    }
}
