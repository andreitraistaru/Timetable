package com.timetable.database.holidays;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.timetable.database.subjects.Subject;

import java.util.List;

@Dao
public interface HolidayDAO {
    @Insert
    void insert(Holiday holiday);

    @Query("SELECT * FROM holidays")
    List<Holiday> getAll();
}
