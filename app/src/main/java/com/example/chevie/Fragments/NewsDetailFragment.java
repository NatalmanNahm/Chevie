package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chevie.Models.News;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

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

    private ArrayList<News> mNews = new ArrayList<>();
    private int mPosition;
    private Context mContext;

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting values parse to the fragment
        Bundle detailBundle = this.getArguments();

        if (detailBundle != null){
            mPosition = detailBundle.getInt("page");
            mNews = detailBundle.getParcelableArrayList("news");
        }

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
        mContext = mRootView.getContext();

        //Getting current data
        News news = mNews.get(mPosition);
        String title = news.getmTitle();
        String playerPic = news.getmPlayerPic();
        String name = news.getmPlayerShortName();
        String time = news.getmTimeShared();
        String source = news.getmSource();
        String content = news.getmContent();

        //Binding views to their current data
        mTitle.setText(title);
        mPlayerName.setText(name);
        mTimeShared.setText(time);
        mNewsSource.setText(source);
        mContent.setText(content);
        SvgLoaderUtil.fetchSvg(mContext, playerPic, mPlayerPic);


        return mRootView;
    }

}
