package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chevie.Adapters.EventHomeAdapter;
import com.example.chevie.Models.CurrentTimeFrame;
import com.example.chevie.Models.EventHome;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * Fragment that link all evenet data to the home page activity
 */
public class HomeEventFragment extends Fragment {

    //Initializer
    private View mRootView;
    private RecyclerView mRecyclerView;
    private TextView mErrorTextview;
    private ArrayList<EventHome> mEventHome = new ArrayList<>();
    private ArrayList<CurrentTimeFrame> mTimeFrame = new ArrayList<>();
    private EventHomeAdapter mEventAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mCurrentSeason;
    private String mTeamOneName;
    private String mTeamTwoName;



    public HomeEventFragment(){
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_home_event, container, false);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.event_recyclerView);
        mErrorTextview = (TextView) mRootView.findViewById(R.id.event_message);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mEventAdapter = new EventHomeAdapter(mEventHome, getContext());
        mRecyclerView.setAdapter(mEventAdapter);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);

        new FetchTimeFrame().execute();

        new FetchEvent().execute();

        return mRootView;
    }

    /**
     * simple method to show event data on the ui
     */
    private void showEventDataView() {
        /* First, make sure the error is invisible */
        mErrorTextview.setVisibility(View.INVISIBLE);
        /* Then, make sure the Event data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * Simple method to show error when needed.
     */
    private void showEventErrorMessage() {
        /* First, hide the currently visible data */
        mRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorTextview.setVisibility(View.VISIBLE);
    }

    /**
     * asyncTask to get data needed to pull a data needed to build the Timeframe
     */
    public class FetchTimeFrame extends AsyncTask<String, Void, ArrayList<CurrentTimeFrame>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<CurrentTimeFrame> doInBackground(String... strings) {
            mTimeFrame = NetworkUtils.fetchTimeFrame();
            CurrentTimeFrame currentTimeFrame= mTimeFrame.get(0);
            mCurrentSeason = currentTimeFrame.getmCurrentSeason();
            return mTimeFrame;
        }

        @Override
        protected void onPostExecute(ArrayList<CurrentTimeFrame> currentTimeFrames) {
            super.onPostExecute(currentTimeFrames);
        }
    }

    public class FetchEvent extends AsyncTask<String, Void, ArrayList<EventHome>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<EventHome> doInBackground(String... strings) {
            mEventHome = NetworkUtils.fetchEventData(mCurrentSeason);

            for (int i =0; i < mEventHome.size(); i++){
                EventHome eventHome = mEventHome.get(i);
                mTeamOneName = eventHome.getmHomeTeam();
                mTeamTwoName = eventHome.getmAwayTeam();

                //Crreate a new transaction to the TeamCard Fragment
                TeamCardFragment teamCardFragment = new TeamCardFragment();
                teamCardFragment.setmTeamoneId(mTeamOneName);
                teamCardFragment.setmTeamTwoId(mTeamTwoName);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.team_card, teamCardFragment)
                        .commit();
            }
            return mEventHome;
        }

        @Override
        protected void onPostExecute(ArrayList<EventHome> eventHomes) {

            if (eventHomes != null && !eventHomes.isEmpty()){
                showEventDataView();
                mEventAdapter.setmEventHome(eventHomes);
            } else {
                showEventErrorMessage();
            }

            super.onPostExecute(eventHomes);
        }
    }

}
