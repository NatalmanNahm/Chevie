package com.example.chevie.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chevie.Adapters.AllTeamsAdapter;
import com.example.chevie.Models.Teams;
import com.example.chevie.R;
import com.example.chevie.TeamDetailActivity;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamAllFragment extends Fragment implements AllTeamsAdapter.TeamsOnClickHandler{

    //Initializer
    private RecyclerView mRecyclerView;
    private AllTeamsAdapter mAdapter;
    private ArrayList<Teams> mTeams = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;
    private View mRootview;
    private static final String ARRAY_TEAMS = "teams Arraylist";
    private Parcelable mSavedGridlayoutLayoutManager;


    public TeamAllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);


        // Inflate the layout for this fragment
        mRootview = inflater.inflate(R.layout.fragment_team_all, container, false);

        mRecyclerView = (RecyclerView) mRootview.findViewById(R.id.teams_recyclerView);
        mGridLayoutManager = new GridLayoutManager(getContext(), calculateNoOfColumns(mRootview.getContext()),
                GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new AllTeamsAdapter(mTeams, getContext(), this);
        mRecyclerView.setAdapter(mAdapter);

        if (savedInstanceState != null){
            mSavedGridlayoutLayoutManager = savedInstanceState.getParcelable(ARRAY_TEAMS);
            mGridLayoutManager.onRestoreInstanceState(mSavedGridlayoutLayoutManager);
        }

        new FetchAllTeams().execute();

        return mRootview;
    }

    /**
     * AsyncTask to get all teams from the Api call
     */
    public class FetchAllTeams extends AsyncTask<String, Void, ArrayList<Teams>> {

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
        Intent intent = new Intent(getContext(), TeamDetailActivity.class);
        intent.putExtra("position", position);
        intent.putParcelableArrayListExtra("TeamsArray", teams);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(ARRAY_TEAMS, mGridLayoutManager.onSaveInstanceState());
    }
}
