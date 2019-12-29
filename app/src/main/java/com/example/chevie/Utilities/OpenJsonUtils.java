package com.example.chevie.Utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.chevie.Models.News;
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
     * Getting all the info needed to create news object
     * @param newsJson
     * @param playerJson
     * @return
     */
    public static ArrayList<News> extractNewsJson(String newsJson, String playerJson){

        isEmptyStringJson(newsJson);
        isEmptyStringJson(playerJson);

        //create empty Arraylist that will hold news data needed
        ArrayList<News> newsArraylist = new ArrayList<>();
        int playerId = 0;
        String source = "";
        String timeAgo = "";
        String title = "";
        String content = "";
        String photoUrl = "";
        String shortName = "";

        try{
            //Getting the rootJosn that will be decomposed to get the data needed
            JSONArray rootJson = new JSONArray(newsJson);

            //iterate through the array to get data needed
            for (int i = 0; i < rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                source = jsonObject.getString("Source");
                timeAgo = jsonObject.getString("TimeAgo");
                title = jsonObject.getString("Title");
                content = jsonObject.getString("Content");
                playerId = jsonObject.getInt("PlayerID");

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


        return newsArraylist;

    }

//    /**
//     * Getting the info needed to create a player image
//     * @param json
//     * @return return a string data to build image
//     */
//    public static ArrayList<PlayerProfile> playerProf (String json){
//
//        //If the string Json is empty the return early
//        if (TextUtils.isEmpty(json)){
//            return null;
//        }
//
//        //Create an empty ArrayList for the Player profile object
//        ArrayList<PlayerProfile> playerArray = new ArrayList<>();
//
//        try{
//            //Getting the rootJosn that will be decomposed to get the data needed
//            JSONObject playerJson = new JSONObject(json);
//
//            String playerPic = playerJson.getString("PhotoUrl");
//            String playerNane = playerJson.getString("ShortName");
//
//            playerArray.add(new PlayerProfile(playerPic, playerNane));
//
//
//        } catch (JSONException e) {
//            //If there is a problem parsing the Json object print this message
//            Log.e(TAG, "Error parsing the player Json object");
//        }
//
//        return playerArray;
//    }
}
