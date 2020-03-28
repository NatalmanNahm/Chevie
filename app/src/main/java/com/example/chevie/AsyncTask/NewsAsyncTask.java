package com.example.chevie.AsyncTask;

import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * This is to take care of any common task needed to be done for News asyncTask
 */
public class NewsAsyncTask {

    public void doInTheBackgroundNewsTask(ArrayList<News> newsArrayList){
        //Just getting the news info
        ArrayList<NewsInfo> infoArray = NetworkUtils.fetchNews();

        //Iterating through the arraylist of news info
        //Then create a news Arraylist
        for (int i = 0; i < 5; i++){
            NewsInfo newsInfo = infoArray.get(i);
            int playerId = newsInfo.getmNewsPlayerId();
            //getting player profile info
            ArrayList<PlayerProfile> playerProfArray = NetworkUtils.fetchPlayerProfile(playerId);
            PlayerProfile playerProfile = playerProfArray.get(0);
            String photoUrl = playerProfile.getmPlayerImg();
            String shortName = playerProfile.getmShortName();

            newsArrayList.add(new News(newsInfo, shortName, photoUrl));

        }
    }
}
