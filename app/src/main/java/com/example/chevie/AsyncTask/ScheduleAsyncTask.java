package com.example.chevie.AsyncTask;

import com.example.chevie.Models.EventHome;
import com.example.chevie.Models.Schedule;
import com.example.chevie.Models.TeamCard;
import com.example.chevie.Utilities.NetworkUtils;

import java.util.ArrayList;

public class ScheduleAsyncTask {

    public void doInBackgroundScheduleTask(String currentSeason, ArrayList<Schedule> schedules){
        ArrayList<EventHome> eventHomeArray = NetworkUtils.fetchEventData(currentSeason);

        //Going through the arraylist to get team name Then use that
        //To fetch team card for each event
        for (int i =0; i < eventHomeArray.size(); i++){
            EventHome eventHome = eventHomeArray.get(i);
            String teamOneName = eventHome.getmHomeTeam();
            String teamTwoName = eventHome.getmAwayTeam();

            ArrayList<TeamCard> teamCard = NetworkUtils.fetchTeamCard(teamOneName, teamTwoName);
            //Get all data for the home Team
            TeamCard team1 = teamCard.get(0);

            //Get all data for away Team
            TeamCard team2 = teamCard.get(1);

            schedules.add(new Schedule(eventHome, team1, team2));

        }
    }
}
