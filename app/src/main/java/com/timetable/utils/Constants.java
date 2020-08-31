package com.timetable.utils;

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

    public static String getWeekDay(int day) {
        switch (day) {
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            case FRIDAY:
                return "Friday";
            case SATURDAY:
                return "Saturday";
            case SUNDAY:
                return "Sunday";
            default:
                return "";
        }
    }
}
