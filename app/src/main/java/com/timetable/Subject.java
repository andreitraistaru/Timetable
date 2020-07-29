package com.timetable;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;

public class Subject {
    private String name;
    private ArrayList<Date> time;
    private ArrayList<Date> timeEvenWeek;
    private String teacher;
    private String location;
    private String otherInfo;
    private Color color;
    private boolean differentOnEvenWeeks;

    public String getName() {
        return name;
    }
    public ArrayList<Date> getTime() {
        return time;
    }
    public ArrayList<Date> getTimeEvenWeek() {
        return timeEvenWeek;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getLocation() {
        return location;
    }
    public String getOtherInfo() {
        return otherInfo;
    }
    public Color getColor() {
        return color;
    }
    public boolean isDifferentOnEvenWeeks() {
        return differentOnEvenWeeks;
    }

    public Subject(String name) {
        this.name = name;

        time = new ArrayList<>();
        timeEvenWeek = null;
        teacher = Constants.UNDEFINED;
        location = Constants.UNDEFINED;
        otherInfo = Constants.UNDEFINED;
        differentOnEvenWeeks = false;
    }

    public void addTime(Date date) {
        time.add(date);
    }
    public void addTimeOnEvenWeeks(Date date) {
        if (timeEvenWeek != null) {
            timeEvenWeek.add(date);
        }
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void enableDifferentOnEvenWeeks() {
        if (!differentOnEvenWeeks) {
            differentOnEvenWeeks = true;

            timeEvenWeek = new ArrayList<Date>();
        }
    }
}
