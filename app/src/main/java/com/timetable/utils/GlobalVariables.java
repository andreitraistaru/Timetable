package com.timetable.utils;

public class GlobalVariables {
    private static int numberOfDays = 7;
    private static String subjectIdExtra = "com.timetable.SUBJECT_ID";

    public static int getNumberOfDays() {
        return numberOfDays;
    }
    public static String getSubjectIdExtra() {
        return subjectIdExtra;
    }
}
