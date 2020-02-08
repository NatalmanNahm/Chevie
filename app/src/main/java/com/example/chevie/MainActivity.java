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
import com.example.chevie.Models.News;
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

                //get rid of the frameLayout View
                mFrameLayout.setVisibility(View.GONE);

                //Open the NewsPager with its Fragment
                mNewsDetailPagerAdapter = new NewsDetailPagerAdapter(getSupportFragmentManager(), mNews);
                mViewPager.setAdapter(mNewsDetailPagerAdapter);
                mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
                mViewPager.setCurrentItem(mNewsPostion);
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.news:

        }

        return true;
    }
}
