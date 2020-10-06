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
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.timetable.utils.Maths;

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
    private RecyclerView recyclerView = null;

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
        recyclerView = view.findViewById(R.id.recyclerView_fragment_day);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));

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

        final int[] cellSize = new int[timetableEntries.size()];
        int maxWidth = 1;

        for (int i = 0; i < timetableEntries.size(); i++) {
            int counter = 1;
            int j = i;
            int minStartHour = timetableEntries.get(i).getStart();
            int maxEndHour = timetableEntries.get(i).getEnd();

            while (j < timetableEntries.size() - 1 && checkOverlapping(maxEndHour, timetableEntries.get(j + 1))) {
                counter++;
                if (maxEndHour < timetableEntries.get(j + 1).getEnd()) {
                    maxEndHour = timetableEntries.get(j + 1).getEnd();
                }

                j++;
            }

            while (i <= j) {
                cellSize[i] = counter;
                timetableEntries.get(i).setSlotStart(minStartHour);
                timetableEntries.get(i).setSlotEnd(maxEndHour);

                i++;
            }

            i--;        //to work with i++ form for statement

            maxWidth = Maths.lcm(maxWidth, counter);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), maxWidth);

        for (int i = 0; i < cellSize.length; i++) {
            cellSize[i] = maxWidth / cellSize[i];
        }

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return cellSize[position];
            }
        });

        if (recyclerView != null) {
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        if (adapter != null) {
            adapter.setTimetable(timetableEntries);
        }
    }

    private boolean checkOverlapping(int maxEndHour, TimetableEntry entry) {
        if (entry.getStart() >= maxEndHour) {
            return false;
        }
        return true;
    }
}
