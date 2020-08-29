package com.timetable.database.subjects;

import androidx.room.TypeConverter;

import java.util.ArrayList;

public class SubjectConverters {
    @TypeConverter
    public static ArrayList<SubjectComponent> toArraySubjectComponents (String value) {
        return null;
    }

    @TypeConverter
    public static String fromArraySubjectComponents (ArrayList<SubjectComponent> value) {
        return null;
    }
}
