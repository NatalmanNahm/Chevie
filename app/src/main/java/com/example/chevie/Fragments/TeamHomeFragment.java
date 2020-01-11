package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chevie.Models.TeamCard;
import com.example.chevie.Models.TeamHome;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * This Fragment contains all code to populate the UI
 * as one team card to be shown on the homepage.
 */
public class TeamHomeFragment extends Fragment {

    //Initializer
    View mRootView;
    Context mContext;
    ArrayList<TeamHome> mTeamHome = new ArrayList<>();
    TextView mTeamId, mName;
    ImageView mLogo;
    TextView mCoach, mCity;
    TextView mDefense, mOffense;
    TextView mStadium;



    public TeamHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_team_home, container, false);
        mContext = mRootView.getContext();

        //Getting reference of all view.
        mTeamId = (TextView) mRootView.findViewById(R.id.team_short);
        mName = (TextView) mRootView.findViewById(R.id.team_name);
        mCoach = (TextView) mRootView.findViewById(R.id.head_coach);
        mCity = (TextView) mRootView.findViewById(R.id.team_city);
        mDefense = (TextView) mRootView.findViewById(R.id.defense);
        mOffense = (TextView) mRootView.findViewById(R.id.offensive);
        mStadium = (TextView) mRootView.findViewById(R.id.std);
        mLogo = (ImageView) mRootView.findViewById(R.id.team_home_logo);

        new FetchTeamHome().execute();

        return mRootView;
    }

    public class FetchTeamHome extends AsyncTask<String, Void, ArrayList<TeamHome>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TeamHome> doInBackground(String... strings) {

            //Getting data for that only one random team
            mTeamHome = NetworkUtils.fetchTeamHome();
            return mTeamHome;
        }

        @Override
        protected void onPostExecute(ArrayList<TeamHome> teamHomes) {
            super.onPostExecute(teamHomes);

            //Getting every single element of the arraylist
            //bind it to a view inside the xml.
            TeamHome teamHome = teamHomes.get(0);

            mTeamId.setText(teamHome.getmTeamShort());
            mName.setText(teamHome.getmName());
            mCoach.setText(teamHome.getmHeadCoach());
            mCity.setText(teamHome.getmCity());
            mDefense.setText(teamHome.getmDefensive());
            mOffense.setText(teamHome.getmOffensive());
            mStadium.setText(teamHome.getmStadium());
            SvgLoaderUtil.fetchSvg(mContext, teamHome.getmLogo(), mLogo);

        }
    }

}
