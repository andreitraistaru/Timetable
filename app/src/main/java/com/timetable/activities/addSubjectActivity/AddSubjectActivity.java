package com.timetable.activities.addSubjectActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.divyanshu.colorseekbar.ColorSeekBar;
import com.timetable.R;
import com.timetable.database.subjects.ClassInterval;
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectComponent;
import com.timetable.database.subjects.SubjectComponentType;
import com.timetable.utils.Constants;

import java.util.ArrayList;

public class AddSubjectActivity extends AppCompatActivity {
    private ArrayList<ClassInterval> lecturesIntervals;
    private ArrayList<ClassInterval> seminarsIntervals;
    private ArrayList<ClassInterval> laboratoriesIntervals;
    private ArrayList<ClassInterval> othersIntervals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.add_subject_activity_title);
        }

        lecturesIntervals = new ArrayList<>();
        seminarsIntervals = new ArrayList<>();
        laboratoriesIntervals = new ArrayList<>();
        othersIntervals = new ArrayList<>();

        CheckBox lectures = findViewById(R.id.lecturesCheckBox_activity_add_subject);
        CheckBox seminars = findViewById(R.id.seminarsCheckBox_activity_add_subject);
        CheckBox laboratories = findViewById(R.id.laboratoriesCheckBox_activity_add_subject);
        CheckBox others = findViewById(R.id.othersCheckBox_activity_add_subject);

        showFields(lectures);
        showFields(seminars);
        showFields(laboratories);
        showFields(others);

        RecyclerView lecturesRecyclerView = findViewById(R.id.classLectures_activity_add_subject);
        lecturesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lecturesRecyclerView.setAdapter(new ClassIntervalItemAdapter(this, lecturesIntervals));

        RecyclerView seminarsRecyclerView = findViewById(R.id.classSeminars_activity_add_subject);
        seminarsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        seminarsRecyclerView.setAdapter(new ClassIntervalItemAdapter(this, seminarsIntervals));

        RecyclerView laboratoriesRecyclerView = findViewById(R.id.classLaboratories_activity_add_subject);
        laboratoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        laboratoriesRecyclerView.setAdapter(new ClassIntervalItemAdapter(this, laboratoriesIntervals));

        RecyclerView othersRecyclerView = findViewById(R.id.classOthers_activity_add_subject);
        othersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        othersRecyclerView.setAdapter(new ClassIntervalItemAdapter(this, othersIntervals));
    }

    public void showFields(View view) {
        if (getCurrentFocus() != null) {
            getCurrentFocus().clearFocus();
        }

        switch (view.getId()) {
            case R.id.lecturesCheckBox_activity_add_subject:
            {
                RecyclerView recyclerView = findViewById(R.id.classLectures_activity_add_subject);
                Button button = findViewById(R.id.newClassLectures_activity_add_subject);
                ColorSeekBar colorSeekBar = findViewById(R.id.colorPickerLectures_activity_add_subject);
                EditText teacher = findViewById(R.id.teacherNameLectures_activity_add_subject);
                EditText location = findViewById(R.id.locationLectures_activity_add_subject);
                EditText info = findViewById(R.id.infoLectures_activity_add_subject);

                if (!((CheckBox) view).isChecked()) {
                    recyclerView.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    colorSeekBar.setVisibility(View.GONE);
                    teacher.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    info.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    colorSeekBar.setVisibility(View.VISIBLE);
                    teacher.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    info.setVisibility(View.VISIBLE);
                }

                break;
            }

            case R.id.seminarsCheckBox_activity_add_subject:
            {
                RecyclerView recyclerView = findViewById(R.id.classSeminars_activity_add_subject);
                Button button = findViewById(R.id.newClassSeminars_activity_add_subject);
                ColorSeekBar colorSeekBar = findViewById(R.id.colorPickerSeminars_activity_add_subject);
                EditText teacher = findViewById(R.id.teacherNameSeminars_activity_add_subject);
                EditText location = findViewById(R.id.locationSeminars_activity_add_subject);
                EditText info = findViewById(R.id.infoSeminars_activity_add_subject);

                if (!((CheckBox) view).isChecked()) {
                    recyclerView.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    colorSeekBar.setVisibility(View.GONE);
                    teacher.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    info.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    colorSeekBar.setVisibility(View.VISIBLE);
                    teacher.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    info.setVisibility(View.VISIBLE);
                }

                break;
            }

            case R.id.laboratoriesCheckBox_activity_add_subject:
            {
                RecyclerView recyclerView = findViewById(R.id.classLaboratories_activity_add_subject);
                Button button = findViewById(R.id.newClassLaboratories_activity_add_subject);
                ColorSeekBar colorSeekBar = findViewById(R.id.colorPickerLaboratories_activity_add_subject);
                EditText teacher = findViewById(R.id.teacherNameLaboratories_activity_add_subject);
                EditText location = findViewById(R.id.locationLaboratories_activity_add_subject);
                EditText info = findViewById(R.id.infoLaboratories_activity_add_subject);

                if (!((CheckBox) view).isChecked()) {
                    recyclerView.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    colorSeekBar.setVisibility(View.GONE);
                    teacher.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    info.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    colorSeekBar.setVisibility(View.VISIBLE);
                    teacher.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    info.setVisibility(View.VISIBLE);
                }

                break;
            }

            case R.id.othersCheckBox_activity_add_subject:
            {
                RecyclerView recyclerView = findViewById(R.id.classOthers_activity_add_subject);
                Button button = findViewById(R.id.newClassOthers_activity_add_subject);
                ColorSeekBar colorSeekBar = findViewById(R.id.colorPickerOthers_activity_add_subject);
                EditText teacher = findViewById(R.id.teacherNameOthers_activity_add_subject);
                EditText location = findViewById(R.id.locationOthers_activity_add_subject);
                EditText info = findViewById(R.id.infoOthers_activity_add_subject);

                if (!((CheckBox) view).isChecked()) {
                    recyclerView.setVisibility(View.GONE);
                    button.setVisibility(View.GONE);
                    colorSeekBar.setVisibility(View.GONE);
                    teacher.setVisibility(View.GONE);
                    location.setVisibility(View.GONE);
                    info.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    colorSeekBar.setVisibility(View.VISIBLE);
                    teacher.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    info.setVisibility(View.VISIBLE);
                }

                break;
            }
        }
    }

    public void showNewClassDialog(final View dialogViewOpener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(dialogViewOpener.getContext()).inflate(R.layout.dialog_add_class, viewGroup, false);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView startHourTextView = dialogView.findViewById(R.id.chooseHolidayStart_dialog_add_holiday);
        TextView finishHourTextView = dialogView.findViewById(R.id.chooseHolidayEnd_dialog_add_holiday);

        SeekBar startHour = dialogView.findViewById(R.id.startHour_dialog_add_class);
        SeekBar finishHour = dialogView.findViewById(R.id.finishHour_dialog_add_class);

        startHourTextView.setText(getResources().getString(R.string.start_hour_dialog_add_class, startHour.getProgress()));
        finishHourTextView.setText(getResources().getString(R.string.start_hour_dialog_add_class, finishHour.getProgress()));

        startHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView startHour = dialogView.findViewById(R.id.chooseHolidayStart_dialog_add_holiday);

                startHour.setText(getResources().getString(R.string.start_hour_dialog_add_class, seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        finishHour.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TextView finishHour = dialogView.findViewById(R.id.chooseHolidayEnd_dialog_add_holiday);

                finishHour.setText(getResources().getString(R.string.finish_hour_dialog_add_class, seekBar.getProgress()));
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
                int weekDay = ((Spinner) dialogView.findViewById(R.id.spinner_dialog_add_class)).getSelectedItemPosition();
                int startHour = ((SeekBar) dialogView.findViewById(R.id.startHour_dialog_add_class)).getProgress();
                int endHour = ((SeekBar) dialogView.findViewById(R.id.finishHour_dialog_add_class)).getProgress();

                if (startHour >= endHour) {
                    Toast.makeText(dialogView.getContext(), dialogView.getContext().getResources().getString(R.string.wrong_starting_ending_hours), Toast.LENGTH_SHORT).show();

                    return;
                }

                int frequency;

                switch (((RadioGroup) dialogView.findViewById(R.id.frequency_dialog_add_class)).getCheckedRadioButtonId()) {
                    case R.id.frequency_both_dialog_add_class:
                        frequency = Constants.BOTH;
                        break;
                    case R.id.frequency_even_dialog_add_class:
                        frequency = Constants.EVEN_ONLY;
                        break;
                    case R.id.frequency_odd_dialog_add_class:
                        frequency = Constants.ODD_ONLY;
                        break;
                    default:
                        frequency = -1;
                }

                addNewClassInterval(new ClassInterval(weekDay, startHour, endHour, frequency), dialogViewOpener.getId());

                alertDialog.dismiss();
            }
        });
    }

    private void addNewClassInterval(ClassInterval classInterval, int where) {
        ClassIntervalItemAdapter adapter;

        switch (where) {
            case R.id.newClassLectures_activity_add_subject:
                lecturesIntervals.add(classInterval);
                adapter = (ClassIntervalItemAdapter) ((RecyclerView) findViewById(R.id.classLectures_activity_add_subject)).getAdapter();

                if (adapter != null) {
                    adapter.notifyItemInserted(lecturesIntervals.size() - 1);
                }

                return;
            case R.id.newClassSeminars_activity_add_subject:
                seminarsIntervals.add(classInterval);
                adapter = (ClassIntervalItemAdapter) ((RecyclerView) findViewById(R.id.classSeminars_activity_add_subject)).getAdapter();

                if (adapter != null) {
                    adapter.notifyItemInserted(seminarsIntervals.size() - 1);
                }

                return;
            case R.id.newClassLaboratories_activity_add_subject:
                laboratoriesIntervals.add(classInterval);
                adapter = (ClassIntervalItemAdapter) ((RecyclerView) findViewById(R.id.classLaboratories_activity_add_subject)).getAdapter();

                if(adapter != null) {
                    adapter.notifyItemInserted(laboratoriesIntervals.size() - 1);
                }

                return;
            case R.id.newClassOthers_activity_add_subject:
                othersIntervals.add(classInterval);
                adapter = (ClassIntervalItemAdapter) ((RecyclerView) findViewById(R.id.classOthers_activity_add_subject)).getAdapter();

                if (adapter != null) {
                    adapter.notifyItemInserted(othersIntervals.size() - 1);
                }
        }
    }

    public void saveSubjectToDatabase(View view) {
        if (!validData()) {
            return;
        }

        Subject newSubject = collectDataFromUI();

        //TODO add newSubject to database

        finish();
    }

    private boolean validData() {
        TextView textView = findViewById(R.id.subjectName_activity_add_subject);

        if (textView.getText().toString().isEmpty()) {
            textView.requestFocus();

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

        if (hasLectures) {
            int color = ((ColorSeekBar) findViewById(R.id.colorPickerLectures_activity_add_subject)).getColor();
            String teacher = ((TextView) findViewById(R.id.teacherNameLectures_activity_add_subject)).getText().toString();
            String location = ((TextView) findViewById(R.id.locationLectures_activity_add_subject)).getText().toString();
            String description = ((TextView) findViewById(R.id.infoLectures_activity_add_subject)).getText().toString();

            SubjectComponent lectures = new SubjectComponent(SubjectComponentType.LECTURE, lecturesIntervals, color, teacher, location, description);

            components.add(lectures);
        }
        if (hasSeminars) {
            int color = ((ColorSeekBar) findViewById(R.id.colorPickerSeminars_activity_add_subject)).getColor();
            String teacher = ((TextView) findViewById(R.id.teacherNameSeminars_activity_add_subject)).getText().toString();
            String location = ((TextView) findViewById(R.id.locationSeminars_activity_add_subject)).getText().toString();
            String description = ((TextView) findViewById(R.id.infoSeminars_activity_add_subject)).getText().toString();

            SubjectComponent seminars = new SubjectComponent(SubjectComponentType.SEMINAR, seminarsIntervals, color, teacher, location, description);

            components.add(seminars);
        }
        if (hasLaboratories) {
            int color = ((ColorSeekBar) findViewById(R.id.colorPickerLaboratories_activity_add_subject)).getColor();
            String teacher = ((TextView) findViewById(R.id.teacherNameLaboratories_activity_add_subject)).getText().toString();
            String location = ((TextView) findViewById(R.id.locationLaboratories_activity_add_subject)).getText().toString();
            String description = ((TextView) findViewById(R.id.infoLaboratories_activity_add_subject)).getText().toString();

            SubjectComponent laboratories = new SubjectComponent(SubjectComponentType.LABORATORY, laboratoriesIntervals, color, teacher, location, description);

            components.add(laboratories);
        }
        if (hasOthers) {
            int color = ((ColorSeekBar) findViewById(R.id.colorPickerOthers_activity_add_subject)).getColor();
            String teacher = ((TextView) findViewById(R.id.teacherNameOthers_activity_add_subject)).getText().toString();
            String location = ((TextView) findViewById(R.id.locationOthers_activity_add_subject)).getText().toString();
            String description = ((TextView) findViewById(R.id.infoOthers_activity_add_subject)).getText().toString();

            SubjectComponent others = new SubjectComponent(SubjectComponentType.OTHER, othersIntervals, color, teacher, location, description);

            components.add(others);
        }

        return new Subject(subjectName, subjectDescription, differentOnEvenWeeks, components);
    }
}