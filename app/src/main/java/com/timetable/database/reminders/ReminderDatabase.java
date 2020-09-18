package com.timetable.database.reminders;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Reminder.class}, version = 1)
@TypeConverters({ReminderConverters.class})
public abstract class ReminderDatabase extends RoomDatabase {
    public static ReminderDatabase instance = null;

    public static ReminderDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (ReminderDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ReminderDatabase.class, "reminders_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return instance;
    }

    public abstract ReminderDAO getRemainderDao();
}
