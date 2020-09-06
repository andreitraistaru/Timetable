package com.timetable.activities.viewTimetableActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.holidays.Holiday;
import com.timetable.database.holidays.HolidaysDatabase;
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectsDatabase;

import java.util.ArrayList;
import java.util.List;

public class DayFragment extends Fragment {
    private int day;
    private long weekNumber;
    private ArrayList<TimetableEntry> timetableEntries;

    public DayFragment(int day, long weekNumber) {
        super();

        this.day = day;
        this.weekNumber = weekNumber;
        this.timetableEntries = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_fragment_day);

        Drawable divider = ContextCompat.getDrawable(view.getContext(), R.drawable.timetable_items_divider);
        DividerItemDecoration itemDecoration1 = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        DividerItemDecoration itemDecoration2 = new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL);

        if (divider != null) {
            itemDecoration1.setDrawable(divider);
            itemDecoration2.setDrawable(divider);
        }

        recyclerView.addItemDecoration(itemDecoration1);
        recyclerView.addItemDecoration(itemDecoration2);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        final TimetableItemAdapter adapter = new TimetableItemAdapter();
        recyclerView.setAdapter(adapter);

        HolidaysDatabase.getDatabase(getContext()).getHolidayDao().getAllHolidays().observe(getViewLifecycleOwner(), new Observer<List<Holiday>>() {
            @Override
            public void onChanged(List<Holiday> holidays) {
                updateTimetable(null, holidays, adapter);
            }
        });

        SubjectsDatabase.getDatabase(getContext()).getSubjectDao().getAllSubjects().observe(getViewLifecycleOwner(), new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                updateTimetable(subjects, null, adapter);
            }
        });

        return view;
    }

    private void updateTimetable (List<Subject> subjects, List<Holiday> holidays, TimetableItemAdapter adapter) {
        if (subjects == null)
            return;
        timetableEntries.add(new TimetableEntry("08:00", "Analiza", "(Lecture)", "Leu", "10:00", subjects.get(0).getComponents().get(0).getColor()));
        adapter.setTimetable(timetableEntries);
    }
}
