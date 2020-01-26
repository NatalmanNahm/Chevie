package com.example.chevie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.chevie.Adapters.AllTeamsAdapter;
import com.example.chevie.Models.Teams;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * All teams Activity
 * Show all teams in the league
 */

public class AllTeamActivity extends AppCompatActivity implements AllTeamsAdapter.TeamsOnClickHandler{

    //Initializer
    RecyclerView mRecyclerView;
    AllTeamsAdapter mAdapter;
    ArrayList<Teams> mTeams = new ArrayList<>();
    GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_team);

        mRecyclerView = (RecyclerView) findViewById(R.id.teams_recyclerView);
        mGridLayoutManager = new GridLayoutManager(this, calculateNoOfColumns(this),
                GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new AllTeamsAdapter(mTeams, getApplicationContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        new FetchAllTeams().execute();

    }

    /**
     * AsyncTask to get all teams from the Api call
     */
    public class FetchAllTeams extends AsyncTask<String, Void, ArrayList<Teams>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Teams> doInBackground(String... strings) {
            mTeams = NetworkUtils.fetchTeams();
            return mTeams;
        }

        @Override
        protected void onPostExecute(ArrayList<Teams> teams) {
            super.onPostExecute(teams);
            if (teams != null ){
                mAdapter.setmTeams(teams);
            }
        }
    }

    /**
     * Helper method to calculate number of columns to be displayed
     * @param context
     * @return
     */
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 4)
            noOfColumns = 4;
        return noOfColumns;
    }

    @Override
    public void onClick(ArrayList<Teams> teams, int position) {

        //Creating a new intent to parse data from this activity to the teamDetailActivity
        Intent intent = new Intent(this, TeamDetailActivity.class);
        intent.putExtra("position", position);
        intent.putParcelableArrayListExtra("TeamsArray", teams);
        startActivity(intent);
    }
}
