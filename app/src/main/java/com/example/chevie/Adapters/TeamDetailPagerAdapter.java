package com.example.chevie.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.chevie.Fragments.TeamDetailFragment;
import com.example.chevie.Models.Teams;

import java.util.ArrayList;

/**
 * Pager Adapter for teams
 * slide between team
 */
public class TeamDetailPagerAdapter extends FragmentStatePagerAdapter {

    //Initializer
    ArrayList<Teams> mTeam = new ArrayList<>();


    public TeamDetailPagerAdapter(@NonNull FragmentManager fm, ArrayList<Teams> teams) {
        super(fm);
        this.mTeam = teams;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        TeamDetailFragment teamDetailFragment = new TeamDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelableArrayList("TeamsArray", mTeam);
        teamDetailFragment.setArguments(bundle);
        return teamDetailFragment;
    }

    @Override
    public int getCount() {
        return mTeam.size();
    }
}
