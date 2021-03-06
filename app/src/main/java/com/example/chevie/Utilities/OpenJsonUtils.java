package com.example.chevie.Utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.chevie.Models.TimeFrame;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Models.TeamHome;
import com.example.chevie.Models.Teams;

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

                int newsId = jsonObject.getInt("NewsID");
                String source = jsonObject.getString("Source");
                String timeAgo = jsonObject.getString("TimeAgo");
                String title = jsonObject.getString("Title");
                String content = jsonObject.getString("Content");
                int playerId = jsonObject.optInt("PlayerID",6);
                newsInfoArraylist.add(new NewsInfo(newsId, playerId, source, timeAgo, title, content));
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
            String playerName = playerJson.getString("ShortName");
            int age = playerJson.optInt("Age", 0);
            String position = playerJson.getString("Position");

            playerArray.add(new PlayerProfile(playerPic, playerName, age, position));


        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the player Json object");
        }
        return playerArray;
    }

    /**
     * Getting the info needed to get the current season info
     * @param json
     * @return ArrayList of TimeFrame
     */
    public static ArrayList<TimeFrame> extractSeason(String json){
        isEmptyStringJson(json);

        //Created an empty Arralist for the current time frame
        ArrayList<TimeFrame> timeFrame = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i<rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String currentSeason = jsonObject.getString("ApiSeason");
                Log.d("SEASON", currentSeason);
                int currentWeek =jsonObject.optInt("Week", 0);

                timeFrame.add(new TimeFrame(currentSeason, currentWeek));
            }

        } catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return timeFrame;
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
        String defensiveOne;
        String logoOne;
        int byeWeekOne;


        try {

            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i < rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String playerId = jsonObject.getString("Key");

                if (playerId.equals(team1Id)){
                    offensiveOne = jsonObject.getString("OffensiveScheme");
                    defensiveOne = jsonObject.getString("DefensiveScheme");
                    logoOne = jsonObject.getString("WikipediaLogoUrl");
                    byeWeekOne = jsonObject.getInt("ByeWeek");

                    teamCards.add(new TeamCard(logoOne, offensiveOne, defensiveOne, byeWeekOne));

                }

            }

            for (int e = 0; e <rootJson.length(); e++){
                JSONObject jsonObject = rootJson.getJSONObject(e);

                String id = jsonObject.getString("Key");

                if (id.equals(team2Id)){
                    offensiveOne = jsonObject.getString("OffensiveScheme");
                    defensiveOne = jsonObject.getString("DefensiveScheme");
                    logoOne = jsonObject.getString("WikipediaLogoUrl");
                    byeWeekOne = jsonObject.getInt("ByeWeek");

                    teamCards.add(new TeamCard(logoOne, offensiveOne, defensiveOne, byeWeekOne));
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
                String time = DateTimeUtil.mathTime(dateTime);

                if (date.equals(currentDate)){
                    String home = jsonObject.getString("HomeTeam");
                    String away = jsonObject.getString("AwayTeam");
                    String forcast = jsonObject.getString("ForecastDescription");
                    int high = jsonObject.getInt("ForecastTempHigh");
                    int low = jsonObject.getInt("ForecastTempLow");

                    JSONObject stadiumoJson = jsonObject.getJSONObject("StadiumDetails");
                    String stadium = stadiumoJson.getString("Name");

                    eventHomes.add(new EventHome(home, away, date, forcast, high, low, stadium, time));
                }

            }
        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return eventHomes;
    }

    /**
     * Getting all data needed to build an event
     * @param json
     * @return
     */
    public static ArrayList<EventHome> extractAllEventHome (String json){
        isEmptyStringJson(json);

        ArrayList<EventHome> eventHomes = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i<rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);

                String dateTime = jsonObject.getString("DateTime");
                String date = DateTimeUtil.dateString(dateTime);
                String currentDate = DateTimeUtil.currentDate();
                String time = DateTimeUtil.mathTime(dateTime);
                String home = jsonObject.getString("HomeTeam");
                String away = jsonObject.getString("AwayTeam");
                String forcast = jsonObject.getString("ForecastDescription");
                int high = jsonObject.getInt("ForecastTempHigh");
                int low = jsonObject.getInt("ForecastTempLow");

                JSONObject stadiumoJson = jsonObject.getJSONObject("StadiumDetails");
                String stadium = stadiumoJson.getString("Name");

                eventHomes.add(new EventHome(home, away, date, forcast, high, low, stadium, time));

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

    /**
     * Getting data needed from the json to fill out our arraylist of ScoreHome
     * with score details
     * @param json
     * @return
     */
    public static ArrayList<ScoreHome> extractScoreHome (String json){
        isEmptyStringJson(json);

        ArrayList<ScoreHome> score = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int e = 0; e<rootJson.length(); e++){
                JSONObject jsonObject = rootJson.getJSONObject(e);

                boolean isOver = jsonObject.getBoolean("IsOver");
                if (isOver){
                    int week = jsonObject.getInt("Week");
                    String dateTime = jsonObject.getString("Date");
                    String date = DateTimeUtil.dateString(dateTime);
                    String homeTeam = jsonObject.getString("HomeTeam");
                    String awayTeam = jsonObject.getString("AwayTeam");
                    int homeScore = jsonObject.getInt("HomeScore");
                    int homeQtr1 = jsonObject.getInt("HomeScoreQuarter1");
                    int homeQtr2 = jsonObject.getInt("HomeScoreQuarter2");
                    int homeQtr3 = jsonObject.getInt("HomeScoreQuarter3");
                    int homeQtr4 = jsonObject.getInt("HomeScoreQuarter4");
                    int awayScore = jsonObject.getInt("AwayScore");
                    int awayQtr1 = jsonObject.getInt("AwayScoreQuarter1");
                    int awayQtr2 = jsonObject.getInt("AwayScoreQuarter2");
                    int awayQtr3 = jsonObject.getInt("AwayScoreQuarter3");
                    int awayQtr4 = jsonObject.getInt("AwayScoreQuarter4");

                    score.add(new ScoreHome(week, date, homeTeam, awayTeam, homeScore,
                            homeQtr1, homeQtr2, homeQtr3, homeQtr4, awayScore, awayQtr1, awayQtr2,
                            awayQtr3, awayQtr4));
                }
            }

        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return score;
    }

    /**
     * Getting Teams data needed from the json we got from the API Calls
     * @param json
     * @return
     */
    public static ArrayList<Teams> extractTeams (String json){
        isEmptyStringJson(json);
        ArrayList<Teams> teams = new ArrayList<>();

        try {
            JSONArray rootJson = new JSONArray(json);

            for (int i = 0; i<rootJson.length(); i++){
                JSONObject jsonObject = rootJson.getJSONObject(i);
                String key = jsonObject.getString("Key");
                String offensive = jsonObject.getString("OffensiveScheme");
                String defensive = jsonObject.getString("DefensiveScheme");
                String logo = jsonObject.getString("WikipediaLogoUrl");
                String primaryColor = jsonObject.getString("PrimaryColor");
                String city = jsonObject.getString("City");
                String name = jsonObject.getString("Name");
                String conference = jsonObject.getString("Conference");
                String division = jsonObject.getString("Division");
                String fullName = jsonObject.getString("FullName");
                int byeWeek = jsonObject.getInt("ByeWeek");
                String headCoach = jsonObject.getString("HeadCoach");
                String offCoordCoach = jsonObject.getString("OffensiveCoordinator");
                String defCoordCoach = jsonObject.getString("DefensiveCoordinator");
                String speTeamCach = jsonObject.getString("SpecialTeamsCoach");

                JSONObject stadiumJson = jsonObject.getJSONObject("StadiumDetails");
                String stadiumName = stadiumJson.getString("Name");
                String stadiumCity = stadiumJson.getString("City");
                String stadiumState = stadiumJson.getString("State");
                int stadiumCapacity = stadiumJson.getInt("Capacity");
                String stadiumSurface = stadiumJson.getString("PlayingSurface");
                int geolat = stadiumJson.getInt("GeoLat");
                int geoLong = stadiumJson.getInt("GeoLong");

                teams.add(new Teams(name, logo, primaryColor, fullName, city, byeWeek, conference,
                        division, defensive, offensive, key, headCoach, offCoordCoach,
                        defCoordCoach, speTeamCach, stadiumName, stadiumCity, stadiumState,
                        stadiumCapacity, stadiumSurface, geolat, geoLong));

            }


        }catch (JSONException e) {
            //If there is a problem parsing the Json object print this message
            Log.e(TAG, "Error parsing the Json object");
        }

        return teams;
    }
}
