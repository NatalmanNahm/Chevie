package com.example.chevie.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chevie.Adapters.ScheduleDetailAdapter;
import com.example.chevie.AsyncTask.ScheduleAsyncTask;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.Schedule;
import com.example.chevie.Models.ScheduleDetail;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Models.TimeFrame;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Fragment detail for schedule.
 */
public class ScheduleDetailFragment extends Fragment {
    //Iniializer
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private ScheduleDetailAdapter mAdapter;
    private ArrayList<Schedule> mScheduleDetail = new ArrayList<>();
    private ArrayList<TimeFrame> mTimeFrame = new ArrayList<>();
    private static final String ARRAY_SCH = "Schedule Arraylist";
    private Parcelable mSavedLinearlayoutLayoutManager;
    private String mCurrentSeason;
    private View mRootView;


    public ScheduleDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_schedule_detail, container, false);

        mRecycler = (RecyclerView) mRootView.findViewById(R.id.sch_detail_recycler);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setHasFixedSize(true);

        mAdapter = new ScheduleDetailAdapter(mScheduleDetail, getContext());
        mRecycler.setAdapter(mAdapter);

        if (savedInstanceState != null){
            mSavedLinearlayoutLayoutManager = savedInstanceState.getParcelable(ARRAY_SCH);
            mLayoutManager.onRestoreInstanceState(mSavedLinearlayoutLayoutManager);
        }

        new FetchTimeFrame().execute();
        new FetchScheduleDetail().execute();

        return mRootView;
    }


    /**
     * asyncTask to get data needed to pull a data needed to build the Timeframe
     */
    public class FetchTimeFrame extends AsyncTask<String, Void, ArrayList<TimeFrame>> {

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
     * AsyncTask to get data needed do build am arraylist of ScheduleDetail
     */
    public class FetchScheduleDetail extends AsyncTask<String, Void, ArrayList<Schedule>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Schedule> doInBackground(String... strings) {
            new ScheduleAsyncTask().doInBackgroundScheduleTask(mCurrentSeason, mScheduleDetail);
            return mScheduleDetail;
        }

        @Override
        protected void onPostExecute(ArrayList<Schedule> schedules) {
            super.onPostExecute(schedules);
            if (schedules != null && !schedules.isEmpty()) {
                mAdapter.setmScheduleDetail(schedules);
            }
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(ARRAY_SCH, mLayoutManager.onSaveInstanceState());

    }
}
