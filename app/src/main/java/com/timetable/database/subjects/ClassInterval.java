package com.timetable.database.subjects;

public class ClassInterval {
    private String location;
    private int day;
    private int startingHour;
    private int endingHour;
    private int frequency;

    public ClassInterval(String location, int day, int startingHour, int endingHour, int frequency) {
        this.location = location;
        this.day = day;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.frequency = frequency;
    }

    public String getLocation() {
        return location;
    }
    public int getDay() {
        return day;
    }
    public int getStartingHour() {
        return startingHour;
    }
    public int getEndingHour() {
        return endingHour;
    }
    public int getFrequency() {
        return frequency;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }
    public void setEndingHour(int endingHour) {
        this.endingHour = endingHour;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
