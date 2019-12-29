package com.example.chevie.Models;

/**
 * Model for news to be displayed for each sport type
 */

public class News {

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
}
