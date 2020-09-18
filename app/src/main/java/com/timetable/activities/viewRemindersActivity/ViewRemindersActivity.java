package com.timetable.activities.viewRemindersActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.reminders.Reminder;
import com.timetable.database.reminders.ReminderDatabase;

import java.util.List;

public class ViewRemindersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_remainders);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_view_todo_lists_activity);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView_activity_view_remainders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RemainderItemAdapter adapter = new RemainderItemAdapter(this);
        recyclerView.setAdapter(adapter);

        ReminderDatabase.getDatabase(this).getRemainderDao().getAllReminders().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                adapter.setReminders(reminders);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_reminders_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_reminder_view_reminder_list_activity:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                final View dialogView = LayoutInflater.from(ViewRemindersActivity.this).inflate(R.layout.dialog_add_reminder, viewGroup, false);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;
            default:
        }

        return true;
    }
}