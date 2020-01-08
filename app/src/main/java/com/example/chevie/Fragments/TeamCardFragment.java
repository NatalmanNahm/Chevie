package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chevie.Models.TeamCard;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;
import com.example.chevie.Utilities.SvgLoaderUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass for teams card
 */
public class TeamCardFragment extends Fragment {

    //Initialize all the parameters here
    private static final String ARG_PARAM1 = "teamOneId";
    private static final String ARG_PARAM2 = "teamTwoId";
    private String mTeamoneId;
    private String mTeamTwoId;
    private ArrayList<TeamCard> mTeamCard = new ArrayList<>();

    private ImageView mTeamOneLogo;
    private ImageView mTeamTwoLogo;
    private TextView mTeamOneOff;
    private TextView mTeamTwoOff;
    private TextView mTeamOneDef;
    private TextView mTeamTwoDef;
    private TextView mTeamOneByeWeek;
    private TextView mTeamTwoByeWeek;

    //Empty Constructor
    public TeamCardFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (savedInstanceState != null){
            mTeamoneId = savedInstanceState.getString(ARG_PARAM1);
            mTeamTwoId = savedInstanceState.getString(ARG_PARAM2);
        }

        View rootView = inflater.inflate(R.layout.fragment_team_card, container, false);

        //Reference to all the elements
        mTeamOneLogo = (ImageView) rootView.findViewById(R.id.team_one_logo);
        mTeamTwoLogo = (ImageView) rootView.findViewById(R.id.team_two_logo);

        mTeamOneOff = (TextView) rootView.findViewById(R.id.offensive1);
        mTeamTwoOff = (TextView) rootView.findViewById(R.id.offensive2);

        mTeamOneDef = (TextView) rootView.findViewById(R.id.denfense1);
        mTeamTwoDef = (TextView) rootView.findViewById(R.id.defense2);

        mTeamOneByeWeek = (TextView) rootView.findViewById(R.id.bye_week1);
        mTeamTwoByeWeek = (TextView) rootView.findViewById(R.id.bye_week2);

        new FetchTeamCardData().execute();
        return rootView;
    }


    public void setmTeamoneId(String mTeamoneId) {
        this.mTeamoneId = mTeamoneId;
    }

    public void setmTeamTwoId(String mTeamTwoId) {
        this.mTeamTwoId = mTeamTwoId;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_PARAM1, mTeamoneId);
        outState.putString(ARG_PARAM2, mTeamTwoId);
    }


    public class FetchTeamCardData extends AsyncTask<String, Void, ArrayList<TeamCard>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TeamCard> doInBackground(String... strings) {
            mTeamCard = NetworkUtils.fetchTeamCard(mTeamoneId, mTeamTwoId);

            return mTeamCard;
        }

        @Override
        protected void onPostExecute(ArrayList<TeamCard> teamCards) {
            if (teamCards != null && !teamCards.isEmpty()){

                TeamCard teamCard1 = teamCards.get(0);
                TeamCard teamCard2 = teamCards.get(1);

                SvgLoaderUtil.fetchSvg(getContext(), teamCard1.getmTeamLogo(), mTeamOneLogo);
                mTeamOneOff.setText(teamCard1.getmOffensiveSch());
                mTeamOneDef.setText(teamCard1.getmDeffensiveSch());
                mTeamOneByeWeek.setText(String.valueOf(teamCard1.getmByeWeek()));

                SvgLoaderUtil.fetchSvg(getContext(), teamCard2.getmTeamLogo(), mTeamTwoLogo);
                mTeamTwoOff.setText(teamCard2.getmOffensiveSch());
                mTeamTwoDef.setText(teamCard2.getmDeffensiveSch());
                mTeamTwoByeWeek.setText(String.valueOf(teamCard2.getmByeWeek()));

            }

            super.onPostExecute(teamCards);
        }
    }
}
