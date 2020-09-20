package com.timetable.activities.viewRemindersActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.timetable.R;
import com.timetable.database.reminders.Reminder;
import com.timetable.database.reminders.ReminderDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewRemindersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_remainders);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_view_reminders_lists_activity);
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
        if (item.getItemId() == R.id.new_reminder_view_reminder_list_activity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            final View dialogView = LayoutInflater.from(ViewRemindersActivity.this).inflate(R.layout.dialog_add_reminder, viewGroup, false);

            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            final Date deadline = new Date();

            (dialogView.findViewById(R.id.deadlineInfo_dialog_add_reminder)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox checkBox = (CheckBox) view;

                    if (checkBox.isChecked()) {
                        dialogView.findViewById(R.id.deadline_dialog_add_reminder).setVisibility(View.VISIBLE);
                        dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setVisibility(View.VISIBLE);
                    } else {
                        dialogView.findViewById(R.id.deadline_dialog_add_reminder).setVisibility(View.GONE);
                        dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setVisibility(View.GONE);
                    }
                }
            });

            dialogView.findViewById(R.id.changeDeadline_dialog_add_reminder).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    final View dateTimeDialogView = LayoutInflater.from(ViewRemindersActivity.this).inflate(R.layout.dialog_date_time, viewGroup, false);

                    builder.setView(dateTimeDialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(((CalendarView) dateTimeDialogView.findViewById(R.id.calendarView_dialog_date_time)).getDate());
                    calendar.set(Calendar.HOUR_OF_DAY, ((TimePicker) dateTimeDialogView.findViewById(R.id.timePicker_dialog_date_time)).getHour());
                    calendar.set(Calendar.MINUTE, ((TimePicker) dateTimeDialogView.findViewById(R.id.timePicker_dialog_date_time)).getMinute());

                    ((CalendarView) dateTimeDialogView.findViewById(R.id.calendarView_dialog_date_time)).setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DAY_OF_MONTH, day);

                            ((TextView) dialogView.findViewById(R.id.deadline_dialog_add_reminder)).setText(getString(R.string.deadline_dialog_add_reminder, calendar.get(Calendar.DAY_OF_MONTH),
                                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
                        }
                    });

                    ((TimePicker) dateTimeDialogView.findViewById(R.id.timePicker_dialog_date_time)).setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                        @Override
                        public void onTimeChanged(TimePicker timePicker, int hour, int minutes) {
                            calendar.set(Calendar.HOUR_OF_DAY, hour);
                            calendar.set(Calendar.MINUTE, minutes);

                            ((TextView) dialogView.findViewById(R.id.deadline_dialog_add_reminder)).setText(getString(R.string.deadline_dialog_add_reminder, calendar.get(Calendar.DAY_OF_MONTH),
                                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
                        }
                    });

                    dateTimeDialogView.findViewById(R.id.choose_dialog_date_time).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deadline.setTime(calendar.getTimeInMillis());
                            alertDialog.dismiss();
                        }
                    });
                }
            });

            (dialogView.findViewById(R.id.save_dialog_add_reminder)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = ((TextView) dialogView.findViewById(R.id.title_dialog_add_reminder)).getText().toString();

                    if (title.isEmpty()) {
                        ((TextView) dialogView.findViewById(R.id.title_dialog_add_reminder)).setError(getString(R.string.no_title_error_dialog_add_reminder));
                        dialogView.findViewById(R.id.title_dialog_add_reminder).requestFocus();

                        return;
                    }

                    final Reminder newReminder = new Reminder();

                    newReminder.setTitle(title);
                    newReminder.setColor(((ColorSeekBar) dialogView.findViewById(R.id.color_dialog_add_reminder)).getColor());

                    if (((CheckBox) dialogView.findViewById(R.id.deadlineInfo_dialog_add_reminder)).isChecked()) {
                        if (((TextView) dialogView.findViewById(R.id.deadline_dialog_add_reminder)).getText().toString().equals(getString(R.string.deadline_default_dialog_add_reminder))) {
                            Toast.makeText(ViewRemindersActivity.this, getString(R.string.no_deadline_error_dialog_add_reminder), Toast.LENGTH_SHORT).show();

                            return;
                        }

                        newReminder.setDeadline(deadline);
                    } else {
                        newReminder.setDeadline(null);
                    }

                    newReminder.setDetails(((TextView) dialogView.findViewById(R.id.details_dialog_add_reminder)).getText().toString());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ReminderDatabase.getDatabase(getApplicationContext()).getRemainderDao().insertReminder(newReminder);
                        }
                    }).start();

                    alertDialog.dismiss();
                }
            });
        }

        return true;
    }
}