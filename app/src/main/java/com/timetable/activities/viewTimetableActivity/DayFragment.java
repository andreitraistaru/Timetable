package com.timetable.activities.viewTimetableActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.timetable.R;

import org.w3c.dom.Text;

public class DayFragment extends Fragment {
    TextView tv;
    int day;

    public DayFragment(int day) {
        super();

        this.day = day;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day, container, false);

        tv = view.findViewById(R.id.textView2);
        tv.setText("Ziua " + day);

        return view;
    }
}
