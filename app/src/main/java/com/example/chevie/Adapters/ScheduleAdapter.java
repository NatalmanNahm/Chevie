package com.example.chevie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chevie.Fragments.TeamCardFragment;
import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.Schedule;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * adapter for the Event home that will hold event for a team match
 */
public class EventHomeAdapter extends RecyclerView.Adapter<EventHomeAdapter.EventHomeHolder> {

    //Initializers
    ArrayList<Schedule> mSchedule = new ArrayList<>();
    Context mContext;

    /**
     * Constructor for Homme Adapter
     * @param eventHome
     * @param context
     */
    public EventHomeAdapter(ArrayList<Schedule> eventHome, Context context){
        mSchedule = eventHome;
        mContext = context;
    }

    @NonNull
    @Override
    public EventHomeAdapter.EventHomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.event_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new EventHomeAdapter.EventHomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHomeAdapter.EventHomeHolder holder, int position) {
        holder.bindEventHome(mSchedule.get(position));
    }

    @Override
    public int getItemCount() {
        if (mSchedule == null) return 0;
        return mSchedule.size();
    }

    public void setmSchedule(ArrayList<Schedule> mSchedule) {
        this.mSchedule = mSchedule;
        notifyDataSetChanged();
    }

    public class EventHomeHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.team_name1) TextView mTeamOneName;
        @Bind(R.id.team_name2) TextView mTeamTwoName;
        @Bind(R.id.forcast) TextView mForecast;
        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.high) TextView mHigh;
        @Bind(R.id.low) TextView mLow;
        @Bind(R.id.stadium) TextView mStadium;
        @Bind(R.id.team_one_logo) ImageView mLogoOne;
        @Bind(R.id.team_two_logo) ImageView mLogoTwo;
        @Bind(R.id.offensive1) TextView mOffOne;
        @Bind(R.id.offensive2) TextView mOffTwo;
        @Bind(R.id.denfense1) TextView mDefOne;
        @Bind(R.id.defense2) TextView mDefTwo;
        @Bind(R.id.bye_week1) TextView mByeWeekOne;
        @Bind(R.id.bye_week2) TextView mByeWeekTwo;

        public EventHomeHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindEventHome(Schedule schedule){
            mTeamOneName.setText(schedule.getmHomeTeam());
            mTeamTwoName.setText(schedule.getmAwayTeam());
            mForecast.setText(schedule.getmForcastDesc());
            mDate.setText(schedule.getmDate());
            mHigh.setText(String.valueOf(schedule.getmHigh()));
            mLow.setText(String.valueOf(schedule.getmLow()));
            mStadium.setText(schedule.getmStadium());
            SvgLoaderUtil.fetchSvg(mContext, schedule.getmTeamOneLogo(), mLogoOne);
            SvgLoaderUtil.fetchSvg(mContext, schedule.getmTeamTwoLogo(), mLogoTwo);
            mOffOne.setText(schedule.getmOneOffensiveSch());
            mOffTwo.setText(schedule.getmTwoOffensiveSch());
            mDefOne.setText(schedule.getmOneDefensiveSch());
            mDefTwo.setText(schedule.getmTwoDefensiveSch());
            mByeWeekOne.setText(schedule.getmOneByeWeek());
            mByeWeekTwo.setText(schedule.getmTwoByeWeek());

        }
    }
}
