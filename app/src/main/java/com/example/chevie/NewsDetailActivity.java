package com.example.chevie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.chevie.Adapters.NewsDetailPagerAdapter;
import com.example.chevie.Models.News;

import java.util.ArrayList;

/**
 * This is the class that holds the view pager
 * with detail of each news
 */
public class NewsDetailActivity extends AppCompatActivity {

    //initializer
    private int mPostion;
    private ArrayList<News> mNews = new ArrayList<>();
    private NewsDetailPagerAdapter mDetailPager;
    private ViewPager mDetailViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        //create an intent
        Intent intent = getIntent();

        //Getting the values parse from the newsFragment
        if (intent != null){
            if (intent.hasExtra("position")){
                mPostion = intent.getIntExtra("position", 0);
                mNews = intent.getParcelableArrayListExtra("newsArray");
            }
        }
        mDetailPager = new NewsDetailPagerAdapter(getSupportFragmentManager(), mNews);
        mDetailViewPager = (ViewPager) findViewById(R.id.News_Pager);
        mDetailViewPager.setAdapter(mDetailPager);
        mDetailViewPager.setCurrentItem(mPostion);


    }
}
