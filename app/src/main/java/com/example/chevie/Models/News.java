package com.example.chevie.Models;

/**
 * Model for news to be displayed for each sport type
 */

public class News {

    //Initializers
    private int mNewsId;
    private String mSource;
    private String mTimeShared;
    private String mTitle;
    private String mContent;
    private String mPlayerPic;

    /**
     * Constructor for the news Object
     * @param newsId
     * @param source
     * @param timeShared
     * @param title
     * @param content
     * @param playerPic
     */
    public News(int newsId, String source, String timeShared, String title, String content, String playerPic){
        mNewsId = newsId;
        mSource = source;
        mTimeShared = timeShared;
        mTitle = title;
        mContent = content;
        mPlayerPic = playerPic;
    }

    public int getmNewsId() {
        return mNewsId;
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
