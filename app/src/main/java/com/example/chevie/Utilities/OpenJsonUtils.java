package com.example.chevie.Utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.chevie.Models.CurrentTimeFrame;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Models.TeamHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class OpenJsonUtils {

    private static final String TAG = OpenJsonUtils.class.getSimpleName();

    /**
     * Create a private constructor, so no one could access it
     */
    private OpenJsonUtils(){

    }

    /**
     * Just to check for empty String and if it is empty then return early.
     * @param json
     * @return
     */
    private static String isEmptyStringJson (String json){
        if (TextUtils.isEmpty(json)){
            return null;
        }
        return null;
    }

    /**
     * Getting all the info needed to create news info object
     * @param json
     * @return arraylist of newsInfo
     */
    public static ArrayList<NewsInfo> extractNewsJson(String json){

        isEmptyStringJson(json);

        //create empty Arraylist that will hold news info data needed
        ArrayList<NewsInfo> newsInfoArraylist = new ArrayList<>();

        try{
            //Getting the rootJosn that will be decomposed to get the data needed
            JSONArray rootJson = new JSONArray(json);

            //iterate through the array to get data needed
            for (int i = 0; i < rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String source = jsonObject.getString("Source");
                String timeAgo = jsonObject.getString("TimeAgo");
                String title = jsonObject.getString("Title");
                String content = jsonObject.getString("Content");
                int playerId = jsonObject.getInt("PlayerID");
                newsInfoArraylist.add(new NewsInfo(playerId, source, timeAgo, title, content));
            }

        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }


        return newsInfoArraylist;

    }

    /**
     * Getting the info needed to get player info
     * @param json
     * @return arrayList of PlayerProfile
     */
    public static ArrayList<PlayerProfile> playerProfile (String json){

        isEmptyStringJson(json);

        //Create an empty ArrayList for the Player profile object
        ArrayList<PlayerProfile> playerArray = new ArrayList<>();

        try{
            //Getting the rootJosn that will be decomposed to get the data needed
            JSONObject playerJson = new JSONObject(json);

            String playerPic = playerJson.getString("PhotoUrl");
            String playerNane = playerJson.getString("ShortName");

            playerArray.add(new PlayerProfile(playerPic, playerNane));


        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the player Json object");
        }
        return playerArray;
    }

    /**
     * Getting the info needed to get the current season info
     * @param json
     * @return ArrayList of CurrentTimeFrame
     */
    public static ArrayList<CurrentTimeFrame> extractCurrentSeason (String json){
        isEmptyStringJson(json);

        //Createe an empty Arralist for the current time frame
        ArrayList<CurrentTimeFrame> currentTimeFrame = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i<rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String currentSeason = jsonObject.getString("ApiSeason");
                Log.d("SEASON", currentSeason);
                int currentWeek =jsonObject.getInt("Week");

                currentTimeFrame.add(new CurrentTimeFrame(currentSeason, currentWeek));
            }

        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return currentTimeFrame;
    }

    /**
     * Getting info needed to build a the two team card
     * @param json
     * @param team1Id
     * @param team2Id
     * @return
     */
    public static ArrayList<TeamCard> extractTeamCard (String json, String team1Id, String team2Id){
        isEmptyStringJson(json);

        ArrayList<TeamCard> teamCards = new ArrayList<>();
        String offensiveOne;
        String defensive;
        String primaryColor;
        String logo;
        int byeWeek;

        try {

            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i < rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String playerId = jsonObject.getString("Key");

                if (playerId.equals(team1Id)){
                    offensiveOne = jsonObject.getString("OffensiveScheme");
                    defensive = jsonObject.getString("DefensiveScheme");
                    logo = jsonObject.getString("WikipediaLogoUrl");
                    byeWeek = jsonObject.getInt("ByeWeek");

                    teamCards.add(new TeamCard(logo, offensiveOne, defensive, byeWeek));

                }

                if (playerId.equals(team2Id)){
                    offensiveOne = jsonObject.getString("OffensiveScheme");
                    defensive = jsonObject.getString("DefensiveScheme");
                    logo = jsonObject.getString("WikipediaLogoUrl");
                    byeWeek = jsonObject.getInt("ByeWeek");

                    teamCards.add(new TeamCard(logo, offensiveOne, defensive, byeWeek));
                }

            }

        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return teamCards;
    }

    /**
     * Getting all data needed to build an event
     * @param json
     * @return
     */
    public static ArrayList<EventHome> extractEventHome (String json){
        isEmptyStringJson(json);

        ArrayList<EventHome> eventHomes = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i<rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String dateTime = jsonObject.getString("DateTime");
                String date = DateTimeUtil.dateString(dateTime);
                String currentDate = DateTimeUtil.currentDate();

                if (date.equals(currentDate)){
                    String home = jsonObject.getString("HomeTeam");
                    String away = jsonObject.getString("AwayTeam");
                    String forcast = jsonObject.getString("ForecastDescription");
                    int high = jsonObject.getInt("ForecastTempHigh");
                    int low = jsonObject.getInt("ForecastTempLow");

                    JSONObject stadiumoJson = jsonObject.getJSONObject("StadiumDetails");
                    String stadium = stadiumoJson.getString("Name");

                    eventHomes.add(new EventHome(home, away, date, forcast, high, low, stadium));
                }

            }
        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return eventHomes;
    }

    /**
     * Getting needed for just one team to be displayed on the home screen.
     * @param json
     * @return
     */
    public static ArrayList<TeamHome> extractTeamHome (String json){
        isEmptyStringJson(json);

        ArrayList<TeamHome> teamHome = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i <rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);
                String teamId = jsonObject.getString("Key");
                Log.d("KEYTEAM", teamId);
                String city = jsonObject.getString("City");
                String name = jsonObject.getString("Name");
                String headCoach = jsonObject.getString("HeadCoach");
                String offensive = jsonObject.getString("OffensiveScheme");
                String defensive = jsonObject.getString("DefensiveScheme");
                String primaryColor = jsonObject.getString("PrimaryColor");
                String logo = jsonObject.getString("WikipediaLogoUrl");

                JSONObject stadiumJson = jsonObject.getJSONObject("StadiumDetails");
                String stadium = stadiumJson.getString("Name");

                teamHome.add(new TeamHome(teamId, city,name, headCoach, offensive, defensive,
                        primaryColor, logo, stadium));
            }


        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object Team Home");
        }

        return teamHome;
    }
}
