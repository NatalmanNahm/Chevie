package com.example.chevie.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chevie.Fragments.TeamCardFragment;
import com.example.chevie.Models.EventHome;
import com.example.chevie.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * adapter for the Event home that will hold event for a team match
 */
public class EventHomeAdapter extends RecyclerView.Adapter<EventHomeAdapter.EventHomeHolder> {

    //Initializers
    ArrayList<EventHome> mEventHome = new ArrayList<>();
    Context mContext;

    /**
     * Constructor for Homme Adapter
     * @param eventHome
     * @param context
     */
    public EventHomeAdapter(ArrayList<EventHome> eventHome, Context context){
        mEventHome = eventHome;
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
        holder.bindEventHome(mEventHome.get(position));
    }

    @Override
    public int getItemCount() {
        if (mEventHome == null) return 0;
        return mEventHome.size();
    }

    public void setmEventHome(ArrayList<EventHome> mEventHome) {
        this.mEventHome = mEventHome;
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

        public EventHomeHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindEventHome(EventHome eventHome){
            mTeamOneName.setText(eventHome.getmHomeTeam());
            mTeamTwoName.setText(eventHome.getmAwayTeam());
            Log.d("HOME1", eventHome.getmHomeTeam());
            mForecast.setText(eventHome.getmForcastDesc());
            mDate.setText(eventHome.getmDate());
            mHigh.setText(String.valueOf(eventHome.getmHigh()));
            mLow.setText(String.valueOf(eventHome.getmLow()));
            mStadium.setText(eventHome.getmStadium());

            AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
            TeamCardFragment teamCardFragment = new TeamCardFragment();
            teamCardFragment.setmTeamTwoId(eventHome.getmAwayTeam());
            teamCardFragment.setmTeamoneId(eventHome.getmHomeTeam());
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.team_card, teamCardFragment)
                    .commit();
        }
    }
}
