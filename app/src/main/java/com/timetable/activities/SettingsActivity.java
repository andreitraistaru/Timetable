package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.timetable.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle(R.string.settings_activity_title);
    }
}