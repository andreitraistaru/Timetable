package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.timetable.R;

public class AddSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        getSupportActionBar().setTitle(R.string.add_subject_activity_title);
    }
}