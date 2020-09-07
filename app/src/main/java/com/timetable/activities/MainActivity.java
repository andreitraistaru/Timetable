package com.timetable.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.timetable.R;
import com.timetable.activities.addSubjectActivity.AddSubjectActivity;
import com.timetable.activities.viewTimetableActivity.ViewTimetableActivity;
import com.timetable.activities.yearStructureActivity.YearStructureActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewTimetable(View view) {
        startActivity(new Intent(this, ViewTimetableActivity.class));
    }

    public void viewEvents(View view) {
          startActivity(new Intent(this, ViewEventsActivity.class));
    }

    public void viewSettings(View view) {
        startActivity(new Intent(this, YearStructureActivity.class));
    }
}