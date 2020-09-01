package com.timetable.database.subjects;

public class ClassInterval {
    private int day;
    private int startingHour;
    private int endingHour;
    private int frequency;

    public ClassInterval(int day, int startingHour, int endingHour, int frequency) {
        this.day = day;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.frequency = frequency;
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
