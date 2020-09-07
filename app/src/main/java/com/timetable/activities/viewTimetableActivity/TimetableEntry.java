package com.timetable.activities.viewTimetableActivity;

import android.content.Context;

import com.timetable.R;
import com.timetable.utils.GlobalVariables;

import java.util.ArrayList;
import java.util.Collections;

public class TimetableEntry implements Comparable<TimetableEntry> {
    private boolean breakTime;
    private int start;
    private String name;
    private String type;
    private String location;
    private int end;
    private int color;
    private int duration;
    private Context context;

    public TimetableEntry(boolean breakTime, int start, String name, String type, String location, int end, int color, Context context) {
        this.breakTime = breakTime;
        this.start = start;
        this.name = name;
        this.type = type;
        this.location = location;
        this.end = end;
        this.color = color;
        this.duration = end - start;
        this.context = context;
    }

    public boolean isBreakTime() {
        return breakTime;
    }
    public int getStart() {
        return start;
    }
    public String getStartHour() {
        return context.getResources().getString(R.string.start_timetable_entry, start);
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
    public int getEnd() {
        return end;
    }
    public String getEndHour() {
        return context.getResources().getString(R.string.end_timetable_entry, end);
    }
    public int getColor() {
        return color;
    }
    public int getDuration() {
        return duration;
    }
    public Context getContext() {
        return context;
    }

    public void setBreakTime(boolean breakTime) {
        this.breakTime = breakTime;
    }
    public void setStart(int start) {
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
    public void setEnd(int end) {
        this.end = end;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int compareTo(TimetableEntry entry) {
        return start - entry.getStart();
    }

    public static void fillBreakTimes(ArrayList<TimetableEntry> timetable, Context context) {
        if (timetable.isEmpty()) {
            timetable.add(new TimetableEntry(true, GlobalVariables.getStartingHour(),
                    context.getString(R.string.break_time), "", "", GlobalVariables.getEndingHour(),
                    GlobalVariables.getBreakTimeColor(), context));

            return;
        }

        ArrayList<TimetableEntry> breaks = new ArrayList<>();

        Collections.sort(timetable);

        for (int i = 0; i < timetable.size() - 1; i++) {
            if (timetable.get(i).getEnd() < timetable.get(i + 1).getStart()) {
                breaks.add(new TimetableEntry(true, timetable.get(i).getEnd(),
                        context.getString(R.string.break_time), "", "", timetable.get(i + 1).getStart(),
                        GlobalVariables.getBreakTimeColor(), context));
            }
        }

        timetable.addAll(breaks);

        Collections.sort(timetable);
    }
}
