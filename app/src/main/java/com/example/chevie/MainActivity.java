package com.example.chevie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.chevie.Adapters.NewsDetailPagerAdapter;
import com.example.chevie.Fragments.ScheduleDetailFragment;
import com.example.chevie.Fragments.ScoreDetailFragment;
import com.example.chevie.Fragments.TeamAllFragment;
import com.example.chevie.Models.News;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Utilities.ZoomOutPageTransformer;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //initializer
    private DrawerLayout mDrawer;
    private FrameLayout mFrameLayout;
    private ViewPager mViewPager;

    //for news
    private int mNewsPostion;
    private ArrayList<News> mNews = new ArrayList<>();
    private NewsDetailPagerAdapter mNewsDetailPagerAdapter;


    //For Schedule
    private ScheduleDetailFragment mSchDetailFragment;

    //For Teams
    private TeamAllFragment mTeamAllFragmant;

    //For Scores
    private ScoreDetailFragment mScoreDetailFragment;
    ArrayList<ScoreHome> mScore = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set default toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_draw);
        mFrameLayout = findViewById(R.id.fragment_all);
        mViewPager = findViewById(R.id.viewPager_main);

        navigationView.setNavigationItemSelectedListener(this);

        //create an intent
        Intent intent = getIntent();

        if (intent != null){
            //Getting the values parse from the newsFragment
            if (intent.hasExtra("positionNews")){
                mNewsPostion = intent.getIntExtra("positionNews", 0);
                mNews = intent.getParcelableArrayListExtra("newsArray");
                navigationView.setCheckedItem(R.id.news);
                setNewsViewPager();
            }

            //Open Schedule detail Fragment
            if (intent.hasExtra("OpenSchedule")){
                navigationView.setCheckedItem(R.id.schedule);
                openScheduleFragment();
            }

            //Open All Team Fragment
            if (intent.hasExtra("OpenTeams")){
                navigationView.setCheckedItem(R.id.teams);
                openTeamFragment();
            }

            if (intent.hasExtra("scoreArray")){
                mScore = intent.getParcelableArrayListExtra("scoreArray");
                navigationView.setCheckedItem(R.id.scores);
                openScoreFragment();
            }
        }

        //this is for menu bar to unfold and fold back in on pressed
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    /**
     * If the drawer/menu bar is open on back press close the drawer
     * else proceed as usual
     */
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * helper method to get the mainActivity inflate the viewPager
     * with news detail pager.
     */
    private void setNewsViewPager(){
        //get rid of the frameLayout View
        mFrameLayout.setVisibility(View.GONE);
        //Show ViewPager
        mViewPager.setVisibility(View.VISIBLE);

        //Open the NewsPager with its Fragment
        mNewsDetailPagerAdapter = new NewsDetailPagerAdapter(getSupportFragmentManager(), mNews);
        mViewPager.setAdapter(mNewsDetailPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setCurrentItem(mNewsPostion);
    }

    /**
     * Helper Method to open Schedule Detail Fragment
     */
    private void openScheduleFragment(){
        //get rid of the ViewPager
        mViewPager.setVisibility(View.GONE);

        //Show Fragment
        mFrameLayout.setVisibility(View.VISIBLE);

        //Open fragment
        mSchDetailFragment = new ScheduleDetailFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_all, mSchDetailFragment)
                .commit();
    }


    /**
     * Helper method to open all Team Fragment
     */
    private void openTeamFragment(){
        //get rid of the ViewPager
        mViewPager.setVisibility(View.GONE);

        //Show Fragment
        mFrameLayout.setVisibility(View.VISIBLE);

        //Open Fragment
        mTeamAllFragmant = new TeamAllFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_all, mTeamAllFragmant)
                .commit();
    }

    /**
     * helper method to open all Scores
     */
    private void openScoreFragment(){
        //get rid of the ViewPager
        mViewPager.setVisibility(View.GONE);

        //Show Fragment
        mFrameLayout.setVisibility(View.VISIBLE);

        //Open Fragment
        mScoreDetailFragment = new ScoreDetailFragment();
        mScoreDetailFragment.setmScore(mScore);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_all, mScoreDetailFragment)
                .commit();
    }


    /**
     * This is to handle what happens when an item is clicked on the menu bar
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.news:
                setNewsViewPager();
                break;
            case R.id.schedule:
                openScheduleFragment();
                break;
            case R.id.teams:
                openTeamFragment();
                break;
            case R.id.scores:
                openScoreFragment();
                break;

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
