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
import com.timetable.activities.viewTimetableActivity.ViewTimetableActivity;

public class MainActivity extends AppCompatActivity {

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Settings clicked!", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, AddSubjectActivity.class));

        return true;
    }

    public void viewTimetable(View view) {
        startActivity(new Intent(this, ViewTimetableActivity.class));
    }

    public void viewEvents(View view) {
          startActivity(new Intent(this, ViewEventsActivity.class));
    }

    public void viewSettings(View view) {
          startActivity(new Intent(this, SettingsActivity.class));
    }
}