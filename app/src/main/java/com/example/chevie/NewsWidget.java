package com.example.chevie;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewsWidget extends AppWidgetProvider {
    private static final String OPENMAIN = "Open news Fragment";
    //initializer
    private int mNewsId;
    private int mPlayerId;
    private String mSource;
    private String mTime;
    private String mContent;
    private String mTitle;
    private String mPhotoUrl;
    private String mShortName;
    private ArrayList<News> mNewsArrayList = new ArrayList<>();


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.news_widget);

            //Open News onClick
            Intent appIntent = new Intent(context, MainActivity.class);
            Bundle extras = new Bundle();
            extras.putInt(OPENMAIN, 0);
            appIntent.putExtras(extras);
            appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);

            new FetchNewsData(views, appWidgetId, appWidgetManager).execute();
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     * AsyncTask to get news Data
     */
    public class FetchNewsData extends AsyncTask<String, Void, ArrayList<News>> {
        private RemoteViews views;
        private int WidgetID;
        private AppWidgetManager WidgetManager;

        public FetchNewsData(RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager){
            this.views = views;
            this.WidgetID = appWidgetID;
            this.WidgetManager = appWidgetManager;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {

            //Just getting the news info
            ArrayList<NewsInfo> mInfoArray = NetworkUtils.fetchNews();

            NewsInfo newsInfo = mInfoArray.get(0);
            mNewsId = newsInfo.getmNewsId();
            mPlayerId = newsInfo.getmNewsPlayerId();
            mSource = newsInfo.getmSource();
            mTime = newsInfo.getmTimeShared();
            mContent = newsInfo.getmContent();
            mTitle = newsInfo.getmTitle();

            //getting player profile info
            ArrayList<PlayerProfile> mPlayerProf = NetworkUtils.fetchPlayerProfile(mPlayerId);
            PlayerProfile playerProfile = mPlayerProf.get(0);
            mPhotoUrl = playerProfile.getmPlayerImg();
            mShortName = playerProfile.getmShortName();

            mNewsArrayList.add(new News(mNewsId, mPlayerId, mShortName, mSource, mTime,
                    mTitle, mContent, mPhotoUrl));

            return mNewsArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            News wdNews = news.get(0);

            views.setTextViewText(R.id.news_title, wdNews.getmTitle());
            Log.d("TITLENEWS", wdNews.getmTitle());
            views.setTextViewText(R.id.news_time_shared, wdNews.getmTimeShared());
            views.setTextViewText(R.id.source, wdNews.getmSource());
            WidgetManager.updateAppWidget(WidgetID, views);
        }
    }
}

