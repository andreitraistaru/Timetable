package com.timetable.database.holidays;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "holidays")
public class Holiday {
    @PrimaryKey (autoGenerate = true)
    private int id;

    private boolean workingWeek;
    private Date firstDay;
    private Date lastDay;

    public Holiday() {}
    public Holiday(boolean workingWeek, Date firstDay, Date lastDay) {
        this.workingWeek = workingWeek;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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

    public boolean isValid() {
        return firstDay.before(lastDay);
    }
}
