package com.timetable.activities.viewTimetableActivity;

import android.content.Context;

import com.timetable.R;
import com.timetable.database.subjects.ClassInterval;
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectComponent;
import com.timetable.utils.Constants;
import com.timetable.utils.GlobalVariables;

import java.util.ArrayList;
import java.util.Collections;

public class TimetableEntry implements Comparable<TimetableEntry> {
    private boolean breakTime;
    private Subject subject;
    private SubjectComponent component;
    private ClassInterval interval;
    private Context context;
    private int start;
    private int end;

    public TimetableEntry(Subject subject, SubjectComponent component, ClassInterval interval, Context context) {
        this.breakTime = false;
        this.subject = subject;
        this.component = component;
        this.interval = interval;
        this.context = context;
        this.start = interval.getStartingHour();
        this.end = interval.getEndingHour();
    }
    public TimetableEntry(int start, int end, Context context) {
        this.breakTime = true;
        this.subject = null;
        this.component = null;
        this.interval = null;
        this.context = context;
        this.start = start;
        this.end = end;
    }

    public boolean isBreakTime() {
        return breakTime;
    }
    public int getStart() {
        return start;
    }
    public String getStartHour() {
        return context.getResources().getString(R.string.start_timetable_entry, getStart());
    }
    public String getName() {
        if (breakTime) {
            return context.getString(R.string.break_time);
        }

        return subject.getName();
    }
    public String getType() {
        if (breakTime) {
            return "";
        }

        return Constants.getSubjectComponentType(context, component.getType());
    }
    public String getLocation() {
        if (breakTime) {
            return "";
        }

        return interval.getLocation();
    }
    public int getEnd() {
        return end;
    }
    public String getEndHour() {
        return context.getResources().getString(R.string.end_timetable_entry, getEnd());
    }
    public int getColor() {
        if (breakTime) {
            return GlobalVariables.getBreakTimeColor();
        }

        return component.getColor();
    }
    public int getDuration() {
        return getEnd() - getStart();
    }
    public Context getContext() {
        return context;
    }
    public String getSubjectDescription() {
        if (breakTime) {
            return "";
        }

        return subject.getDescription();
    }
    public String getTeacher() {
        if (breakTime) {
            return "";
        }

        return component.getTeacher();
    }
    public String getActivityDescription() {
        if (breakTime) {
            return "";
        }

        return component.getDescription();
    }
    public String getDay() {
        if (breakTime) {
            return "";
        }

        return Constants.getWeekDay(context, interval.getDay());
    }
    public String getFrequency() {
        if (breakTime) {
            return "";
        }

        return Constants.getFrequency(context, interval.getFrequency());
    }

    @Override
    public int compareTo(TimetableEntry entry) {
        return getStart() - entry.getStart();
    }

    public static void fillBreakTimes(ArrayList<TimetableEntry> timetable, Context context) {
        if (timetable.isEmpty()) {
            timetable.add(new TimetableEntry(GlobalVariables.getStartingHour(), GlobalVariables.getEndingHour(), context));

            return;
        }

        ArrayList<TimetableEntry> breaks = new ArrayList<>();

        Collections.sort(timetable);

        for (int i = 0; i < timetable.size() - 1; i++) {
            if (timetable.get(i).getEnd() < timetable.get(i + 1).getStart()) {
                breaks.add(new TimetableEntry(timetable.get(i).getEnd(), timetable.get(i + 1).getStart(), context));
            }
        }

        timetable.addAll(breaks);

        Collections.sort(timetable);
    }
}
