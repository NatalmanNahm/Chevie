package com.example.chevie.Utilities;

import android.net.Uri;
import android.util.Log;

import com.example.chevie.BuildConfig;
import com.example.chevie.Models.CurrentTimeFrame;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Models.TeamHome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //Base URL to access the sportsDataIo database
    private static final String SPORT_DATA_IO_URL = "https://api.sportsdata.io/v3";

    //Parameter to be used to query data from the sportsDataIo database
    //Will be append onto the base URL
    private static final String NFL = "nfl";
    private static final String SCORES = "scores";
    private static final String JSON = "json";
    private static final String NEWS = "News";
    private static final String API_KEY = "Key";
    private static final String PLAYER = "Player";
    private static final String TIMEFRAME = "Timeframes";
    private static final String CURRENT = "current";
    private static final String TEAMS = "Teams";
    private static final String SCHEDULES = "Schedules";
    private static final String SCORE_BY_SEASON = "Scores";

    //all Api calls key
    private static String NFL_KEY = BuildConfig.nfl2SportDataIoApiKey;

    /**
     * Helper method to simplify the need of trying to build the Url
     * @param uri
     */
    public static URL tryBuildUrl (Uri uri){
        URL url = null;

        try {
            url = new URL(uri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;

    }

    /**
     * This is a build Url just to get to the nfl version of the Url
     * @return a return url that will be used later to build the news Url
     */
    private static URL buildUrl_nfl(){

        //Build the Url with the query parameter
        Uri uriBuilder = Uri.parse(SPORT_DATA_IO_URL).buildUpon()
                .appendPath(NFL)
                .appendPath(SCORES)
                .appendPath(JSON)
                .build();

        URL url = tryBuildUrl(uriBuilder);

        return url;
    }

    /**
     * This is to build the news Url to fetch json data from the sport Api
     * @return The return result is a URL for new
     */
    public static URL build_news_url(String key) {
        Uri uriBuilder = Uri.parse(buildUrl_nfl().toString()).buildUpon()
                .appendPath(NEWS)
                .appendQueryParameter(API_KEY, key)
                .build();

        URL url = tryBuildUrl(uriBuilder);

        return url;
    }

    /**
     * This is to build the Timeframe Url to fetch json data from sport API
     * @param key
     * @return url to be used to fetch data from database
     */
    public static URL build_timeframe_url (String key){
        Uri uriBuilder = Uri.parse(buildUrl_nfl().toString()).buildUpon()
                .appendPath(TIMEFRAME)
                .appendPath(CURRENT)
                .appendQueryParameter(API_KEY, key)
                .build();

        URL url = tryBuildUrl(uriBuilder);

        return url;
    }


    /**
     * build the Url to query the sport data to get a player detail
     * @param playerId
     * @param key
     * @return url to be used to fetch data from database
     */
    public static URL build_player_info_url(int playerId, String key){
        Uri uriBuilder = Uri.parse(buildUrl_nfl().toString()).buildUpon()
                .appendPath(PLAYER)
                .appendPath(String.valueOf(playerId))
                .appendQueryParameter(API_KEY, key)
                .build();

        URL url = tryBuildUrl(uriBuilder);

        return url;
    }

    /**
     * BUild the url to query the database and get all score by the current season
     * @param season
     * @param key
     * @return Url to be used to fetch data from the database
     */
    public static URL build_score_url(String season, String key){
        Uri uriBuilder = Uri.parse(buildUrl_nfl().toString()).buildUpon()
                .appendPath(SCORE_BY_SEASON)
                .appendPath(season)
                .appendQueryParameter(API_KEY, key)
                .build();

        URL url = tryBuildUrl(uriBuilder);

        return url;
    }

    /**
     * build the Url to query the sport data to get a team detail
     * @param key
     * @return
     */
    public static URL build_team_card_url (String key){
        Uri uriBuilder = Uri.parse(buildUrl_nfl().toString()).buildUpon()
                .appendPath(TEAMS)
                .appendQueryParameter(API_KEY, key)
                .build();
        URL url = tryBuildUrl(uriBuilder);
        return  url;
    }

    /**
     * build the url to query the sport data to get an even info
     * @param key
     * @param season
     * @return
     */
    public static URL build_team_event_home_url (String key, String season){
        Uri uriBuilder = Uri.parse(buildUrl_nfl().toString()).buildUpon()
                .appendPath(SCHEDULES)
                .appendPath(season)
                .appendQueryParameter(API_KEY, key)
                .build();

        URL url = tryBuildUrl(uriBuilder);
        return  url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {

        String jsonResponse = "";

        //if the url is null the return early
        if (url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //Trying to get connection the server and if the status is 200
        //That means the connection was a success and then we can retrieve data needed
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.setConnectTimeout(15000 /*milliseconds*/);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "MakeHTTPRequest: Problem retrieving data from JSON result ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream (InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Query sport data and then return news info data
     * @return
     */
    public static ArrayList<NewsInfo> fetchNews(){
        //Create url objects

        URL urlNews = build_news_url(NFL_KEY);

        String jsonResponseNews = null;

        try {
            jsonResponseNews = getResponseFromHttpUrl(urlNews);

        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        ArrayList<NewsInfo> newsInfos = OpenJsonUtils.extractNewsJson(jsonResponseNews);

        return newsInfos;

    }

    /**
     * Query data to get player Profile info
     * @return a player profile data
     */
    public static ArrayList<PlayerProfile> fetchPlayerProfile (int playerId){
        URL playerUrl = build_player_info_url(playerId, NFL_KEY);

        String jsonReponse = null;

        try {
            jsonReponse = getResponseFromHttpUrl(playerUrl);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        ArrayList<PlayerProfile> playerProfile = OpenJsonUtils.playerProfile(jsonReponse);

        return playerProfile;
    }

    /**
     * Query data to get Team card info
     * @param team1
     * @param team2
     * @return a team Card data
     */
    public static ArrayList<TeamCard> fetchTeamCard (String team1, String team2){
        URL url = build_team_card_url(NFL_KEY);

        String jsonReponse = null;

        try {
            jsonReponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        ArrayList<TeamCard> teamCard = OpenJsonUtils.extractTeamCard(jsonReponse, team1, team2);

        return teamCard;
    }

    /**
     * Query data to get the current time Frame
     * @return ArrayList of CurrentTimeFrame
     */
    public static ArrayList<CurrentTimeFrame> fetchTimeFrame (){
        URL url = build_timeframe_url(NFL_KEY);

        String jsonResponse = null;

        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        Log.d("URL", jsonResponse);

        ArrayList<CurrentTimeFrame> currentTimeFrame = OpenJsonUtils.extractCurrentSeason(jsonResponse);

        return currentTimeFrame;

    }

    /**
     * Query data to get event data
     * @param season
     * @return ArrayList of eventHome data
     */
    public static  ArrayList<EventHome> fetchEventData(String season){
        URL url = build_team_event_home_url(NFL_KEY, season);

        String jsonResponse = null;

        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        ArrayList<EventHome> eventHome = OpenJsonUtils.extractEventHome(jsonResponse);

        return eventHome;

    }

    /**
     * Query Api database to get team home data
     * @return
     */
    public static ArrayList<TeamHome> fetchTeamHome(){
        URL url = build_team_card_url(NFL_KEY);

        String jsonResponse = null;

        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        ArrayList<TeamHome> teamHomes = OpenJsonUtils.extractTeamHome(jsonResponse);

        return teamHomes;
    }

    /**
     * Query data to get all score for a season
     * @param season
     * @return
     */
    public static ArrayList<ScoreHome> fetchScoreHome(String season){
        URL url = build_score_url(season, NFL_KEY);

        String jsonResponse = null;

        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }
        ArrayList<ScoreHome> score = OpenJsonUtils.extractScoreHome(jsonResponse);

        return score;
    }


}
