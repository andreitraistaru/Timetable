package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timetable.R;

public class YearStructureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_structure);

        getSupportActionBar().setTitle(R.string.year_structure_activity_title);
    }

    public void changeFirstDayOfSemester(View view) {

    }

    public void changeLastDayOfSemester(View view) {

    }

    public void addSubject(View view) {
        startActivity(new Intent(this, AddSubjectActivity.class));
    }

    public void addHoliday(View view) {

    }
}