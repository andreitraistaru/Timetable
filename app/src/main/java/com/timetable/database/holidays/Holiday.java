package com.timetable.database.holidays;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.timetable.R;
import com.timetable.utils.Constants;

import java.util.Calendar;
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

    public boolean isPastHoliday(Calendar date) {
        Calendar holidayEnd = Calendar.getInstance();
        holidayEnd.setTime(getLastDay());

        return holidayEnd.before(date);
    }

    public boolean isHolidayAtDate(Calendar date) {
        Calendar holidayStart = Calendar.getInstance();
        holidayStart.setTime(getFirstDay());

        Calendar holidayEnd = Calendar.getInstance();
        holidayEnd.setTime(getLastDay());

        if (holidayStart.before(date) && holidayEnd.after(date)) {
            return true;
        }

        if ((holidayStart.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) &&
                (holidayStart.get(Calendar.MONTH) == date.get(Calendar.MONTH)) &&
                (holidayStart.get(Calendar.YEAR) == date.get(Calendar.YEAR))) {
            return true;
        } else return (holidayEnd.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) &&
                (holidayEnd.get(Calendar.MONTH) == date.get(Calendar.MONTH)) &&
                (holidayEnd.get(Calendar.YEAR) == date.get(Calendar.YEAR));

    }

    public int getDurationInWeeksAfterDate(Calendar date) {
        Calendar startDate = Calendar.getInstance();

        if (getFirstDay().before(date.getTime())) {
            startDate.setTime(date.getTime());
        } else {
            startDate.setTime(getFirstDay());
        }

        Calendar holidayEnd = Calendar.getInstance();
        holidayEnd.setTime(getLastDay());

        int duration = 0;

        while (startDate.before(holidayEnd)) {
            startDate.add(Calendar.WEEK_OF_YEAR, 1);

            duration++;
        }

        return duration;
    }
}
