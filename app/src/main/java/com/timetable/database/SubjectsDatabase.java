package com.timetable.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Subject.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SubjectsDatabase extends RoomDatabase {
    public abstract SubjectDAO subjectDao();
}
