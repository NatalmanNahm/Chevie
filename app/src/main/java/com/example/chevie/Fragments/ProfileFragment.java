package com.example.chevie.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chevie.MainActivity;
import com.example.chevie.Models.User;
import com.example.chevie.R;
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
    private TextView mName, mUserName, mEmail;
    private View mRootView;

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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserId = user.getUid();

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
                Log.w(TAG, "loadUser:onCancelled", databaseError.toException());
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
