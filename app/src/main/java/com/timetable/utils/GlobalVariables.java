package com.timetable.utils;

import android.graphics.Color;

public class GlobalVariables {
    private static int numberOfDays = 7;
    private static String subjectIdExtra = "com.timetable.SUBJECT_ID";
    private static int startingHour = 8;
    private static int endingHour = 20;
    private static int breakTimeColor = Color.LTGRAY;
    private static int holidayTimeColor = Color.GREEN;

    public static int getNumberOfDays() {
        return numberOfDays;
    }
    public static String getSubjectIdExtra() {
        return subjectIdExtra;
    }
    public static int getStartingHour() {
        return startingHour;
    }
    public static int getEndingHour() {
        return endingHour;
    }
    public static int getBreakTimeColor() {
        return breakTimeColor;
    }
    public static int getHolidayTimeColor() {
        return holidayTimeColor;
    }
}
