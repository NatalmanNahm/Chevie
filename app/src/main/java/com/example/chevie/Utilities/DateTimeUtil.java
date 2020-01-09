package com.example.chevie.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Get Current date and time
 */
public class DateTimeUtil {

    /**
     * Method to get date format.
     * @param DateTime
     * @return
     */
    public String dateString(String DateTime){
        String [] separated = DateTime.split("T");
        String date = separated [0];
        return date;
    }

    /**
     * Method to get the current year.
     * @param date
     * @return
     */
    public String currentDate(String date){
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return currentDate;
    }

    /**
     * Method to get Time format
     * @param DateTime
     * @return
     */
    public String timeString(String DateTime){
        String [] separated = DateTime.split("T");
        String time = separated [1];

        String [] divide = time.split(":");
        String timeString = divide[0] + divide [1];
        return timeString;
    }

    /**
     * Method to get time for a match
     * @param timeString
     * @return
     */
    public String mathTime(String timeString){
        String time = timeString(timeString);

        // Convert time where time is like: 0100, 0200, 0300....2300...
        if (time.length() == 4 ) {
            String hour = time.substring(0,2);
            String minutes = time.substring(2,4);
            String meridian = "am";

            if (hour.substring(0,2).equals("00")) {
                hour = "12";
            } else if (hour.substring(0,1).equals("1") || hour.substring(0,1).equals("2")) {
                meridian = "pm";
                Integer militaryHour = Integer.parseInt(hour);
                Integer convertedHour = null;

                if (militaryHour > 12) {
                    convertedHour = (militaryHour - 12);

                    if (convertedHour < 10)
                        hour = "0" + String.valueOf(convertedHour);
                    else
                        hour = String.valueOf(convertedHour);
                }
            }

            time = hour + ":" + minutes + " " + meridian;
        }
        return time;
    }

}
