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
import android.widget.LinearLayout;

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

    private RecyclerView mRecyclerView;
    private AllScoreAdapter mAllScoreAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private View mRootView;
    private LinearLayout mLinearErrorLayout;


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
        mLinearErrorLayout = (LinearLayout) mRootView.findViewById(R.id.empty_score_frag);

        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAllScoreAdapter = new AllScoreAdapter(mAllScore, getContext());
        mRecyclerView.setAdapter(mAllScoreAdapter);

        new FetchAllScore().execute();

        return mRootView;
    }


    /**
     * simple method to show Score data on the ui
     */
    private void showDataView() {
        /* First, make sure the error is invisible */
        mLinearErrorLayout.setVisibility(View.INVISIBLE);
        /* Then, make sure the Score data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * Simple method to show error when needed.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mLinearErrorLayout.setVisibility(View.VISIBLE);
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
                String homeName = scoreHome.getmHomeTeam();
                String awayName = scoreHome.getmAwayTeam();

                mTeamCard = NetworkUtils.fetchTeamCard(homeName, awayName);

                TeamCard team1 = mTeamCard.get(0);
                String homeLogo = team1.getmTeamLogo();

                TeamCard team2 = mTeamCard.get(1);
                String awayLogo = team2.getmTeamLogo();

                mAllScore.add(new AllScore(scoreHome, homeLogo, awayLogo));
            }

            return mAllScore;
        }

        @Override
        protected void onPostExecute(ArrayList<AllScore> allScores) {
            if (allScores != null && !allScores.isEmpty()){
                showDataView();
                mAllScoreAdapter.setmAllScore(allScores);
            } else {
                showErrorMessage();
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
