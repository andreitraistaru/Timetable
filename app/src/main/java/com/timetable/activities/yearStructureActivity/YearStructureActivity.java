package com.timetable.activities.yearStructureActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.activities.addSubjectActivity.AddSubjectActivity;
import com.timetable.database.holidays.Holiday;
import com.timetable.database.holidays.HolidaysDatabase;
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectsDatabase;
import com.timetable.utils.Constants;

import java.util.Calendar;
import java.util.List;

public class YearStructureActivity extends AppCompatActivity {
    private Holiday newHoliday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_structure);

        newHoliday = null;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.year_structure_activity_title);
        }

        final SharedPreferences sharedPreferences = getSharedPreferences(Constants.getYearStructureSharedPreferenceName(), MODE_PRIVATE);
        TextView semesterStart = findViewById(R.id.startingDateInfo_year_structure_activity);

        semesterStart.setText(getResources().getString(R.string.starting_date_year_structure,
                sharedPreferences.getInt("semester_start_day", Constants.getSemesterStartDefault(0)),
                sharedPreferences.getInt("semester_start_month", Constants.getSemesterStartDefault(1)) + 1,
                sharedPreferences.getInt("semester_start_year", Constants.getSemesterStartDefault(2))));

        RecyclerView subjects = findViewById(R.id.subjects_year_structure_activity);
        subjects.setLayoutManager(new LinearLayoutManager(this));
        final SubjectItemAdapter subjectAdapter = new SubjectItemAdapter(this);
        subjects.setAdapter(subjectAdapter);

        SubjectsDatabase.getDatabase(this).getSubjectDao().getAllSubjects().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                subjectAdapter.setSubjects(subjects);
            }
        });

        RecyclerView holidays = findViewById(R.id.holiday_year_structure_activity);
        holidays.setLayoutManager(new LinearLayoutManager(this));
        final HolidayItemAdapter holidayAdapter = new HolidayItemAdapter(this);
        holidays.setAdapter(holidayAdapter);

        HolidaysDatabase.getDatabase(this).getHolidayDao().getAllHolidays().observe(this, new Observer<List<Holiday>>() {
            @Override
            public void onChanged(List<Holiday> holidays) {
                holidayAdapter.setHolidays(holidays);
            }
        });
    }

    public void chooseSemesterStart(View view) {
        final TextView semesterBegin = findViewById(R.id.startingDateInfo_year_structure_activity);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final CalendarView calendar = dialogView.findViewById(R.id.calendarView_dialog_date_time);
        final Calendar selectedDate = Calendar.getInstance();

        while (selectedDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            selectedDate.add(Calendar.DATE, 1);
        }

        calendar.setDate(selectedDate.getTimeInMillis());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, day);

                if (newDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                    selectedDate.set(year, month, day);
                } else {
                    calendar.setDate(selectedDate.getTimeInMillis());
                    Toast.makeText(YearStructureActivity.this, getResources().getString(R.string.error_not_monday_starting_date_year_structure), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button chooseDate = dialogView.findViewById(R.id.choose_dialog_date_time);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semesterBegin.setText(getResources().getString(R.string.starting_date_year_structure,
                        selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

                SharedPreferences sharedPreferences = getSharedPreferences(Constants.getYearStructureSharedPreferenceName(), MODE_PRIVATE);
                sharedPreferences.edit().putInt("semester_start_day", selectedDate.get(Calendar.DAY_OF_MONTH))
                                        .putInt("semester_start_month", selectedDate.get(Calendar.MONTH))
                                        .putInt("semester_start_year", selectedDate.get(Calendar.YEAR)).apply();

                alertDialog.dismiss();
            }
        });
    }

    public void addSubject(View view) {
        startActivity(new Intent(this, AddSubjectActivity.class));
    }

    public void addHoliday(View view) {
        newHoliday = new Holiday();
        newHoliday.setWorkingWeek(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(YearStructureActivity.this).inflate(R.layout.dialog_add_holiday, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        ((TextView) dialogView.findViewById(R.id.chooseHolidayStart_dialog_add_holiday)).setText(getResources().getString(R.string.start_day_dialog_add_holiday, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
        newHoliday.setFirstDay(calendar.getTime());

        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        ((TextView) dialogView.findViewById(R.id.chooseHolidayEnd_dialog_add_holiday)).setText(getResources().getString(R.string.end_day_dialog_add_holiday, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
        newHoliday.setLastDay(calendar.getTime());

        Button save = dialogView.findViewById(R.id.save_dialog_add_holiday);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newHoliday.isValid()) {
                    Toast.makeText(dialogView.getContext(), getResources().getString(R.string.wrong_starting_ending_dates_error), Toast.LENGTH_SHORT).show();

                    return;
                }

                newHoliday.setWorkingWeek(((CheckBox) dialogView.findViewById(R.id.working_week_dialog_add_holiday)).isChecked());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HolidaysDatabase.getDatabase(getApplicationContext()).getHolidayDao().insertHoliday(newHoliday);
                        newHoliday = null;
                    }
                }).start();

                alertDialog.dismiss();
            }
        });
    }

    public void chooseHolidayPeriod(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final TextView holidayBegin = view.getRootView().findViewById(R.id.chooseHolidayStart_dialog_add_holiday);
        final TextView holidayEnd = view.getRootView().findViewById(R.id.chooseHolidayEnd_dialog_add_holiday);

        final CalendarView calendar = dialogView.findViewById(R.id.calendarView_dialog_date_time);
        final Calendar selectedDate = Calendar.getInstance();

        if (view.getId() == R.id.startingDate_dialog_add_holiday) {
            while (selectedDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                selectedDate.add(Calendar.DATE, 1);
            }

            calendar.setDate(selectedDate.getTimeInMillis());

            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, month, day);

                    if (newDate.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                        selectedDate.set(year, month, day);
                    } else {
                        calendar.setDate(selectedDate.getTimeInMillis());
                        Toast.makeText(YearStructureActivity.this, getResources().getString(R.string.error_not_monday_add_holiday), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (view.getId() == R.id.endingDate_dialog_add_holiday) {
            while (selectedDate.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                selectedDate.add(Calendar.DATE, 1);
            }

            while (selectedDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                selectedDate.add(Calendar.DATE, 1);
            }

            calendar.setDate(selectedDate.getTimeInMillis());

            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, month, day);

                    if (newDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        selectedDate.set(year, month, day);
                    } else {
                        calendar.setDate(selectedDate.getTimeInMillis());
                        Toast.makeText(YearStructureActivity.this, getResources().getString(R.string.error_not_monday_add_holiday), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        Button chooseDate = dialogView.findViewById(R.id.choose_dialog_date_time);
        switch (view.getId()) {
            case R.id.startingDate_dialog_add_holiday:
                chooseDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holidayBegin.setText(getResources().getString(R.string.start_day_dialog_add_holiday,
                                selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

                        newHoliday.setFirstDay(selectedDate.getTime());

                        alertDialog.dismiss();
                    }
                });

                break;
            case R.id.endingDate_dialog_add_holiday:
                chooseDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holidayEnd.setText(getResources().getString(R.string.end_day_dialog_add_holiday,
                                selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

                        newHoliday.setLastDay(selectedDate.getTime());

                        alertDialog.dismiss();
                    }
                });

                break;
        }
    }
}