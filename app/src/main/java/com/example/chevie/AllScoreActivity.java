package com.example.chevie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chevie.Adapters.AllScoreAdapter;
import com.example.chevie.Models.AllScore;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * Activity that shows all score
 */
public class AllScoreActivity extends AppCompatActivity {

    //Initializer
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_score);

        //Create Intent
        Intent intent = getIntent();
        //Getting the arrayList parse to it
        if (intent != null){
            if (intent.hasExtra("scoreArray")){
                mScore = intent.getParcelableArrayListExtra("scoreArray");
            }
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_score);

        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAllScoreAdapter = new AllScoreAdapter(mAllScore, this);
        mRecyclerView.setAdapter(mAllScoreAdapter);

        new FetchAllScore().execute();

    }

    public class FetchAllScore extends AsyncTask<String, Void, ArrayList<AllScore>>{

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
}
