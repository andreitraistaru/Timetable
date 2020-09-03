package com.timetable.database.subjects;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SubjectConverters {
    @TypeConverter
    public static ArrayList<SubjectComponent> toArraySubjectComponents (String string) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SubjectComponent>>(){}.getType();

        return gson.fromJson(string, type);
    }

    @TypeConverter
    public static String fromArraySubjectComponents (ArrayList<SubjectComponent> subjectComponents) {
        Gson gson = new Gson();

        return gson.toJson(subjectComponents);
    }
}
