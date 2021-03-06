package com.example.chevie.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chevie.Models.News;
import com.example.chevie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Adapater for news object to populate the UI
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewholder> {

    //initializer
    ArrayList<News> mNews = new ArrayList<>();
    Context mContext;

    //Create an onClickHandler to make it easier for the
    // activity to interact with the recycleView
    private final NewsAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface NewsAdapterOnClickHandler{
        void onClick(int position, ArrayList<News> news);
    }

    /**
     * Constructor for the adapter
     * @param context
     * @param news
     */
    public NewsAdapter(Context context, ArrayList<News> news, NewsAdapterOnClickHandler clickHandler){
        mContext = context;
        mNews = news;
        mClickHandler = clickHandler;
    }


    @NonNull
    @Override
    public NewsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.news_list_items;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new NewsViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewholder newsHolder, int position) {
        newsHolder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        if (mNews == null) return 0;
        return mNews.size();
    }

    //Creating a setter for the news
    public void setmNews(ArrayList<News> mNews) {
        this.mNews = mNews;
        notifyDataSetChanged();
    }

    /**
     * Cache of the children views for a news list Item
     */
    public class NewsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.news_title) TextView mNewsTitle;
        @Bind(R.id.news_time_shared) TextView mTime;
        @Bind(R.id.source) TextView mSource;
        @Bind(R.id.player_name) TextView mPlayerShortName;
        @Bind(R.id.player_Pic) ImageView mPlayerImg;
        Context mContext;

        public NewsViewholder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        //This is to populate texts and image for the news UI
        public void bindNews(News news){
            mNewsTitle.setText(news.getmNewsInfo().getmTitle());
            mTime.setText(news.getmNewsInfo().getmTimeShared());
            mSource.setText(news.getmNewsInfo().getmSource());
            mPlayerShortName.setText(news.getmPlayerShortName());
            Picasso.get().load(news.getmPlayerPic()).into(mPlayerImg);

        }


        //Parsing teh current news Object on click
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            mClickHandler.onClick(position, mNews);
        }
    }
}
