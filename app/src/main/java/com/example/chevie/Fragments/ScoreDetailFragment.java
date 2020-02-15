package com.example.chevie.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chevie.Adapters.AllScoreAdapter;
import com.example.chevie.Models.AllScore;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreDetailFragment extends Fragment {

    //Initializer
    private static final String ARG_PARAM1 = "param1";
    private ArrayList<ScoreHome> mScore = new ArrayList<>();
    private ArrayList<AllScore> mAllScore = new ArrayList<>();
    private ArrayList<TeamCard> mTeamCard = new ArrayList<>();
    private String mHomeName, mAwayName;
    private String mHomeLogo, mAwayLogo;
    private int mHomeScore, mAwayScore;
    private int mHomeQtr1, mAwayQtr1;
    private int mHomeQtr2, mAwayQtr2;
    private int mHomeQtr3, mAwayQtr3;
    private int mHomeQtr4, mAwayQtr4;
    private int mWeek;
    private String mDate;

    private RecyclerView mRecyclerView;
    private AllScoreAdapter mAllScoreAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private View mRootView;


    public ScoreDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setRetainInstance(true);

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_score_detail, container, false);

        if (savedInstanceState != null){
            mScore = savedInstanceState.getParcelableArrayList(ARG_PARAM1);
        }

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView_score);

        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAllScoreAdapter = new AllScoreAdapter(mAllScore, getContext());
        mRecyclerView.setAdapter(mAllScoreAdapter);

        new FetchAllScore().execute();

        return mRootView;
    }


    public class FetchAllScore extends AsyncTask<String, Void, ArrayList<AllScore>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<AllScore> doInBackground(String... strings) {
            for (int i = 0; i<mScore.size(); i++){
                ScoreHome scoreHome = mScore.get(i);
                mHomeName = scoreHome.getmHomeTeam();
                mAwayName = scoreHome.getmAwayTeam();

                mTeamCard = NetworkUtils.fetchTeamCard(mHomeName, mAwayName);

                TeamCard team1 = mTeamCard.get(0);
                mHomeLogo = team1.getmTeamLogo();
                mHomeQtr1 = scoreHome.getmHomeQtr1();
                mHomeQtr2 = scoreHome.getmHomeQtr2();
                mHomeQtr3 = scoreHome.getmHomeQtr3();
                mHomeQtr4 = scoreHome.getmHomeQtr4();
                mHomeScore = scoreHome.getmHomeScore();

                TeamCard team2 = mTeamCard.get(1);
                mAwayLogo = team2.getmTeamLogo();
                mAwayQtr1 = scoreHome.getmAwayQtr1();
                mAwayQtr2 = scoreHome.getmAwayQtr2();
                mAwayQtr3 = scoreHome.getmAwayQtr3();
                mAwayQtr4 = scoreHome.getmAwayQtr4();
                mAwayScore = scoreHome.getmAwayScore();

                mDate = scoreHome.getmDate();
                mWeek = scoreHome.getmWeek();

                mAllScore.add(new AllScore(mWeek, mHomeLogo, mAwayLogo, mDate, mHomeName, mAwayName,
                        mHomeScore, mHomeQtr1, mHomeQtr2, mHomeQtr3, mHomeQtr4, mAwayScore,
                        mAwayQtr1, mAwayQtr2, mAwayQtr3, mAwayQtr4));
            }

            return mAllScore;
        }

        @Override
        protected void onPostExecute(ArrayList<AllScore> allScores) {
            if (allScores != null && !allScores.isEmpty()){
                mAllScoreAdapter.setmAllScore(allScores);
            }

            super.onPostExecute(allScores);
        }
    }

    public void setmScore(ArrayList<ScoreHome> mScore) {
        this.mScore = mScore;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARG_PARAM1, mScore);
    }

}
