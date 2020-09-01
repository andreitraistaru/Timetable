package com.timetable.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
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
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectComponent;
import com.timetable.database.subjects.SubjectComponentType;

import java.util.ArrayList;

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
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView startHourTextView = dialogView.findViewById(R.id.chooseHolidayStart_dialog_add_holiday);
        TextView finishHourTextView = dialogView.findViewById(R.id.chooseHolidayEnd_dialog_add_holiday);

        SeekBar startHour = dialogView.findViewById(R.id.startHour_dialog_add_class);
        SeekBar finishHour = dialogView.findViewById(R.id.finishHour_dialog_add_class);

        startHourTextView.setText(getResources().getString(R.string.start_hour_dialog_add_class) + " " + startHour.getProgress());
        finishHourTextView.setText(getResources().getString(R.string.start_hour_dialog_add_class) + " " + finishHour.getProgress());

        startHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView startHour = dialogView.findViewById(R.id.chooseHolidayStart_dialog_add_holiday);

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
                TextView finishHour = dialogView.findViewById(R.id.chooseHolidayEnd_dialog_add_holiday);

                finishHour.setText(getResources().getString(R.string.finish_hour_dialog_add_class) + " " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button save = dialogView.findViewById(R.id.save_dialog_add_class);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dialogView.getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    public void saveSubjectToDatabase(View view) {
        if (!validData()) {
            return;
        }

        Subject newSubject = collectDataFromUI();

        //TODO add newSubject to database
    }

    private boolean validData() {
        TextView textView = findViewById(R.id.subjectName_activity_add_subject);

        if (textView.getText().toString().isEmpty()) {
            textView.setError(getResources().getString(R.string.subjectName_activity_add_subject_error));

            return false;
        }

        return true;
    }

    private Subject collectDataFromUI() {
        String subjectName = ((TextView) findViewById(R.id.subjectName_activity_add_subject)).getText().toString();
        String subjectDescription = ((TextView) findViewById(R.id.subjectInfo_activity_add_subject)).getText().toString();
        boolean differentOnEvenWeeks = ((CheckBox) findViewById(R.id.differentTimetableEvenWeekCheckBox_activity_add_subject)).isChecked();
        ArrayList<SubjectComponent> components = new ArrayList<>();

        boolean hasLectures = ((CheckBox) findViewById(R.id.lecturesCheckBox_activity_add_subject)).isChecked();
        boolean hasSeminars = ((CheckBox) findViewById(R.id.seminarsCheckBox_activity_add_subject)).isChecked();
        boolean hasLaboratories = ((CheckBox) findViewById(R.id.laboratoriesCheckBox_activity_add_subject)).isChecked();
        boolean hasOthers = ((CheckBox) findViewById(R.id.othersCheckBox_activity_add_subject)).isChecked();

        /*

        if (hasLectures) {
            SubjectComponent lectures = new SubjectComponent(SubjectComponentType.LECTURE, );

            components.add(lectures);
        }

        if (hasSeminars) {
            SubjectComponent seminars = new SubjectComponent();

            components.add(seminars);
        }

        if (hasLaboratories) {
            SubjectComponent laboratories = new SubjectComponent();

            components.add(laboratories);
        }

        if (hasOthers) {
            SubjectComponent others = new SubjectComponent();

            components.add(others);
        }
        
         */

        return new Subject(subjectName, subjectDescription, differentOnEvenWeeks, components);
    }
}