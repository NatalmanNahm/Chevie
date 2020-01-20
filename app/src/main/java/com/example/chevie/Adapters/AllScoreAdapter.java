package com.example.chevie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chevie.Models.AllScore;
import com.example.chevie.Models.Schedule;
import com.example.chevie.Models.ScoreHome;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * All score adapter to display score for all teams match
 */

public class AllScoreAdapter extends RecyclerView.Adapter<AllScoreAdapter.AllScoreHolder> {

    //Initializer
    ArrayList<AllScore> mAllScore = new ArrayList<>();
    Context mContext;

    /**
     * Constructor for AllScoreAdapter
     * @param mScore
     * @param context
     */
    public AllScoreAdapter(ArrayList<AllScore> mScore, Context context){
        mAllScore = mScore;
        mContext = context;
    }

    @NonNull
    @Override
    public AllScoreAdapter.AllScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.all_score_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new AllScoreAdapter.AllScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllScoreAdapter.AllScoreHolder holder, int position) {
        holder.bindAllScore(mAllScore.get(position));
    }

    @Override
    public int getItemCount() {
        if (mAllScore == null) return 0;
        return mAllScore.size();
    }

    public void setmAllScore(ArrayList<AllScore> mAllScore) {
        this.mAllScore = mAllScore;
        notifyDataSetChanged();
    }

    public class AllScoreHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.home) ImageView mHomeLogo;
        @Bind(R.id.away) ImageView mAwayLogo;
        @Bind(R.id.home_nom) TextView mHomeName;
        @Bind(R.id.away_nom) TextView mAwayName;
        @Bind(R.id.home_1st) TextView mHome1st;
        @Bind(R.id.away_1st) TextView mAway1st;
        @Bind(R.id.home_2nd) TextView mHome2nd;
        @Bind(R.id.away_2nd) TextView mAway2nd;
        @Bind(R.id.home_3rd) TextView mHome3rd;
        @Bind(R.id.away_3rd) TextView mAway3rd;
        @Bind(R.id.home_4th) TextView mHome4th;
        @Bind(R.id.away_4th) TextView mAway4th;
        @Bind(R.id.home_fin) TextView mHomeFinScr;
        @Bind(R.id.away_fin) TextView mAwayFinScr;
        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.week) TextView mWeek;

        public AllScoreHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        //Bind data to their views
        public void bindAllScore(AllScore allScore){
            SvgLoaderUtil.fetchSvg(mContext, allScore.getHomeLogo(),mHomeLogo);
            mHomeName.setText(allScore.getmHomeTeam());
            mHome1st.setText(String.valueOf(allScore.getmHomeQtr1()));
            mHome2nd.setText(String.valueOf(allScore.getmHomeQtr2()));
            mHome3rd.setText(String.valueOf(allScore.getmHomeQtr3()));
            mHome4th.setText(String.valueOf(allScore.getmHomeQtr4()));
            mHomeFinScr.setText(String.valueOf(allScore.getmHomeScore()));

            SvgLoaderUtil.fetchSvg(mContext, allScore.getAwayLogo(), mAwayLogo);
            mAwayName.setText(allScore.getmAwayTeam());
            mAway1st.setText(String.valueOf(allScore.getmAwayQtr1()));
            mAway2nd.setText(String.valueOf(allScore.getmAwayQtr2()));
            mAway3rd.setText(String.valueOf(allScore.getmAwayQtr3()));
            mAway4th.setText(String.valueOf(allScore.getmAwayQtr4()));
            mAwayFinScr.setText(String.valueOf(allScore.getmAwayScore()));

            mDate.setText(allScore.getmDate());
            mWeek.setText(String.valueOf(allScore.getmWeek()));
        }
    }
}