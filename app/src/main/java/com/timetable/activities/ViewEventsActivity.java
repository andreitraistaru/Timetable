package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.timetable.R;

public class ViewEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.events_activity_title);
        }
    }
}