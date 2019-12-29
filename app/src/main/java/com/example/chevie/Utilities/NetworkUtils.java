package com.example.chevie.Utilities;

import android.net.Uri;
import android.util.Log;

import com.example.chevie.BuildConfig;
import com.example.chevie.Models.News;
import com.example.chevie.Models.PlayerProfile;

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
    private static String NFL = "nfl";
    private static String SCORES = "scores";
    private static String JSON = "json";
    private static String NEWS = "News";
    private static String API_KEY = "Key";
    private static String PLAYER = "Player";

    private static String NFL_KEY = BuildConfig.nflSportDataIoApiKey;

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

        URL url = null;

        try {
            url = new URL(uriBuilder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

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

        URL url = null;

        try {
            url = new URL(uriBuilder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

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

        URL url = null;

        try {
            url = new URL(uriBuilder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
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
     * Query sport data and then return news data
     * @param playerId
     * @return
     */
    public static ArrayList<News> fetchNews(int playerId){
        //Create url objects

        URL urlNews = build_news_url(NFL_KEY);
        URL urlPlayer = build_player_info_url(playerId, NFL_KEY);

        String jsonResponsePlayer = null;
        String jsonResponseNews = null;

        try {
            jsonResponsePlayer = getResponseFromHttpUrl(urlPlayer);
            jsonResponseNews = getResponseFromHttpUrl(urlNews);

        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request", e);
        }

        ArrayList<News> news = OpenJsonUtils.extractNewsJson(jsonResponseNews, jsonResponsePlayer);

        return news;

    }

}
