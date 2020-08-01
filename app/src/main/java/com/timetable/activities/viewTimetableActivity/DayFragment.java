package com.timetable.activities.viewTimetableActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;

public class DayFragment extends Fragment {
    int day;

    public DayFragment(int day) {
        super();

        this.day = day;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_fragment_day);

        Drawable divider = ContextCompat.getDrawable(view.getContext(), R.drawable.timetable_items_divider);
        DividerItemDecoration itemDecoration1 = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        DividerItemDecoration itemDecoration2 = new DividerItemDecoration(view.getContext(), DividerItemDecoration.HORIZONTAL);

        itemDecoration1.setDrawable(divider);
        itemDecoration2.setDrawable(divider);

        recyclerView.addItemDecoration(itemDecoration1);
        recyclerView.addItemDecoration(itemDecoration2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new TimetableItemAdapter());

        return view;
    }
}
