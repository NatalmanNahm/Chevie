package com.example.chevie.Utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.chevie.Models.CurrentTimeFrame;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Models.TeamCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
                String currentWeek =jsonObject.getString("ApiWeek");

                currentTimeFrame.add(new CurrentTimeFrame(currentSeason,currentWeek));
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
        String offensive;
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
                    offensive = jsonObject.getString("OffensiveScheme");
                    defensive = jsonObject.getString("DefensiveSchemew");
                    primaryColor = jsonObject.getString("PrimaryColor");
                    logo = jsonObject.getString("WikipediaLogoUrl");
                    byeWeek = jsonObject.getInt("ByeWeek");

                    teamCards.add(new TeamCard(logo, primaryColor, offensive, defensive, byeWeek));

                }

                else if (playerId.equals(team2Id)){
                    offensive = jsonObject.getString("OffensiveScheme");
                    defensive = jsonObject.getString("DefensiveSchemew");
                    primaryColor = jsonObject.getString("PrimaryColor");
                    logo = jsonObject.getString("WikipediaLogoUrl");
                    byeWeek = jsonObject.getInt("ByeWeek");

                    teamCards.add(new TeamCard(logo, primaryColor, offensive, defensive, byeWeek));
                }

            }

        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return teamCards;
    }
}
