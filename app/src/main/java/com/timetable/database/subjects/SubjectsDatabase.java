package com.timetable.database.subjects;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Subject.class}, version = 1)
@TypeConverters({SubjectConverters.class})
public abstract class SubjectsDatabase extends RoomDatabase {
    private static SubjectsDatabase instance;

    public static SubjectsDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (SubjectsDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            SubjectsDatabase.class, "students_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return instance;
    }

    public abstract SubjectDAO getSubjectDao();
}
