package com.timetable.database.holidays;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Holiday.class}, version = 1)
@TypeConverters({HolidayConverters.class})
public abstract class HolidaysDatabase extends RoomDatabase {
    private static HolidaysDatabase instance;

    public static HolidaysDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (HolidaysDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            HolidaysDatabase.class, "holidays_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return instance;
    }

    public abstract HolidayDAO getHolidayDao();
}
