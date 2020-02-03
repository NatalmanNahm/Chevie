package com.example.chevie.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chevie.Models.ScheduleDetail;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter for the schedule Adapter
 */

public class ScheduleDetailAdapter extends RecyclerView.Adapter<ScheduleDetailAdapter.ScheduleHolder> {

    //Initializer
    ArrayList<ScheduleDetail> mScheduleDetail = new ArrayList<>();
    Context mContext;

    /**
     * Constructor for ScheduleDetailAdapter
     * @param mScheduleDetail
     * @param mContext
     */
    public ScheduleDetailAdapter(ArrayList<ScheduleDetail> mScheduleDetail, Context mContext) {
        this.mScheduleDetail = mScheduleDetail;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ScheduleDetailAdapter.ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.schedule_detail_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new ScheduleDetailAdapter.ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleDetailAdapter.ScheduleHolder holder, int position) {
        holder.bindScheduleDetail(mScheduleDetail.get(position));
    }

    @Override
    public int getItemCount() {
        if (mScheduleDetail == null) return 0;
        return mScheduleDetail.size();
    }

    public void setmScheduleDetail(ArrayList<ScheduleDetail> mScheduleDetail) {
        this.mScheduleDetail = mScheduleDetail;
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.time) TextView mTime;
        @Bind(R.id.home) ImageView mHomeLogo;
        @Bind(R.id.away) ImageView mAwayLogo;
        @Bind(R.id.home_nom) TextView mHomeName;
        @Bind(R.id.away_nom) TextView mAwayName;
        @Bind(R.id.home_def) TextView mHomeDef;
        @Bind(R.id.away_def) TextView mAwayDef;
        @Bind(R.id.home_off) TextView mHomeOff;
        @Bind(R.id.away_off) TextView mAwayOff;
        @Bind(R.id.home_bye_week) TextView mHomeByeWeek;
        @Bind(R.id.away_bye_week) TextView mAwayByeWeek;
        @Bind(R.id.stadium_name) TextView mStadiumName;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindScheduleDetail(ScheduleDetail scheduleDetail){

            SvgLoaderUtil.fetchSvg(mContext, scheduleDetail.getmTeamOneLogo(), mHomeLogo);
            SvgLoaderUtil.fetchSvg(mContext, scheduleDetail.getmTeamTwoLogo(), mAwayLogo);
            mDate.setText(scheduleDetail.getmDate());
            mTime.setText(scheduleDetail.getmTime());
            mHomeName.setText(scheduleDetail.getmHomeTeam());
            mAwayName.setText(scheduleDetail.getmAwayTeam());
            mHomeDef.setText(scheduleDetail.getmOneDefensiveSch());
            mAwayDef.setText(scheduleDetail.getmTwoDefensiveSch());
            mHomeOff.setText(scheduleDetail.getmOneOffensiveSch());
            mAwayOff.setText(scheduleDetail.getmTwoOffensiveSch());
            mHomeByeWeek.setText( R.string.week_label +  String.valueOf(scheduleDetail.getmOneByeWeek()));
            mAwayByeWeek.setText(R.string.week_label + String.valueOf(scheduleDetail.getmTwoByeWeek()));
            mStadiumName.setText(scheduleDetail.getmStadium());

        }
    }
}
