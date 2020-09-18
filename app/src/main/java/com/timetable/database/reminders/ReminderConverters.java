package com.timetable.database.reminders;

import androidx.room.TypeConverter;

import java.util.Date;

public class ReminderConverters {
    @TypeConverter
    public static Date toDate (long data) {
        if (data == -1) {
            return null;
        }

        return new Date(data);
    }

    @TypeConverter
    public static long fromDate(Date data) {
        if (data == null) {
            return -1;
        }

        return data.getTime();
    }
}
