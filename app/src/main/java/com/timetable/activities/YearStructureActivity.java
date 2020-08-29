package com.timetable.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.timetable.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class YearStructureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_structure);

        getSupportActionBar().setTitle(R.string.year_structure_activity_title);
    }

    public void chooseSemesterPeriod(View view) {
        final TextView semesterBegin = findViewById(R.id.startingDateInfo_year_structure_activity);
        final TextView semesterEnd = findViewById(R.id.endingDateInfo_year_structure_activity);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final CalendarView calendar = dialogView.findViewById(R.id.calendarView_dialog_calendar);
        final Calendar selectedDate = Calendar.getInstance();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedDate.set(year, month, day);
            }
        });

        Button chooseDate = dialogView.findViewById(R.id.choose_date_dialog_calendar);

        switch (view.getId()) {
            case R.id.startingDate_year_structure_activity:
                chooseDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        semesterBegin.setText(getResources().getString(R.string.starting_date_year_structure,
                                selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

                        alertDialog.dismiss();
                    }
                });

                break;
            case R.id.endingDate_year_structure_activity:
                chooseDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        semesterEnd.setText(getResources().getString(R.string.ending_date_year_structure,
                                selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

                        alertDialog.dismiss();
                    }
                });

                break;
        }
    }

    public void addSubject(View view) {
        startActivity(new Intent(this, AddSubjectActivity.class));
    }

    public void addHoliday(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_add_holiday, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button save = dialogView.findViewById(R.id.save_dialog_add_holiday);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dialogView.getContext(), "Saved!", Toast.LENGTH_SHORT).show();
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

        final CalendarView calendar = dialogView.findViewById(R.id.calendarView_dialog_calendar);
        final Calendar selectedDate = Calendar.getInstance();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedDate.set(year, month, day);
            }
        });

        Button chooseDate = dialogView.findViewById(R.id.choose_date_dialog_calendar);
        switch (view.getId()) {
            case R.id.startingDate_dialog_add_holiday:
                chooseDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holidayBegin.setText(getResources().getString(R.string.start_day_dialog_add_holiday,
                                selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

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

                        alertDialog.dismiss();
                    }
                });

                break;
        }
    }
}