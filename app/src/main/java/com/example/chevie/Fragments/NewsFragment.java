package com.example.chevie.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chevie.Adapters.NewsAdapter;
import com.example.chevie.AsyncTask.NewsAsyncTask;
import com.example.chevie.MainActivity;
import com.example.chevie.Models.News;
import com.example.chevie.Models.NewsInfo;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsAdapter.NewsAdapterOnClickHandler{

    // the fragment initialization parameters
    private View rootView;
    private Context mContext;
    private RecyclerView mNewsRecyclerView;
    private TextView mNewserrorMessage;
    private LinearLayoutManager mNewsLLManager;
    private NewsAdapter mNewsAdapter;
    private ArrayList<News> mNewsArrayList;
    private ArrayList<NewsInfo> mInfoArray;
    private ArrayList<PlayerProfile> mPlayerProf;
    private static final String POSITION = "positionNews";
    private static final String NEWSARRAY = "newsArray";


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_news, container, false);

        //Getting reference to the recyclerview
        mNewsArrayList = new ArrayList<>();
        mNewsRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_recyclerView);
        mNewserrorMessage = (TextView) rootView.findViewById(R.id.error_message);
        mContext = rootView.getContext();

        //Creating a Linear Layout manager
        mNewsLLManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        mNewsRecyclerView.setLayoutManager(mNewsLLManager);
        mNewsRecyclerView.setHasFixedSize(true);
        mNewsAdapter = new NewsAdapter(getContext(), mNewsArrayList, this);
        mNewsRecyclerView.setAdapter(mNewsAdapter);
        new PagerSnapHelper().attachToRecyclerView(mNewsRecyclerView);

        new FetchNewsData().execute();

        return rootView;
    }

    /**
     * simple method to show news data on the ui
     */
    private void showNewsDataView() {
        /* First, make sure the error is invisible */
        mNewserrorMessage.setVisibility(View.INVISIBLE);
        /* Then, make sure the news data is visible */
        mNewsRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * Simple method to show error when needed.
     */
    private void showNewsErrorMessage() {
        /* First, hide the currently visible data */
        mNewsRecyclerView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mNewserrorMessage.setVisibility(View.VISIBLE);
    }

    /**
     * This is to handle onClick so when user click on
     * a news it takes them to the detail of the News.
     * @param position
     * @param news
     */
    @Override
    public void onClick(int position, ArrayList<News> news) {
        Class destinationClass = MainActivity.class;

        //Creating intent and opening NewsDetailActivity
        Intent intent = new Intent(getActivity(), destinationClass);
        intent.putExtra(POSITION, position);
        intent.putParcelableArrayListExtra(NEWSARRAY, news);
        startActivity(intent);

    }

    /**
     * AsyncTask to get news Data
     */
    public class FetchNewsData extends AsyncTask<String, Void, ArrayList<News>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<News> doInBackground(String... strings) {
            //Do all needed for the background task
            new NewsAsyncTask().doInTheBackgroundNewsTask(mNewsArrayList);
            return mNewsArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> news) {

            if (news != null && !news.isEmpty()){
                showNewsDataView();
                mNewsAdapter.setmNews(news);
            } else {
                showNewsErrorMessage();
            }
            super.onPostExecute(news);
        }
    }

}
