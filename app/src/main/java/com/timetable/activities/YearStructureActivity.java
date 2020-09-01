package com.timetable.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.timetable.R;
import com.timetable.activities.addSubjectActivity.AddSubjectActivity;
import com.timetable.utils.Constants;

import java.util.Calendar;

public class YearStructureActivity extends AppCompatActivity {

    private final String sharedPreferenceName = "YearStructureSharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_structure);

        getSupportActionBar().setTitle(R.string.year_structure_activity_title);

        final SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferenceName, MODE_PRIVATE);
        final EditText semesterDuration = findViewById(R.id.semesterDuration_year_structure_activity);
        TextView semesterStart = findViewById(R.id.startingDateInfo_year_structure_activity);

        semesterDuration.setText(String.valueOf(sharedPreferences.getInt("semester_duration", Constants.SEMESTER_DURATION_DEFAULT)));

        semesterDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (!semesterDuration.getText().toString().isEmpty()) {
                    sharedPreferences.edit().putInt("semester_duration", Integer.parseInt(semesterDuration.getText().toString())).apply();                } else {
                    sharedPreferences.edit().putInt("semester_duration", Constants.SEMESTER_DURATION_DEFAULT).apply();
                }

                updateSemesterEnd();
            }
        });

        semesterStart.setText(getResources().getString(R.string.starting_date_year_structure,
                sharedPreferences.getInt("semester_start_day", Constants.getSemesterStartDefault(0)),
                sharedPreferences.getInt("semester_start_month", Constants.getSemesterStartDefault(1)) + 1,
                sharedPreferences.getInt("semester_start_year", Constants.getSemesterStartDefault(2))));
        updateSemesterEnd();
    }

    public void chooseSemesterStart(View view) {
        final TextView semesterBegin = findViewById(R.id.startingDateInfo_year_structure_activity);

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
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                semesterBegin.setText(getResources().getString(R.string.starting_date_year_structure,
                        selectedDate.get(Calendar.DAY_OF_MONTH), selectedDate.get(Calendar.MONTH) + 1, selectedDate.get(Calendar.YEAR)));

                SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferenceName, MODE_PRIVATE);
                sharedPreferences.edit().putInt("semester_start_day", selectedDate.get(Calendar.DAY_OF_MONTH))
                                        .putInt("semester_start_month", selectedDate.get(Calendar.MONTH))
                                        .putInt("semester_start_year", selectedDate.get(Calendar.YEAR)).apply();

                updateSemesterEnd();

                alertDialog.dismiss();
            }
        });
    }

    private void updateSemesterEnd() {
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferenceName, MODE_PRIVATE);
        TextView semesterEnd = findViewById(R.id.endingDateInfo_year_structure_activity);
        Calendar calendar = Calendar.getInstance();

        calendar.set(sharedPreferences.getInt("semester_start_year", Constants.getSemesterStartDefault(2)),
                        sharedPreferences.getInt("semester_start_month", Constants.getSemesterStartDefault(1)),
                        sharedPreferences.getInt("semester_start_day", Constants.getSemesterStartDefault(0)));

        calendar.add(Calendar.WEEK_OF_YEAR, sharedPreferences.getInt("semester_duration", Constants.SEMESTER_DURATION_DEFAULT));
        calendar.add(Calendar.DATE, -1);

        semesterEnd.setText(getResources().getString(R.string.ending_date_year_structure, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
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