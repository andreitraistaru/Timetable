package com.timetable.database.holidays;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HolidayConverters {
    @TypeConverter
    public static Date toDate (String string) {
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy", Locale.getDefault());

        try {
            return dateFormat.parse(string);
        } catch (ParseException e) {
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(Date date) {
        if (date == null) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy", Locale.getDefault());

        return dateFormat.format(date);
    }
}
