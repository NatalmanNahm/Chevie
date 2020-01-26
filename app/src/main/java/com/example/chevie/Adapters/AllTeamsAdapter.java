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

import com.example.chevie.Models.Teams;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * All teams Adapter for the all teams Activity
 */

public class AllTeamsAdapter extends RecyclerView.Adapter<AllTeamsAdapter.AllTeamsHolder> {

    //Initializer
    ArrayList<Teams> mTeams = new ArrayList<>();
    Context mContext;
    private TeamsOnClickHandler mClickHandler;

    /**
     * Interface that handle an Item Click
     */
    public interface TeamsOnClickHandler{
        void onClick(ArrayList<Teams> teams, int position);
    }

    /**
     * Constructor for the Adapter
     * @param mTeams
     * @param mContext
     */
    public AllTeamsAdapter(ArrayList<Teams> mTeams, Context mContext, TeamsOnClickHandler clickHandler) {
        this.mTeams = mTeams;
        this.mContext = mContext;
        this.mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public AllTeamsAdapter.AllTeamsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.all_team_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new AllTeamsAdapter.AllTeamsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTeamsAdapter.AllTeamsHolder holder, int position) {
        holder.bindAllTeams(mTeams.get(position));

    }

    @Override
    public int getItemCount() {
        if (mTeams == null) return 0;
        return mTeams.size();
    }

    public void setmTeams(ArrayList<Teams> mTeams) {
        this.mTeams = mTeams;
        notifyDataSetChanged();
    }

    public class AllTeamsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.team_logo) ImageView mTeamLogo;
        @Bind(R.id.team_name) TextView mTeamName;
        @Bind(R.id.defensive) TextView mDefensive;

        public AllTeamsHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindAllTeams(Teams teams){
            SvgLoaderUtil.fetchSvg(mContext, teams.getmTeamLogo(), mTeamLogo);
            mTeamName.setText(teams.getmTeamKey());
            mDefensive.setText(teams.getmDefensive());
            GradientDrawable drawable = (GradientDrawable) mTeamLogo.getBackground();
            drawable.setColor(Color.parseColor("#" + teams.getmPrimaryColor()));
        }


        /**
         * Parsing the current Team object on click
         * @param v
         */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickHandler.onClick(mTeams, position);
        }
    }
}
