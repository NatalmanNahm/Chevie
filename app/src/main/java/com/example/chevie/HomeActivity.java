package com.example.chevie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chevie.Auth.LoginActivity;
import com.example.chevie.Fragments.ScheduleFragment;
import com.example.chevie.Fragments.NewsFragment;
import com.example.chevie.Fragments.ScoreHomeFragment;
import com.example.chevie.Fragments.TeamHomeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button mLogOutBtn;
    FirebaseAuth mFirebaseAuth;
    private Context mContext;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private NewsFragment mNewsFragment;
    private ScheduleFragment mEventFragment;
    private TeamHomeFragment mTeamHomeFragment;
    private ScoreHomeFragment mScoreHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Beginning the transaction of the News Fragment.
        mNewsFragment = new NewsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.news_Fragment, mNewsFragment)
                .commit();

        //Beginning the Transaction of the Schedule Fragment
        mEventFragment = new ScheduleFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.event_fragment, mEventFragment)
                .commit();

        //Beginning the transaction of the Team fragment
        mTeamHomeFragment = new TeamHomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.team_home_Fragment, mTeamHomeFragment)
                .commit();

        //Beginning the transaction of the Score Fragemnt
        mScoreHomeFragment = new ScoreHomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.score_home_Fragment, mScoreHomeFragment)
                .commit();

        mLogOutBtn = (Button) findViewById(R.id.logOut_btn);
        mContext = this;
        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
