package com.example.chevie.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chevie.MainActivity;
import com.example.chevie.Models.User;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private User mUser;
    private String mUserId;
    private static final String TAG = ProfileFragment.class.getSimpleName();
    private TextView mName, mUserName, mEmail, mMyTeamName;
    private View mRootView;
    private ImageView mMyTeamImg;
    private LinearLayout mTeamLinear, mNewsLiniear, mScheduleLiniear;
    private static final String TEAMNAME = "mTeamName";
    private static final String TEAMLOGO = "mTeamLogo";
    private static final String USER = "Users";
    private static final String TEAMS = "Teams";
    private static final String ERROR_MESSAGE = "loadUser:onCancelled";
    private Context mContext;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_profile, container, false);

        mName = (TextView) mRootView.findViewById(R.id.name);
        mUserName = (TextView) mRootView.findViewById(R.id.user_name);
        mEmail = (TextView) mRootView.findViewById(R.id.email);
        mMyTeamImg = (ImageView) mRootView.findViewById(R.id.my_team_img);
        mMyTeamName = (TextView) mRootView.findViewById(R.id.my_team_name);
        mNewsLiniear = (LinearLayout) mRootView.findViewById(R.id.my_news_layout);
        mTeamLinear = (LinearLayout) mRootView.findViewById(R.id.my_team_layout);
        mScheduleLiniear = (LinearLayout) mRootView.findViewById(R.id.schdule_Layout);
        mContext = mRootView.getContext();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserId = user.getUid();
        final DatabaseReference myTeamRef = mDatabaseRef.child(USER).child(mUserId).child(TEAMS);

        //Getting back user info saved in the database
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting User failed, log a message
                Log.w(TAG, ERROR_MESSAGE, databaseError.toException());
            }
        });

        //Get data needed from the database and set them to their
        //appropriate views.
        myTeamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String name = dataSnapshot.child(TEAMNAME).getValue(String.class);
                    Log.d("NAMETEAM", name);
                    String imgString = dataSnapshot.child(TEAMLOGO).getValue(String.class);
                    Log.d("IMGURL", imgString);


                    if (name == null){
                        mMyTeamName.setText("");

                    } else {
                        mMyTeamName.setText(name);
                        SvgLoaderUtil.fetchSvg(mContext, imgString, mMyTeamImg);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting User failed, log a message
                Log.w(TAG, ERROR_MESSAGE, databaseError.toException());
            }
        });

        return mRootView;
    }

    /**
     * Helper method to get user data from the database
     * and display it on the UI
     * @param dataSnapshot
     */
    public void showData (DataSnapshot dataSnapshot){
        for (DataSnapshot ds: dataSnapshot.getChildren()){
            mUser = new User();
            mUser.setmName(ds.child(mUserId).getValue(User.class).getmName());
            mUser.setmEmail(ds.child(mUserId).getValue(User.class).getmEmail());

            mUserName.setText(mUser.getmName());
            mName.setText(mUser.getmName());
            mEmail.setText(mUser.getmEmail());
        }
    }

}
