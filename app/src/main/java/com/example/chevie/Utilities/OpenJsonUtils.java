package com.example.chevie.Utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;

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

                //Getting the rootJosn that will be decomposed to get the data needed
                JSONObject pJson = new JSONObject(playerJson);

                photoUrl = pJson.getString("PhotoUrl");
                shortName = pJson.getString("ShortName");

                newsArraylist.add(new News(playerId, shortName, source, timeAgo, title, content, photoUrl));

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
    public static ArrayList<PlayerProfile> playerId (String json){

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
}
