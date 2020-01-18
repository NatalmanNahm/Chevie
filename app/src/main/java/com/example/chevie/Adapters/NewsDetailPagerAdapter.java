package com.example.chevie.Adapters;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.chevie.Fragments.NewsDetailFragment;
import com.example.chevie.Models.News;

import java.util.ArrayList;


/**
 * Adapter for viewPager to help us populate the UI
 */
public class NewsDetailPagerAdapter extends FragmentStatePagerAdapter {

    //Initializer
    private ArrayList<News> mNewsDetail;
    private Bundle mNewsBundle;


    public NewsDetailPagerAdapter(@NonNull FragmentManager fm, ArrayList<News> newsDetail) {
        super(fm);
        this.mNewsDetail = newsDetail;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        mNewsBundle.putParcelableArrayList("news", mNewsDetail);
        mNewsBundle.putInt("page", position);
        mNewsBundle.putBoolean("isLastPage", position == getCount()-1);
        newsDetailFragment.setArguments(mNewsBundle);

        return newsDetailFragment;
    }

    @Override
    public int getCount() {
        return mNewsDetail.size();
    }
}
