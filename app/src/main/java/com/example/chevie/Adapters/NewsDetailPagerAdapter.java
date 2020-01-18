package com.example.chevie.Adapters;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ViewGroup;

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


    public NewsDetailPagerAdapter(@NonNull FragmentManager fm, ArrayList<News> newsDetail) {
        super(fm);
        this.mNewsDetail = newsDetail;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle mNewsBundle = new Bundle();
        mNewsBundle.putInt("page", position);
        Log.d("POSITION1", String.valueOf(position));
        mNewsBundle.putParcelableArrayList("news", mNewsDetail);
        mNewsBundle.putBoolean("isLastPage", position == getCount()-1);
        newsDetailFragment.setArguments(mNewsBundle);

        return newsDetailFragment;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return mNewsDetail.size();
    }
}
