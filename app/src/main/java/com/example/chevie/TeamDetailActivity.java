package com.example.chevie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.chevie.Adapters.TeamDetailPagerAdapter;
import com.example.chevie.Models.Teams;
import com.example.chevie.R;
import com.example.chevie.Utilities.ZoomOutPageTransformer;

import java.util.ArrayList;

/**
 * Activity that holds Viewpager with detail of each Teams
 */

public class TeamDetailActivity extends AppCompatActivity {

    //Initializer
    private int mPosition;
    private ArrayList<Teams> mTeams = new ArrayList<>();
    private TeamDetailPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private static final String POSITION = "position";
    private static final String TEAMARRAY = "TeamsArray";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        //Getiing data parse to this activity
        Intent intent = getIntent();

        if (intent != null){
            if (intent.hasExtra(POSITION)){
                mPosition = intent.getIntExtra(POSITION, 0);
                mTeams = intent.getParcelableArrayListExtra(TEAMARRAY);
            }
        }

        mAdapter = new TeamDetailPagerAdapter(getSupportFragmentManager(), mTeams);
        mViewPager = (ViewPager) findViewById(R.id.team_pager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setCurrentItem(mPosition);
    }
}
