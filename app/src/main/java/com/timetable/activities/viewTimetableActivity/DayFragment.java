package com.timetable.activities.viewTimetableActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.holidays.Holiday;
import com.timetable.database.holidays.HolidaysDatabase;
import com.timetable.database.subjects.ClassInterval;
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectComponent;
import com.timetable.database.subjects.SubjectsDatabase;
import com.timetable.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DayFragment extends Fragment {
    private int day;
    private long weekNumber;
    private List<Subject> subjects = null;
    private List<Holiday> holidays = null;
    private ArrayList<TimetableEntry> timetableEntries;
    private TimetableItemAdapter adapter;

    public DayFragment(int day, long weekNumber) {
        super();

        this.day = day;
        this.weekNumber = weekNumber;
        this.timetableEntries = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_fragment_day);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TimetableItemAdapter();
        recyclerView.setAdapter(adapter);

        HolidaysDatabase.getDatabase(getContext()).getHolidayDao().getAllHolidays().observe(getViewLifecycleOwner(), new Observer<List<Holiday>>() {
            @Override
            public void onChanged(List<Holiday> data) {
                holidays = data;
                updateTimetable(getContext());
            }
        });

        SubjectsDatabase.getDatabase(getContext()).getSubjectDao().getAllSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> data) {
                subjects = data;
                updateTimetable(getContext());
            }
        });

        return view;
    }

    public void setWeekNumber(long weekNumber, Context context) {
        this.weekNumber = weekNumber;

        updateTimetable(context);
    }

    private boolean checkWeek(int frequency) {
        switch (frequency) {
            case Constants.BOTH:
                return true;
            case Constants.EVEN_ONLY:
                return (weekNumber == Constants.EVEN_WEEK || weekNumber % 2 == 0);
            case Constants.ODD_ONLY:
                return (weekNumber == Constants.ODD_WEEK || weekNumber % 2 == 1);
            default:
                return false;
        }
    }

    private void updateTimetable (Context context) {
        if (subjects == null || holidays == null) {
            return;
        }

        timetableEntries = new ArrayList<>();

        for (Subject subject : subjects) {
            for (SubjectComponent component : subject.getComponents()) {
                for (ClassInterval interval : component.getIntervals()) {
                    if (day == interval.getDay() && checkWeek(interval.getFrequency())) {
                        TimetableEntry entry = new TimetableEntry(subject, component, interval, context);

                        timetableEntries.add(entry);
                    }
                }
            }
        }

        TimetableEntry.fillBreakTimes(timetableEntries, context);

        if (adapter != null) {
            adapter.setTimetable(timetableEntries);
        }
    }
}
