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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.chevie.Adapters.NewsDetailPagerAdapter;
import com.example.chevie.Auth.LoginActivity;
import com.example.chevie.Fragments.ScheduleDetailFragment;
import com.example.chevie.Fragments.ScoreDetailFragment;
import com.example.chevie.Fragments.TeamAllFragment;
import com.example.chevie.Models.News;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.Models.User;
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

    private TextView mName, mEmail;

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
        mLogOutBtn = (Button) findViewById(R.id.logOut_btn);
        mName = (TextView) findViewById(R.id.name_of_user);
        mEmail = (TextView) findViewById(R.id.email_of_user);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserId = user.getUid();


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
                Log.w(TAG, "loadUser:onCancelled", databaseError.toException());
            }
        });
//        mName.setText(mUser.getmName());
//        mEmail.setText(mUser.getmEmail());

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
     * Heloer method to get user data from the database
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
