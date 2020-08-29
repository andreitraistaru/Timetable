package com.timetable.database.subjects;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Subject.class}, version = 1)
@TypeConverters({SubjectConverters.class})
public abstract class SubjectsDatabase extends RoomDatabase {
    public abstract SubjectDAO subjectDao();
}
