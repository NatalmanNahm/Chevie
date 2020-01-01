package com.example.chevie.Models;

/**
 * News info model
 */

public class NewsInfo {

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
    public NewsInfo(int playerId, String source, String time, String title, String content){
        mNewsPlayerId = playerId;
        mSource = source;
        mTimeShared = time;
        mTitle = title;
        mContent = content;
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
}
