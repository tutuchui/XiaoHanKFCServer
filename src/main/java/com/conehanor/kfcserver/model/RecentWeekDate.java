package com.conehanor.kfcserver.model;

import java.util.Calendar;
import java.util.Date;

public class RecentWeekDate {

    Date[] recentWeek;

    public RecentWeekDate(){
        recentWeek = new Date[8];
        recentWeek[0] = new Date();
        for(int i = 1; i <= 7; i++){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -i);
            recentWeek[i] = c.getTime();
        }
    }

    public Date[] getRecentWeek() {
        return recentWeek;
    }
}
