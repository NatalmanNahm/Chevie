package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chevie.R;

/**
 * A simple {@link Fragment} subclass.
 *Fragment that display a news detail a the time
 */
public class NewsDetailFragment extends Fragment {

    //Initializer
    private View mRootView;
    private TextView mTitle;
    private ImageView mPlayerPic;
    private TextView mPlayerName;
    private TextView mTimeShared;
    private TextView mNewsSource;
    private TextView mContent;

    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_news_detail, container, false);

        //bind Views
        mTitle = (TextView) mRootView.findViewById(R.id.news_detail_title);
        mPlayerPic = (ImageView) mRootView.findViewById(R.id.detail_news_player_img);
        mPlayerName = (TextView) mRootView.findViewById(R.id.news_detail_name);
        mTimeShared = (TextView) mRootView.findViewById(R.id.news_detail_time);
        mNewsSource = (TextView) mRootView.findViewById(R.id.news_detail_source);
        mContent = (TextView) mRootView.findViewById(R.id.news_detail_content);

        return mRootView;
    }

}
