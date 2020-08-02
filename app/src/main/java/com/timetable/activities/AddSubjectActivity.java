package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.timetable.R;

public class AddSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        getSupportActionBar().setTitle(R.string.add_subject_activity_title);

        CheckBox lectures = findViewById(R.id.lecturesCheckBox_activity_add_subject);
        CheckBox seminars = findViewById(R.id.seminarsCheckBox_activity_add_subject);
        CheckBox laboratories = findViewById(R.id.laboratoriesCheckBox_activity_add_subject);
        CheckBox others = findViewById(R.id.othersCheckBox_activity_add_subject);

        showFieldsForAddingLectures(lectures);
        showFieldsForAddingSeminars(seminars);
        showFieldsForAddingLaboratories(laboratories);
        showFieldsForAddingOthers(others);
    }

    public void showFieldsForAddingLectures(View view) {
        Button button = findViewById(R.id.newClassLectures_activity_add_subject);
        SeekBar colorPicker = findViewById(R.id.colorPickerLectures_activity_add_subject);
        View color = findViewById(R.id.colorLectures_activity_add_subject);
        EditText teacher = findViewById(R.id.teacherNameLectures_activity_add_subject);
        EditText location = findViewById(R.id.locationLectures_activity_add_subject);
        EditText info = findViewById(R.id.infoLectures_activity_add_subject);

        if (!((CheckBox) view).isChecked()) {
            button.setVisibility(View.GONE);
            colorPicker.setVisibility(View.GONE);
            color.setVisibility(View.GONE);
            teacher.setVisibility(View.GONE);
            location.setVisibility(View.GONE);
            info.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
            colorPicker.setVisibility(View.VISIBLE);
            color.setVisibility(View.VISIBLE);
            teacher.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
        }
    }

    public void showFieldsForAddingSeminars(View view) {
        Button button = findViewById(R.id.newClassSeminars_activity_add_subject);
        SeekBar colorPicker = findViewById(R.id.colorPickerSeminars_activity_add_subject);
        View color = findViewById(R.id.colorSeminars_activity_add_subject);
        EditText teacher = findViewById(R.id.teacherNameSeminars_activity_add_subject);
        EditText location = findViewById(R.id.locationSeminars_activity_add_subject);
        EditText info = findViewById(R.id.infoSeminars_activity_add_subject);

        if (!((CheckBox) view).isChecked()) {
            button.setVisibility(View.GONE);
            colorPicker.setVisibility(View.GONE);
            color.setVisibility(View.GONE);
            teacher.setVisibility(View.GONE);
            location.setVisibility(View.GONE);
            info.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
            colorPicker.setVisibility(View.VISIBLE);
            color.setVisibility(View.VISIBLE);
            teacher.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
        }
    }

    public void showFieldsForAddingLaboratories(View view) {
        Button button = findViewById(R.id.newClassLaboratories_activity_add_subject);
        SeekBar colorPicker = findViewById(R.id.colorPickerLaboratories_activity_add_subject);
        View color = findViewById(R.id.colorLaboratories_activity_add_subject);
        EditText teacher = findViewById(R.id.teacherNameLaboratories_activity_add_subject);
        EditText location = findViewById(R.id.locationLaboratories_activity_add_subject);
        EditText info = findViewById(R.id.infoLaboratories_activity_add_subject);

        if (!((CheckBox) view).isChecked()) {
            button.setVisibility(View.GONE);
            colorPicker.setVisibility(View.GONE);
            color.setVisibility(View.GONE);
            teacher.setVisibility(View.GONE);
            location.setVisibility(View.GONE);
            info.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
            colorPicker.setVisibility(View.VISIBLE);
            color.setVisibility(View.VISIBLE);
            teacher.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
        }
    }

    public void showFieldsForAddingOthers(View view) {
        Button button = findViewById(R.id.newClassOthers_activity_add_subject);
        SeekBar colorPicker = findViewById(R.id.colorPickerOthers_activity_add_subject);
        View color = findViewById(R.id.colorOthers_activity_add_subject);
        EditText teacher = findViewById(R.id.teacherNameOthers_activity_add_subject);
        EditText location = findViewById(R.id.locationOthers_activity_add_subject);
        EditText info = findViewById(R.id.infoOthers_activity_add_subject);

        if (!((CheckBox) view).isChecked()) {
            button.setVisibility(View.GONE);
            colorPicker.setVisibility(View.GONE);
            color.setVisibility(View.GONE);
            teacher.setVisibility(View.GONE);
            location.setVisibility(View.GONE);
            info.setVisibility(View.GONE);
        } else {
            button.setVisibility(View.VISIBLE);
            colorPicker.setVisibility(View.VISIBLE);
            color.setVisibility(View.VISIBLE);
            teacher.setVisibility(View.VISIBLE);
            location.setVisibility(View.VISIBLE);
            info.setVisibility(View.VISIBLE);
        }
    }

    public void showNewClassDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_add_class, viewGroup, false);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        SeekBar startHour = dialogView.findViewById(R.id.startHour_dialog_add_class);
        SeekBar finishHour = dialogView.findViewById(R.id.finishHour_dialog_add_class);

        startHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView startHour = dialogView.findViewById(R.id.chooseStartHour_dialog_add_class);

                startHour.setText(getResources().getString(R.string.start_hour_dialog_add_class) + " " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        finishHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView finishHour = dialogView.findViewById(R.id.chooseFinishHour_dialog_add_class);

                finishHour.setText(getResources().getString(R.string.finish_hour_dialog_add_class) + " " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}