package com.example.chevie.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * News info model
 */

public class NewsInfo implements Parcelable{

    private int mNewsId;
    private int mNewsPlayerId;
    private String mSource;
    private String mTimeShared;
    private String mTitle;
    private String mContent;

    /**
     * Constructor for the news info to be used to create the actual news object
     * @param playerId
     * @param source
     * @param time
     * @param title
     * @param content
     */
    public NewsInfo(int newsId ,int playerId, String source, String time, String title, String content){
        mNewsId = newsId;
        mNewsPlayerId = playerId;
        mSource = source;
        mTimeShared = time;
        mTitle = title;
        mContent = content;
    }

    protected NewsInfo(Parcel in) {
        mNewsId = in.readInt();
        mNewsPlayerId = in.readInt();
        mSource = in.readString();
        mTimeShared = in.readString();
        mTitle = in.readString();
        mContent = in.readString();
    }

    public static final Creator<NewsInfo> CREATOR = new Creator<NewsInfo>() {
        @Override
        public NewsInfo createFromParcel(Parcel in) {
            return new NewsInfo(in);
        }

        @Override
        public NewsInfo[] newArray(int size) {
            return new NewsInfo[size];
        }
    };

    public int getmNewsId() {
        return mNewsId;
    }

    public int getmNewsPlayerId() {
        return mNewsPlayerId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mNewsId);
        dest.writeInt(mNewsPlayerId);
        dest.writeString(mSource);
        dest.writeString(mTimeShared);
        dest.writeString(mTitle);
        dest.writeString(mContent);
    }
}
