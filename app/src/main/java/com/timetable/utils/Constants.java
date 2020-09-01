package com.timetable.utils;

import android.content.Context;

import com.timetable.R;

import java.util.Calendar;
import java.util.Date;

public class Constants {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    public static final int EVEN_ONLY = 0;
    public static final int ODD_ONLY = 1;
    public static final int BOTH = 2;

    public static final int SEMESTER_DURATION_DEFAULT = 14;

    public static int getSemesterStartDefault(int returnValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        switch (returnValue) {
            case 0:
                return calendar.get(Calendar.DAY_OF_MONTH);
            case 1:
                return calendar.get(Calendar.MONTH);
            case 2:
                return calendar.get(Calendar.YEAR);
            default:
                return 0;
        }
    }

    public static String getWeekDay(Context context, int day) {
        switch (day) {
            case MONDAY:
                return context.getResources().getString(R.string.monday);
            case TUESDAY:
                return context.getResources().getString(R.string.tuesday);
            case WEDNESDAY:
                return context.getResources().getString(R.string.wednesday);
            case THURSDAY:
                return context.getResources().getString(R.string.thursday);
            case FRIDAY:
                return context.getResources().getString(R.string.friday);
            case SATURDAY:
                return context.getResources().getString(R.string.saturday);
            case SUNDAY:
                return context.getResources().getString(R.string.sunday);
            default:
                return context.getResources().getString(R.string.nothing);
        }
    }

    public static String getFrequency(Context context, int frequency) {
        switch (frequency) {
            case EVEN_ONLY:
                return context.getResources().getString(R.string.even_only);
            case ODD_ONLY:
                return context.getResources().getString(R.string.odd_only);
            case BOTH:
                return context.getResources().getString(R.string.both);
            default:
                return context.getResources().getString(R.string.nothing);
        }
    }
}
