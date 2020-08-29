package com.timetable.database;

import java.util.ArrayList;
import java.util.Date;

public class SchoolYear {
    private int numberOfWeeks;
    private Date firstDay;
    private Date lastDay;
    private ArrayList<Holiday> holidays;

    public SchoolYear(int numberOfWeeks, Date firstDay, Date lastDay) {
        this.numberOfWeeks = numberOfWeeks;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.holidays = new ArrayList<>();
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks;
    }
    public Date getFirstDay() {
        return firstDay;
    }
    public Date getLastDay() {
        return lastDay;
    }
    public ArrayList<Holiday> getHolidays() {
        return holidays;
    }

    public void setNumberOfWeeks(int numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }
    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }
    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public void addHoliday(Holiday holiday) {
        this.holidays.add(holiday);
    }
}
