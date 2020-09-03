package com.timetable.database.subjects;

import java.util.ArrayList;

public class SubjectComponent {
    private SubjectComponentType type;
    private ArrayList<ClassInterval> intervals;
    private int color;
    private String teacher;
    private String description;

    public SubjectComponent(SubjectComponentType type, ArrayList<ClassInterval> intervals, int color, String teacher, String description) {
        this.type = type;
        this.intervals = intervals;
        this.color = color;
        this.teacher = teacher;
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
    public String getDescription() {
        return description;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void addInterval(ClassInterval interval) {
        this.intervals.add(interval);
    }
}
