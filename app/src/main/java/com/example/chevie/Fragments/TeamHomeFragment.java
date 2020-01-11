package com.example.chevie.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chevie.R;

/**
 * A simple {@link Fragment} subclass.
 * This Fragment contains all code to populate the UI
 * as one team card to be shown on the homepage.
 */
public class TeamHomeFragment extends Fragment {

    public TeamHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_home, container, false);
    }

}
