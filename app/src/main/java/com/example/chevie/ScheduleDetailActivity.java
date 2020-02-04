package com.example.chevie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.chevie.Adapters.ScheduleAdapter;
import com.example.chevie.Adapters.ScheduleDetailAdapter;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.Schedule;
import com.example.chevie.Models.ScheduleDetail;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Models.TimeFrame;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;


/**
 * Activity for all schedules
 */
public class ScheduleDetailActivity extends AppCompatActivity {

    //Iniializer
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private ScheduleDetailAdapter mAdapter;
    private ArrayList<ScheduleDetail> mScheduleDetail = new ArrayList<>();
    private ArrayList<TimeFrame> mTimeFrame = new ArrayList<>();
    private ArrayList<EventHome> mEventHome = new ArrayList<>();
    private ArrayList<TeamCard> mTeamCard = new ArrayList<>();
    private String mCurrentSeason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);

        mRecycler = (RecyclerView) findViewById(R.id.sch_detail_recycler);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setHasFixedSize(true);

        mAdapter = new ScheduleDetailAdapter(mScheduleDetail, getApplicationContext());
        mRecycler.setAdapter(mAdapter);

        new FetchTimeFrame().execute();
        new FetchScheduleDetail().execute();
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
    public class FetchScheduleDetail extends AsyncTask<String, Void, ArrayList<ScheduleDetail>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<ScheduleDetail> doInBackground(String... strings) {
            mEventHome = NetworkUtils.fetchAllEventData(mCurrentSeason);

            //Going through the arraylist to get team name Then use that
            //To fetch team card for each event
            for (int i = 0; i < mEventHome.size(); i++) {
                EventHome eventHome = mEventHome.get(i);
                String mTeamOneName = eventHome.getmHomeTeam();
                String mTeamTwoName = eventHome.getmAwayTeam();
                String mStadium = eventHome.getmStadium();
                String mDate = eventHome.getmDate();
                String time = eventHome.getmTime();

                mTeamCard = NetworkUtils.fetchTeamCard(mTeamOneName, mTeamTwoName);
                //Get all data for the home Team
                TeamCard team1 = mTeamCard.get(0);
                String mTeamOneLogo = team1.getmTeamLogo();
                String mOffOne = team1.getmOneOffensiveSch();
                String mDefOne = team1.getmDeffensiveSch();
                int mByeWeekOne = team1.getmOneByeWeek();

                //Get all data for away Team
                TeamCard team2 = mTeamCard.get(1);
                String mTeamTwoLogo = team2.getmTeamLogo();
                String mOffTwo = team2.getmOneOffensiveSch();
                String mDefTwo = team2.getmDeffensiveSch();
                int mByeWeekTwo = team2.getmOneByeWeek();

                mScheduleDetail.add(new ScheduleDetail(mTeamOneName, mTeamTwoName, mDate, time,
                        mStadium, mTeamOneLogo, mOffOne, mDefOne, mByeWeekOne, mTeamTwoLogo,
                        mOffTwo, mDefTwo, mByeWeekTwo));

            }
            return mScheduleDetail;
        }

        @Override
        protected void onPostExecute(ArrayList<ScheduleDetail> schedules) {
            super.onPostExecute(schedules);
            if (schedules != null && !schedules.isEmpty()) {
                mAdapter.setmScheduleDetail(schedules);
            }
        }

    }
}
