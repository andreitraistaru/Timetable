package com.timetable.database.subjects;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Insert
    void insert(Subject subject);

    @Query("SELECT * FROM subjects")
    List<Subject> getAll();
}
