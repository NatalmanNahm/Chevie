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

import com.example.chevie.Models.Schedule;
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
    ArrayList<Schedule> mScheduleDetail = new ArrayList<>();
    Context mContext;

    /**
     * Constructor for ScheduleDetailAdapter
     * @param mScheduleDetail
     * @param mContext
     */
    public ScheduleDetailAdapter(ArrayList<Schedule> mScheduleDetail, Context mContext) {
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
        return new ScheduleHolder(view);
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

    public void setmScheduleDetail(ArrayList<Schedule> mScheduleDetail) {
        this.mScheduleDetail = mScheduleDetail;
        notifyDataSetChanged();
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

        public void bindScheduleDetail(Schedule scheduleDetail){

            SvgLoaderUtil.fetchSvg(mContext, scheduleDetail.getmTeamCardOne().getmTeamLogo(), mHomeLogo);
            SvgLoaderUtil.fetchSvg(mContext, scheduleDetail.getmTeamCardTwo().getmTeamLogo(), mAwayLogo);
            mDate.setText(scheduleDetail.getmEventHome().getmDate());
            mTime.setText(scheduleDetail.getmEventHome().getmTime());
            mHomeName.setText(scheduleDetail.getmEventHome().getmHomeTeam());
            mAwayName.setText(scheduleDetail.getmEventHome().getmAwayTeam());
            mHomeDef.setText(scheduleDetail.getmTeamCardOne().getmDeffensiveSch());
            mAwayDef.setText(scheduleDetail.getmTeamCardTwo().getmDeffensiveSch());
            mHomeOff.setText(scheduleDetail.getmTeamCardOne().getmOneOffensiveSch());
            mAwayOff.setText(scheduleDetail.getmTeamCardTwo().getmOneOffensiveSch());
            mHomeByeWeek.setText(itemView.getContext().getResources().
                    getString(R.string.week_sch, scheduleDetail.getmTeamCardOne().getmOneByeWeek()));
            mAwayByeWeek.setText(itemView.getContext().getResources().
                    getString(R.string.week_sch, scheduleDetail.getmTeamCardTwo().getmOneByeWeek()));
            mStadiumName.setText(scheduleDetail.getmEventHome().getmStadium());

        }
    }
}
