package com.example.chevie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.chevie.Adapters.NewsDetailPagerAdapter;
import com.example.chevie.AsyncTask.NewsAsyncTask;
import com.example.chevie.Auth.LoginActivity;
import com.example.chevie.Fragments.ProfileFragment;
import com.example.chevie.Fragments.ScheduleDetailFragment;
import com.example.chevie.Fragments.ScoreDetailFragment;
import com.example.chevie.Fragments.TeamAllFragment;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.TimeFrame;
import com.example.chevie.Models.User;
import com.example.chevie.Utilities.NetworkUtils;
import com.example.chevie.Utilities.ZoomOutPageTransformer;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //initializer
    private DrawerLayout mDrawer;
    private FrameLayout mFrameLayout;
    private ViewPager mViewPager;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button mLogOutBtn;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User mUser;
    private String mUserId;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String ERROR = "loadUser:onCancelled";
    private static final String NEWSPOSITION = "positionNews";
    private static final String NEWSARRAY = "newsArray";
    private static final String OPENSCH = "OpenSchedule";
    private static final String OPENTEAM = "OpenTeams";
    private static final String SCOREARRY = "scoreArray";
    private static final String OPENMAIN = "Open news Fragment";


    private TextView mName, mEmail;

    //for news
    private int mNewsPostion;
    private ArrayList<News> mNews = new ArrayList<>();
    private NewsDetailPagerAdapter mNewsDetailPagerAdapter;
    private static final String POSITION = "news Position";
    private static final String ARRAY_NEWS = "news Arraylist";


    //For Schedule
    private ScheduleDetailFragment mSchDetailFragment;
    private static final String SCH_FRAG = "Schedule Fragment";

    //For Teams
    private TeamAllFragment mTeamAllFragmant;

    //For Scores
    private ScoreDetailFragment mScoreDetailFragment;
    private ArrayList<ScoreHome> mScore = new ArrayList<>();
    private ArrayList<TimeFrame> mCurrent = new ArrayList<>();
    private String mCurrentSeason;

    //For Profile
    private ProfileFragment mProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //See id there is a fragment saved then retrieve that or create a new one
        if (savedInstanceState == null){
            //create a new instance fragment
            mScoreDetailFragment = new ScoreDetailFragment();
            mProfileFragment = new ProfileFragment();
            mTeamAllFragmant = new TeamAllFragment();
            mSchDetailFragment = new ScheduleDetailFragment();

        }

        //Set default toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_draw);
        mFrameLayout = findViewById(R.id.fragment_all);
        mViewPager = findViewById(R.id.viewPager_main);
        mLogOutBtn = (Button) findViewById(R.id.logOut_btn);
        mName = (TextView) findViewById(R.id.name_of_user);
        mEmail = (TextView) findViewById(R.id.email_of_user);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserId = user.getUid();

        //Fetch Current Season
        new FetchCurrentSeason().execute();


        //Getting back user info saved in the database
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting User failed, log a message
                Log.w(TAG, ERROR, databaseError.toException());
            }
        });

        navigationView.setNavigationItemSelectedListener(this);

        //create an intent
        Intent intent = getIntent();

        if (intent != null){

            if (intent.hasExtra(OPENMAIN)){
                new FetchNewsData(new NewsAsyncResponse() {
                    @Override
                    public void processFinish(ArrayList<News> output) {
                        mNews = output;
                        setNewsViewPager();
                    }
                }).execute();
            }

            //Getting the values parse from the newsFragment
            if (intent.hasExtra(NEWSPOSITION)){
                mNewsPostion = intent.getIntExtra(NEWSPOSITION, 0);
                mNews = intent.getParcelableArrayListExtra(NEWSARRAY);
                navigationView.setCheckedItem(R.id.news);
                setNewsViewPager();
            }

            //Open Schedule detail Fragment
            if (intent.hasExtra(OPENSCH)){
                navigationView.setCheckedItem(R.id.schedule);
                openScheduleFragment();
            }

            //Open All Team Fragment
            if (intent.hasExtra(OPENTEAM)){
                navigationView.setCheckedItem(R.id.teams);
                openTeamFragment();
            }

            if (intent.hasExtra(SCOREARRY)){
                mScore = intent.getParcelableArrayListExtra(SCOREARRY);
                navigationView.setCheckedItem(R.id.scores);
                openScoreFragment();
            }
        }

        //this is for menu bar to unfold and fold back in on pressed
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

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
        mScoreDetailFragment.setmScore(mScore);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_all, mScoreDetailFragment)
                .commit();
    }

    /**
     * Helper Method to open Profile Fragment
     */
    public void openProfileFragment(){
        //get rid of the ViewPager
        mViewPager.setVisibility(View.GONE);

        //Show Fragment
        mFrameLayout.setVisibility(View.VISIBLE);

        //Open Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_all, mProfileFragment)
                .commit();
    }

    /**
     * Helper method to get user data from the database
     * and display it on the UI
     * @param dataSnapshot
     */
    public void showData (DataSnapshot dataSnapshot){
        for (DataSnapshot ds: dataSnapshot.getChildren()){
            mUser = new User();
            mUser.setmName(ds.child(mUserId).getValue(User.class).getmName());
            mUser.setmEmail(ds.child(mUserId).getValue(User.class).getmEmail());

            mName.setText(mUser.getmName());
            mEmail.setText(mUser.getmEmail());
        }
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
                if (mNews == null || mNews.isEmpty()){

                    //Fetch news data then display it on the UI
                    FetchNewsData newsData = (FetchNewsData) new FetchNewsData(new NewsAsyncResponse() {
                        @Override
                        public void processFinish(ArrayList<News> output) {
                            mNews = output;
                            setNewsViewPager();
                        }
                    }).execute();
                } else {
                    setNewsViewPager();
                }
                break;
            case R.id.schedule:
                openScheduleFragment();
                break;
            case R.id.teams:
                openTeamFragment();
                break;
            case R.id.scores:

                if (mScore == null || mScore.isEmpty()){
                    //Fetch Score data then display it on UI
                    FetchScore fetchScore = (FetchScore) new FetchScore(new ScoreInterface() {
                        @Override
                        public void processFinish(ArrayList<ScoreHome> output) {
                            mScore = output;
                            openScoreFragment();
                        }
                    }).execute();
                } else {
                    openScoreFragment();
                }
                break;
            case R.id.profile:
                openProfileFragment();

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * AsyncTask to get news Data
     */
    public class FetchNewsData extends AsyncTask<String, Void, ArrayList<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //call back the interface
        public NewsAsyncResponse delegate;

        //Constructor assigning callback
        public FetchNewsData(NewsAsyncResponse delegate){
            this.delegate = delegate;
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            //Do all needed in the background.
            new NewsAsyncTask().doInTheBackgroundNewsTask(mNews);
            return mNews;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {
            delegate.processFinish(news);
        }
    }

    /**
     * Interface to get the News arraylist out of the asynctask.
     */
    public interface NewsAsyncResponse {
        void processFinish(ArrayList<News> output);
    }


    /**
     * This asyncTask class is to get the current time frame then get our current season.
     */
    public class FetchCurrentSeason extends AsyncTask<String, Void, ArrayList<TimeFrame>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<TimeFrame> doInBackground(String... strings) {
            mCurrent = NetworkUtils.fetchCurrentTimeFrame();
            TimeFrame timeFrame = mCurrent.get(0);
            mCurrentSeason = timeFrame.getmCurrentSeason();

            return mCurrent;
        }

        @Override
        protected void onPostExecute(ArrayList<TimeFrame> timeFrames) {
            super.onPostExecute(timeFrames);
        }
    }

    /**
     * This asynsTask class is to get score data
     */
    public class FetchScore extends AsyncTask<String, Void, ArrayList<ScoreHome>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //call back the interface
        public ScoreInterface delegate;

        //Constructor assigning callback
        public FetchScore (ScoreInterface delegate){
            this.delegate = delegate;
        }

        @Override
        protected ArrayList<ScoreHome> doInBackground(String... strings) {
            mScore = NetworkUtils.fetchScoreHome(mCurrentSeason);

            //if the Current season hasn't started yet
            //then use Data from previous season
            if (mScore.size() == 0){
                ArrayList<TimeFrame> mPrevious = NetworkUtils.fetchPreviousTimeFrame();
                TimeFrame timeFrame = mPrevious.get(0);
                String mPreviousSeason = timeFrame.getmCurrentSeason();
                mScore = NetworkUtils.fetchScoreHome(mPreviousSeason);
            }
            return mScore;
        }

        @Override
        protected void onPostExecute(ArrayList<ScoreHome> scoreHomes) {
            delegate.processFinish(scoreHomes);

        }
    }

    /**
     * Interface to get the Score arraylist out of the asynctask.
     */
    public interface ScoreInterface {
        void processFinish(ArrayList<ScoreHome> output);
    }
}
