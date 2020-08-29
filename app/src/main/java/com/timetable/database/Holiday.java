package com.timetable.database;

import java.util.Date;

public class Holiday {
    private boolean workingWeek;
    private Date firstDay;
    private Date lastDay;

    public Holiday(boolean workingWeek, Date firstDay, Date lastDay) {
        this.workingWeek = workingWeek;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public boolean isWorkingWeek() {
        return workingWeek;
    }

    public Date getFirstDay() {
        return firstDay;
    }
    public Date getLastDay() {
        return lastDay;
    }

    public void setWorkingWeek(boolean workingWeek) {
        this.workingWeek = workingWeek;
    }
    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }
    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }
}
