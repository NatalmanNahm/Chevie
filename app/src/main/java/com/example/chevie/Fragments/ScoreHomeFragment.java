package com.example.chevie.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chevie.MainActivity;
import com.example.chevie.Models.TimeFrame;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * This fragment is to display a match score on the home page
 */
public class ScoreHomeFragment extends Fragment {

    //Initializer
    View mRootView;
    Context mContext;
    ArrayList<ScoreHome> mScore = new ArrayList<>();
    ArrayList<TeamCard> mTeamCard = new ArrayList<>();
    ArrayList<TimeFrame> mCurrent = new ArrayList<>();
    ArrayList<TimeFrame> mPrevious = new ArrayList<>();
    LinearLayout mScoreContainer, mEmptyScoreContainer;
    TextView mHomeName, mAwayName;
    ImageView mHomeLogo, mAwayLogo;
    TextView mHomeScore, mAwayScore;
    TextView mHomeQtr1, mAwayQtr1;
    TextView mHomeQtr2, mAwayQtr2;
    TextView mHomeQtr3, mAwayQtr3;
    TextView mHomeQtr4, mAwayQtr4;
    TextView mDate, mWeek;
    String mCurrentSeason, mPreviousSeason;
    String mHomeKey, mAwayKey;
    private static final String SCOREARRAY = "scoreArray";

    public ScoreHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_score_home, container, false);
        mContext = mRootView.getContext();

        //Reference to all views
        mHomeName = (TextView) mRootView.findViewById(R.id.home_name);
        mHomeLogo = (ImageView) mRootView.findViewById(R.id.home_logo);
        mHomeScore = (TextView) mRootView.findViewById(R.id.home_score);
        mHomeQtr1 = (TextView) mRootView.findViewById(R.id.home_qtr_1);
        mHomeQtr2 = (TextView) mRootView.findViewById(R.id.home_qtr2);
        mHomeQtr3 = (TextView) mRootView.findViewById(R.id.home_qtr3);
        mHomeQtr4 = (TextView) mRootView.findViewById(R.id.home_qtr4);
        mAwayName = (TextView) mRootView.findViewById(R.id.away_name);
        mAwayLogo = (ImageView) mRootView.findViewById(R.id.away_logo);
        mAwayScore = (TextView) mRootView.findViewById(R.id.away_score);
        mAwayQtr1 = (TextView) mRootView.findViewById(R.id.away_qtr_1);
        mAwayQtr2 = (TextView) mRootView.findViewById(R.id.away_qtr_2);
        mAwayQtr3 = (TextView) mRootView.findViewById(R.id.away_qtr_3);
        mAwayQtr4 = (TextView) mRootView.findViewById(R.id.away_qtr_4);
        mDate = (TextView) mRootView.findViewById(R.id.date);
        mWeek = (TextView) mRootView.findViewById(R.id.week);
        mScoreContainer = (LinearLayout) mRootView.findViewById(R.id.score_container);
        mEmptyScoreContainer = (LinearLayout) mRootView.findViewById(R.id.empty_score_frag);

        new FetchCurrentSeason().execute();
        new FetchScore().execute();
        new FetchTeamLogo().execute();

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an intent and parse data to the allScoreActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putParcelableArrayListExtra(SCOREARRAY, mScore);
                startActivity(intent);

            }
        });

        return mRootView;
    }

    /**
     * This asyncTask class is to get the current time frame then get our current season.
     */
    public class FetchCurrentSeason extends AsyncTask<String, Void, ArrayList<TimeFrame>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TimeFrame> doInBackground(String... strings) {
            mCurrent = NetworkUtils.fetchCurrentTimeFrame();
            TimeFrame timeFrame = mCurrent.get(0);
            mCurrentSeason = timeFrame.getmCurrentSeason();

            return mCurrent;
        }

        @Override
        protected void onPostExecute(ArrayList<TimeFrame> timeFrames) {
            super.onPostExecute(timeFrames);
        }
    }

    /**
     * This asynsTask class is to get the most recent match score data
     * and display it on the home page
     */
    public class FetchScore extends AsyncTask<String, Void, ArrayList<ScoreHome>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<ScoreHome> doInBackground(String... strings) {
            mScore = NetworkUtils.fetchScoreHome(mCurrentSeason);

            //if the Current season hasn't started yet
            //then use Data from previous season
            if (mScore.size() == 0){
                mPrevious = NetworkUtils.fetchPreviousTimeFrame();
                TimeFrame timeFrame = mPrevious.get(0);
                mPreviousSeason = timeFrame.getmCurrentSeason();
                mScore = NetworkUtils.fetchScoreHome(mPreviousSeason);

                if (mScore.size() != 0){
                    ScoreHome randomScore = mScore.get(mScore.size() - 1);
                    mHomeKey = randomScore.getmHomeTeam();
                    mAwayKey = randomScore.getmAwayTeam();
                }

            }else {
                ScoreHome randomScore = mScore.get(mScore.size() - 1);
                mHomeKey = randomScore.getmHomeTeam();
                mAwayKey = randomScore.getmAwayTeam();
            }
            return mScore;
        }

        @Override
        protected void onPostExecute(ArrayList<ScoreHome> scoreHomes) {
            super.onPostExecute(scoreHomes);

            if (scoreHomes != null && !scoreHomes.isEmpty()){
                //Pull the most recent score
                ScoreHome randomScore = scoreHomes.get(scoreHomes.size() - 1);

                //Then bind views to the most recent data pulled
                mHomeName.setText(mHomeKey);
                mHomeScore.setText(String.valueOf(randomScore.getmHomeScore()));
                mHomeQtr1.setText(String.valueOf(randomScore.getmHomeQtr1()));
                mHomeQtr2.setText(String.valueOf(randomScore.getmHomeQtr2()));
                mHomeQtr3.setText(String.valueOf(randomScore.getmHomeQtr3()));
                mHomeQtr4.setText(String.valueOf(randomScore.getmHomeQtr4()));

                mAwayName.setText(mAwayKey);
                mAwayScore.setText(String.valueOf(randomScore.getmAwayScore()));
                mAwayQtr1.setText(String.valueOf(randomScore.getmAwayQtr1()));
                mAwayQtr2.setText(String.valueOf(randomScore.getmAwayQtr2()));
                mAwayQtr3.setText(String.valueOf(randomScore.getmAwayQtr3()));
                mAwayQtr4.setText(String.valueOf(randomScore.getmAwayQtr4()));

                mDate.setText(randomScore.getmDate());
                mWeek.setText(String.valueOf(randomScore.getmWeek()));
                
            } else {//Show this if there is no data to show
                mScoreContainer.setVisibility(View.GONE);
                mEmptyScoreContainer.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * This asyncTask is to get only image logo of each team
     */
    public class FetchTeamLogo extends AsyncTask<String, Void, ArrayList<TeamCard>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TeamCard> doInBackground(String... strings) {
            mTeamCard = NetworkUtils.fetchTeamCard(mHomeKey, mAwayKey);
            return mTeamCard;
        }

        @Override
        protected void onPostExecute(ArrayList<TeamCard> teamCards) {
            super.onPostExecute(teamCards);

            if (teamCards != null && !teamCards.isEmpty()){
                //Get the data need to display Team logo
                TeamCard team1 = teamCards.get(0);
                SvgLoaderUtil.fetchSvg(getContext(), team1.getmTeamLogo(), mHomeLogo);
                TeamCard team2 = teamCards.get(1);
                SvgLoaderUtil.fetchSvg(getContext(),team2.getmTeamLogo(), mAwayLogo);
            }
        }
    }

}
