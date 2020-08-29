package com.timetable.database;

import androidx.room.TypeConverter;

import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<SubjectComponent> toSubjectComponents (String value) {
        return null;
    }

    @TypeConverter
    public static String fromSubjectComponents(ArrayList<SubjectComponent> value) {
        return null;
    }
}
