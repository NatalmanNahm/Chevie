package com.example.chevie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to help inflate the teamDetailActivity with list of players
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {

    //Initializer
    ArrayList<PlayerProfile> mPlayers;
    Context mContext;

    /**
     * Constrcutor for the adapter
     * @param mPlayers
     * @param mContext
     */
    public PlayerAdapter(ArrayList<PlayerProfile> mPlayers, Context mContext) {
        this.mPlayers = mPlayers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlayerAdapter.PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.players_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = layoutInflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new PlayerAdapter.PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerAdapter.PlayerHolder holder, int position) {
        holder.bindPlayerProfile(mPlayers.get(position));
    }

    @Override
    public int getItemCount() {
        if (mPlayers == null) return 0;
        return mPlayers.size();
    }

    public void setmPlayers(ArrayList<PlayerProfile> mPlayers) {
        this.mPlayers = mPlayers;
        notifyDataSetChanged();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.player_pic) ImageView mPlayerPic;
        @Bind(R.id.player_name) TextView mPlayerName;
        @Bind(R.id.age) TextView mPlayerAge;
        @Bind(R.id.position) TextView mPlayerPosition;

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindPlayerProfile(PlayerProfile playerProfile){
            Picasso.get().load(playerProfile.getmPlayerImg()).into(mPlayerPic);
            mPlayerName.setText(playerProfile.getmShortName());
            mPlayerAge.setText(String.valueOf(playerProfile.getmAge()));
            mPlayerPosition.setText(playerProfile.getmPosition());
        }
    }
}
