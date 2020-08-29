package com.timetable.database.holidays;

import androidx.room.TypeConverter;

import java.util.Date;

public class HolidayConverters {
    @TypeConverter
    public static Date toDate (String value) {
        return null;
    }

    @TypeConverter
    public static String fromDate(Date value) {
        return null;
    }
}
