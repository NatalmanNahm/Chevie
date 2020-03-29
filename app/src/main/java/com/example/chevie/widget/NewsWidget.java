package com.example.chevie.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;

import com.example.chevie.MainActivity;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewsWidget extends AppWidgetProvider {
    //initializer
    private  PendingIntent pendingIntent;
    int startTime;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        startTime = startTime + (1000 * 60 * 30);

        final AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        final  Intent intent = new Intent(context, UpdateService.class);

        if (pendingIntent == null){
            pendingIntent = PendingIntent.getService(context, 0, intent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
        }

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);


//        if (Build.VERSION.SDK_INT < 23) {
//            if (Build.VERSION.SDK_INT >= 19) {
//                if(System.currentTimeMillis()<startTime)
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
//            } else {
//                if(System.currentTimeMillis()<startTime)
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
//            }
//        } else {
//            if(System.currentTimeMillis()<startTime)
//                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
//        }
//    }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    //    /**
//     * AsyncTask to get news Data
//     */
//    public class FetchNewsData extends AsyncTask<String, Void, ArrayList<News>> {
//        private RemoteViews views;
//        private int WidgetID;
//        private AppWidgetManager WidgetManager;
//
//        public FetchNewsData(RemoteViews views, int appWidgetID, AppWidgetManager appWidgetManager){
//            this.views = views;
//            this.WidgetID = appWidgetID;
//            this.WidgetManager = appWidgetManager;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected ArrayList<News> doInBackground(String... strings) {
//
//            //Just getting the news info
//            ArrayList<NewsInfo> mInfoArray = NetworkUtils.fetchNews();
//
//            NewsInfo newsInfo = mInfoArray.get(0);
//            mNewsId = newsInfo.getmNewsId();
//            mPlayerId = newsInfo.getmNewsPlayerId();
//            mSource = newsInfo.getmSource();
//            mTime = newsInfo.getmTimeShared();
//            mContent = newsInfo.getmContent();
//            mTitle = newsInfo.getmTitle();
//
//            //getting player profile info
//            ArrayList<PlayerProfile> mPlayerProf = NetworkUtils.fetchPlayerProfile(mPlayerId);
//            PlayerProfile playerProfile = mPlayerProf.get(0);
//            mPhotoUrl = playerProfile.getmPlayerImg();
//            mShortName = playerProfile.getmShortName();
//
//            mNewsArrayList.add(new News(mNewsId, mPlayerId, mShortName, mSource, mTime,
//                    mTitle, mContent, mPhotoUrl));
//
//            return mNewsArrayList;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<News> news) {
//            super.onPostExecute(news);
//
//            News wdNews = news.get(0);
//
//            views.setTextViewText(R.id.news_title, wdNews.getmTitle());
//            Log.d("TITLENEWS", wdNews.getmTitle());
//            views.setTextViewText(R.id.news_time_shared, wdNews.getmTimeShared());
//            views.setTextViewText(R.id.source, wdNews.getmSource());
//            WidgetManager.updateAppWidget(WidgetID, views);
//        }
//    }
}

