package com.example.chevie.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chevie.Adapters.PlayerAdapter;
import com.example.chevie.Models.PlayerProfile;
import com.example.chevie.Models.Teams;
import com.example.chevie.R;
import com.example.chevie.Utilities.NetworkUtils;
import com.example.chevie.Utilities.SvgLoaderUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamDetailFragment extends Fragment {

    //Initializer
    private int mPosition;
    private String mTeamKey;
    private ArrayList<Teams> mTeams = new ArrayList<>();
    private ArrayList<PlayerProfile> mPlayerprofile = new ArrayList<>();
    private Context mContext;
    private View mRootView;
    private ConstraintLayout mLogoLayout;
    private ImageView mTeamLogo;
    private TextView mTeamName, mTeamInitial;
    private TextView mTeamLocation, mByeWeek;
    private TextView mDefensive, mOffensive;
    private TextView mConference, mDivision;
    private TextView mStadiumName, mStadiumLoc;
    private TextView mCapacity, mSurface;
    private TextView mHeadCoach, mSpecialCoach;
    private TextView mCdrDef, mCdrOff;
    private RecyclerView mPlayerRecyclerView;
    private PlayerAdapter mPlayerAdapter;
    private GridLayoutManager mGridLayoutManager;
    private ImageView mMyTeamTag;
    private Button mBtnAsMyTeam;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private String mUserId;
    private static final String TAG = TeamDetailFragment.class.getSimpleName();
    private static final String POSITION = "position";
    private static final String TEAMARRAY = "TeamsArray";
    private static final String USER = "Users";
    private static final String TEAMS = "Teams";
    private static final String ERROR_MESSAGE = "loadUser:onCancelled";
    private static final String TEAMKEY = "mTeamKey";


    public TeamDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Getting data parse to the Fragment
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt(POSITION);
            mTeams = bundle.getParcelableArrayList(TEAMARRAY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_team_detail, container, false);
        mContext = mRootView.getContext();

        //Bind View
        mLogoLayout = (ConstraintLayout) mRootView.findViewById(R.id.logo_const);
        mTeamLogo = (ImageView) mRootView.findViewById(R.id.logo);
        mTeamName = (TextView) mRootView.findViewById(R.id.team_name);
        mTeamInitial = (TextView) mRootView.findViewById(R.id.team_initial);
        mTeamLocation = (TextView) mRootView.findViewById(R.id.team_loc);
        mDefensive = (TextView) mRootView.findViewById(R.id.defense);
        mOffensive = (TextView) mRootView.findViewById(R.id.offense);
        mConference = (TextView) mRootView.findViewById(R.id.conference);
        mDivision = (TextView) mRootView.findViewById(R.id.division);
        mByeWeek = (TextView) mRootView.findViewById(R.id.bye_week);
        mStadiumName = (TextView) mRootView.findViewById(R.id.std_name);
        mStadiumLoc = (TextView) mRootView.findViewById(R.id.std_loc);
        mCapacity = (TextView) mRootView.findViewById(R.id.capacity);
        mSurface = (TextView) mRootView.findViewById(R.id.surface);
        mHeadCoach = (TextView) mRootView.findViewById(R.id.head_coach);
        mSpecialCoach = (TextView) mRootView.findViewById(R.id.special_coach);
        mCdrDef = (TextView) mRootView.findViewById(R.id.def_coord);
        mCdrOff = (TextView) mRootView.findViewById(R.id.off_coord);
        mMyTeamTag = (ImageView) mRootView.findViewById(R.id.my_team_tag);
        mBtnAsMyTeam = (Button) mRootView.findViewById(R.id.btn_add_team);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserId = user.getUid();
        final DatabaseReference myTeamRef = mDatabaseRef.child(USER).child(mUserId).child(TEAMS);


        //Get the current Item
        final Teams team = mTeams.get(mPosition);
        mTeamKey = team.getmTeamKey();


        //Adding my unique team to the datase only if there is not
        // already a team saved in the database.
        mBtnAsMyTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myTeamRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(mContext,getString(R.string.team_exist),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            myTeamRef.setValue(team);
                            mMyTeamTag.setVisibility(View.VISIBLE);
                            mBtnAsMyTeam.setVisibility(View.GONE);
                            Toast.makeText(mContext, getString(R.string.added_team),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Getting User failed, log a message
                        Log.w(TAG, ERROR_MESSAGE, databaseError.toException());
                    }
                });

            }
        });


        SvgLoaderUtil.imageCheck(mTeamLogo, team.getmTeamLogo(), mContext);
        mLogoLayout.setBackgroundColor(Color.parseColor(getString(R.string.hash_tag) + team.getmPrimaryColor()));
        mTeamName.setText(team.getmTeamName());
        mTeamInitial.setText(team.getmTeamKey());
        mTeamLocation.setText((team.getmTeamCity() + ", " + team.getmStadiumState()));
        mDefensive.setText(team.getmDefensive());
        mOffensive.setText(team.getmOffensive());
        mConference.setText(team.getmConference());
        mDivision.setText(team.getmDivision());
        mByeWeek.setText(String.valueOf(team.getmByeWeek()));
        mStadiumName.setText(team.getmStadiumName());
        mStadiumLoc.setText((team.getmStadiumCity() + ", " + team.getmStadiumState()));
        mCapacity.setText(String.valueOf(team.getmStadiumCapacity()));
        mSurface.setText(team.getmStadiumSurface());
        mHeadCoach.setText(team.getmHeadCoach());
        mSpecialCoach.setText(team.getmSpecTeamCoach());
        mCdrDef.setText(team.getmDefensiveCoord());
        mCdrOff.setText(team.getmOffensiveCoord());

        mPlayerRecyclerView = (RecyclerView) mRootView.findViewById(R.id.players_recycler);
        mGridLayoutManager = new GridLayoutManager(mContext, calculateNoOfColumns(mContext),
                GridLayoutManager.VERTICAL, false);
        mPlayerRecyclerView.setLayoutManager(mGridLayoutManager);
        mPlayerRecyclerView.setHasFixedSize(true);
        mPlayerAdapter = new PlayerAdapter(mPlayerprofile, mContext);
        mPlayerRecyclerView.setAdapter(mPlayerAdapter);

        //Check if this is the team saved. if it is then set the text and the imageView
        // accordingly.
        myTeamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    String teamKey = dataSnapshot.child(TEAMKEY).getValue(String.class);

                    if (teamKey.equals(team.getmTeamKey())){
                        mMyTeamTag.setVisibility(View.VISIBLE);
                        mBtnAsMyTeam.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting User failed, log a message
                Log.w(TAG, ERROR_MESSAGE, databaseError.toException());
            }
        });

        new FetchPlayerByTeam().execute();

        return mRootView;
    }

    /**
     * AsyncTask to get players by team
     */
    public class FetchPlayerByTeam extends AsyncTask<String, Void, ArrayList<PlayerProfile>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<PlayerProfile> doInBackground(String... strings) {
            mPlayerprofile = NetworkUtils.fetchPlayerByTeam(mTeamKey);

            return mPlayerprofile;
        }

        @Override
        protected void onPostExecute(ArrayList<PlayerProfile> playerProfiles) {
            super.onPostExecute(playerProfiles);

            if (playerProfiles != null && !playerProfiles.isEmpty()){
                mPlayerAdapter.setmPlayers(playerProfiles);
            }
        }
    }

    /**
     * Helper method to calculate number of columns to be displayed
     * @param context
     * @return
     */
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if(noOfColumns < 4)
            noOfColumns = 4;
        return noOfColumns;
    }


}