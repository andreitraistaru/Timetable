package com.timetable.activities.viewTimetableActivity;

public class TimetableEntry {
    private String start;
    private String name;
    private String type;
    private String location;
    private String end;
    private int color;

    public TimetableEntry() {}
    public TimetableEntry(String start, String name, String type, String location, String end, int color) {
        this.start = start;
        this.name = name;
        this.type = type;
        this.location = location;
        this.end = end;
        this.color = color;
    }

    public String getStart() {
        return start;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getLocation() {
        return location;
    }
    public String getEnd() {
        return end;
    }
    public int getColor() {
        return color;
    }

    public void setStart(String start) {
        this.start = start;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
