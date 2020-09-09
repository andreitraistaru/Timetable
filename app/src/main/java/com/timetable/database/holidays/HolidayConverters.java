package com.timetable.database.holidays;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HolidayConverters {
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
