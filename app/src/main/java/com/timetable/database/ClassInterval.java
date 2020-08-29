package com.timetable.database;

public class ClassInterval {
    private WeekDay day;
    private int startingHour;
    private int endingHour;
    private ClassFrequency frequency;

    public void ClassFrequency(WeekDay day, int startingHour, int endingHour, ClassFrequency frequency) {
        this.day = day;
        this.startingHour = startingHour;
        this.endingHour = endingHour;
        this.frequency = frequency;
    }

    public WeekDay getDay() {
        return day;
    }
    public int getStartingHour() {
        return startingHour;
    }
    public int getEndingHour() {
        return endingHour;
    }
    public ClassFrequency getFrequency() {
        return frequency;
    }

    public void setDay(WeekDay day) {
        this.day = day;
    }
    public void setStartingHour(int startingHour) {
        this.startingHour = startingHour;
    }
    public void setEndingHour(int endingHour) {
        this.endingHour = endingHour;
    }
    public void setFrequency(ClassFrequency frequency) {
        this.frequency = frequency;
    }
}
