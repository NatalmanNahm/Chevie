package com.example.chevie.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;

import com.example.chevie.AsyncTask.NewsAsyncTask;
import com.example.chevie.MainActivity;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

public class UpdateService extends Service {

    //initializer
    private static final String OPENMAIN = "Open news Fragment";
    private ArrayList<News> mNewsArrayList = new ArrayList<>();
    private static final String TAG = "UpdateService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "Service started");
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.news_widget);


        //Create a bundle
        Bundle extras = new Bundle();
        extras.putInt(OPENMAIN, 0);

        //Open News onClick
        Intent appIntent = new Intent(getApplicationContext(), MainActivity.class);
        appIntent.putExtras(extras);
        appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Creating a pending intent so that onClick we can open the Activity with news.
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Open Main Activity on Click
        views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);

        ComponentName widget = new ComponentName(this, NewsWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        new FetchNewsData(views, manager, widget).execute();
        manager.updateAppWidget(widget, views);

        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * AsyncTask to get news Data
     */
    public class FetchNewsData extends AsyncTask<String, Void, ArrayList<News>> {
        private RemoteViews views;
        private AppWidgetManager WidgetManager;
        private ComponentName widget;

        public FetchNewsData(RemoteViews views, AppWidgetManager appWidgetManager, ComponentName widget){
            this.views = views;
            this.WidgetManager = appWidgetManager;
            this.widget = widget;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            //Do all needed in the background tasks
            new NewsAsyncTask().doInTheBackgroundNewsTask(mNewsArrayList);
            return mNewsArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);

            News wdNews = news.get(0);

            views.setTextViewText(R.id.news_title, wdNews.getmNewsInfo().getmTitle());
            Log.d("TITLENEWS", "string");
            views.setTextViewText(R.id.news_time_shared, wdNews.getmNewsInfo().getmTimeShared());
            views.setTextViewText(R.id.source, wdNews.getmNewsInfo().getmSource());
            WidgetManager.updateAppWidget(widget, views);
        }
    }
}
