package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.timetable.R;

public class YearStructureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_structure);

        getSupportActionBar().setTitle(R.string.year_structure_activity_title);
    }

    public void changeFirstDayOfSemester(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void changeLastDayOfSemester(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    public void chooseFirstDayOfHoliday(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button chooseDate = dialogView.findViewById(R.id.choose_date_dialog_calendar);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dialogView.getContext(), "First!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void chooseLastDayOfHoliday(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_calendar, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button chooseDate = dialogView.findViewById(R.id.choose_date_dialog_calendar);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dialogView.getContext(), "Second!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
}