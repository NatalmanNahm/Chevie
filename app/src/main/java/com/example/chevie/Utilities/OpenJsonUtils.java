package com.example.chevie.Utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.chevie.Models.News;

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

    public static ArrayList<News> extractNewsJson(String json){

        //If the string Json is empty the return early
        if (TextUtils.isEmpty(json)){
            return null;
        }

        //create empty Arraylist that will hold news data needed
        ArrayList<News> newsArraylist = new ArrayList<>();

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

            }

        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the news Json object");
        }

        return newsArraylist;

    }

    public static String PlayerPic (String json, int playerId){

        //If the string Json is empty the return early
        if (TextUtils.isEmpty(json)){
            return null;
        }

        //Create an empty String for the Player Picture String Url
        String playerPic = "";

        try{
            //Getting the rootJosn that will be decomposed to get the data needed
            JSONArray rootJson = new JSONArray(json);

            //iterate through the array to get data needed
            for (int i = 0; i < rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String picUrl = jsonObject.getString("PhotoUrl");
                int id = jsonObject.getInt("PlayerID");

                if (playerId == id){
                    playerPic = picUrl;
                }
            }

        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the news Json object");
        }

        return playerPic;
    }
}
