package com.example.chevie.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chevie.Adapters.ScheduleAdapter;
import com.example.chevie.AsyncTask.ScheduleAsyncTask;
import com.example.chevie.MainActivity;
import com.example.chevie.Models.TimeFrame;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.Schedule;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * Fragment that link all evenet data to the home page activity
 */
public class ScheduleFragment extends Fragment {

    //Initializer
    private View mRootView;
    private RecyclerView mRecyclerView;
    private ConstraintLayout mErrorLayout;
    private ArrayList<EventHome> mEventHome = new ArrayList<>();
    private ArrayList<TimeFrame> mTimeFrame = new ArrayList<>();
    private ArrayList<Schedule> mSchedule = new ArrayList<>();
    private ArrayList<TeamCard> mTeamCard = new ArrayList<>();
    private ScheduleAdapter mEventAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mCurrentSeason;
    private String mTeamOneName, mTeamTwoName;
    private String mTeamOneLogo, mTeamTwoLogo;
    private String mDate, mStadium, mForecastDesc;
    private int mLow, mHigh;
    private String mOffOne, mOffTwo;
    private String mDefOne, mDefTwo;
    private int mByeWeekOne, mByeWeekTwo;
    private static final String OPENSCHEDULE = "OpenSchedule";



    public ScheduleFragment(){
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.event_RecyclerView);
        mErrorLayout = (ConstraintLayout) mRootView.findViewById(R.id.Schedule_error_layout);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mEventAdapter = new ScheduleAdapter(mSchedule, getContext());
        mRecyclerView.setAdapter(mEventAdapter);
        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);

        new FetchTimeFrame().execute();

        new FetchEvent().execute();

        //OnClick open the all Team Activity
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(OPENSCHEDULE, true);
                startActivity(intent);
            }
        });

        return mRootView;
    }

    /**
     * simple method to show event data on the ui
     */
    private void showEventDataView() {
        /* First, make sure the error is invisible */
        mErrorLayout.setVisibility(View.INVISIBLE);
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
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    /**
     * asyncTask to get data needed to pull a data needed to build the Timeframe
     */
    public class FetchTimeFrame extends AsyncTask<String, Void, ArrayList<TimeFrame>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TimeFrame> doInBackground(String... strings) {
            mTimeFrame = NetworkUtils.fetchCurrentTimeFrame();
            TimeFrame timeFrame = mTimeFrame.get(0);
            mCurrentSeason = timeFrame.getmCurrentSeason();
            return mTimeFrame;
        }

        @Override
        protected void onPostExecute(ArrayList<TimeFrame> timeFrames) {
            super.onPostExecute(timeFrames);
        }
    }


    /**
     * AsyncTask to fetch Schedule data
     */
    public class FetchEvent extends AsyncTask<String, Void, ArrayList<Schedule>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Schedule> doInBackground(String... strings) {
            new ScheduleAsyncTask().doInBackgroundScheduleTask(mCurrentSeason, mSchedule);
            return mSchedule;
        }

        @Override
        protected void onPostExecute(ArrayList<Schedule> schedules) {

            if (schedules != null && !schedules.isEmpty()){
                showEventDataView();
                mEventAdapter.setmSchedule(schedules);
            } else {
                showEventErrorMessage();
            }

            super.onPostExecute(schedules);
        }
    }

}
