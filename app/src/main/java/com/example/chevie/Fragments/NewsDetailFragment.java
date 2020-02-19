package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chevie.Models.News;
import com.example.chevie.R;
import com.example.chevie.Utilities.SvgLoaderUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
 *Fragment that display a news detail a the time
 */
public class NewsDetailFragment extends Fragment {

    //Initializer
    private View mRootView;
    private TextView mTitle;
    private ImageView mPlayerPic;
    private TextView mPlayerName;
    private TextView mTimeShared;
    private TextView mNewsSource;
    private TextView mContent;
    private FloatingActionButton mSaveImageView;
    private ImageView mSaved;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private String mUserId;

    private ArrayList<News> mNews = new ArrayList<>();
    private int mPosition;
    private Context mContext;
    private static final String TAG = NewsDetailFragment.class.getSimpleName();

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting values parse to the fragment
        Bundle detailBundle = this.getArguments();

        if (detailBundle != null){
            mPosition = detailBundle.getInt("page");
            mNews = detailBundle.getParcelableArrayList("news");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_news_detail, container, false);
        mContext = mRootView.getContext();

        //bind Views
        mTitle = (TextView) mRootView.findViewById(R.id.news_detail_title);
        mPlayerPic = (ImageView) mRootView.findViewById(R.id.detail_news_player_img);
        mPlayerName = (TextView) mRootView.findViewById(R.id.news_detail_name);
        mTimeShared = (TextView) mRootView.findViewById(R.id.news_detail_time);
        mNewsSource = (TextView) mRootView.findViewById(R.id.news_detail_source);
        mContent = (TextView) mRootView.findViewById(R.id.news_detail_content);
        mSaveImageView = (FloatingActionButton) mRootView.findViewById(R.id.save_news);
        mSaved = (ImageView) mRootView.findViewById(R.id.saved);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        mUserId = user.getUid();
        final DatabaseReference newsSavedRef =
                mDatabaseRef.child("Users").child(mUserId).child("News");

        //Getting current data
        final News news = mNews.get(mPosition);
        String title = news.getmTitle();
        final int newsId = news.getmNewsId();
        String playerPic = news.getmPlayerPic();
        String name = news.getmPlayerShortName();
        String time = news.getmTimeShared();
        String source = news.getmSource();
        String content = news.getmContent();

        //Binding views to their current data
        mTitle.setText(title);
        mPlayerName.setText(name);
        mTimeShared.setText(time);
        mNewsSource.setText(source);
        mContent.setText(content);
        Picasso.get().load(playerPic).into(mPlayerPic);


        //Add news to the database only if the data is not in the database
        mSaveImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                newsSavedRef.orderByChild("mNewsId").equalTo(newsId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (!dataSnapshot.exists()) {
                            newsSavedRef.push().setValue(news);
                            mSaved.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(mContext, "News is already saved"
                                    , Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "loadUser:onCancelled", databaseError.toException());
                    }
                });
            }
        });


        //Checking the news if it is already saved inside the database
        newsSavedRef.orderByChild("mNewsId").equalTo(newsId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    mSaved.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadUser:onCancelled", databaseError.toException());
            }
        });


        return mRootView;
    }

}
