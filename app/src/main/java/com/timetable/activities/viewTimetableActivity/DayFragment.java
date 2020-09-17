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
import com.timetable.utils.GlobalVariables;

import java.util.ArrayList;
import java.util.List;


public class DayFragment extends Fragment {
    private final String dayBundleKey = "day";
    private static ArrayList<DayFragment> instances = null;
    private static long weekNumber;
    private int day;
    private static List<Subject> subjects = null;
    private static List<Holiday> holidays = null;
    private ArrayList<TimetableEntry> timetableEntries;
    private TimetableItemAdapter adapter = null;

    public DayFragment() {}
    private DayFragment(int day) {
        super();

        this.day = day;
    }

    public static DayFragment getInstance(int day, long weekNumber) {
        if (instances == null) {
            DayFragment.weekNumber = weekNumber;
            instances = new ArrayList<>(GlobalVariables.getNumberOfDays());

            for (int i = 0; i < GlobalVariables.getNumberOfDays(); i++) {
                instances.add(i, new DayFragment(i));
            }
        }

        return instances.get(day);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            day = savedInstanceState.getInt(dayBundleKey);
            instances.remove(day);
            instances.add(day, this);
        }

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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(dayBundleKey, day);
    }

    public static void setWeekNumber(long weekNumber, Context context) {
        DayFragment.weekNumber = weekNumber;

        if (instances == null) {
            return;
        }

        for (DayFragment fragment : instances) {
            fragment.updateTimetable(context);
        }
    }

    private boolean checkHoliday() {
        return weekNumber == Constants.HOLIDAY_WEEK;
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

        if (checkHoliday()) {
            timetableEntries.add(new TimetableEntry(context));
        } else {
            for (Subject subject : subjects) {
                for (SubjectComponent component : subject.getComponents()) {
                    for (ClassInterval interval : component.getIntervals()) {
                        if (day == interval.getDay() && checkWeek(interval.getFrequency())) {
                            timetableEntries.add(new TimetableEntry(subject, component, interval, context));
                        }
                    }
                }
            }

            TimetableEntry.fillBreakTimes(timetableEntries, context);
        }

        if (adapter != null) {
            adapter.setTimetable(timetableEntries);
        }
    }
}
