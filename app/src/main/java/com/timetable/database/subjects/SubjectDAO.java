package com.timetable.database.subjects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Insert
    void insertSubject(Subject subject);

    @Query("SELECT * FROM subjects")
    LiveData<List<Subject>> getAllSubjects();
}
