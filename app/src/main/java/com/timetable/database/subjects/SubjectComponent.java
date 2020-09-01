package com.timetable.database.subjects;

import android.graphics.Color;

import java.util.ArrayList;

public class SubjectComponent {
    private SubjectComponentType type;
    private ArrayList<ClassInterval> intervals;
    private int color;
    private String teacher;
    private String location;
    private String description;

    public SubjectComponent(SubjectComponentType type, int color, String teacher, String location, String description) {
        this.type = type;
        this.intervals = new ArrayList<>();
        this.color = color;
        this.teacher = teacher;
        this.location = location;
        this.description = description;
    }

    public SubjectComponentType getType() {
        return type;
    }
    public ArrayList<ClassInterval> getIntervals() {
        return intervals;
    }
    public int getColor() {
        return color;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getLocation() {
        return location;
    }
    public String getDescription() {
        return description;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void addInterval(ClassInterval interval) {
        this.intervals.add(interval);
    }
}
