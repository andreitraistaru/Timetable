package com.timetable.database.holidays;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HolidayDAO {
    @Insert
    void insertHoliday(Holiday holiday);

    @Query("SELECT * FROM holidays")
    LiveData<List<Holiday>> getAllHolidays();
}
