package com.timetable.database.holidays;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Holiday.class}, version = 1)
@TypeConverters({HolidayConverters.class})
public abstract class HolidaysDatabase extends RoomDatabase {
    public abstract HolidayDAO holidayDAO();
}
