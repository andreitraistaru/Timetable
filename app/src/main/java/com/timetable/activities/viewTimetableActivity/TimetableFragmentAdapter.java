package com.timetable.activities.viewTimetableActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.timetable.utils.GlobalVariables;

public class TimetableFragmentAdapter extends FragmentStateAdapter {
    private long weekNumber;

    public TimetableFragmentAdapter(@NonNull FragmentActivity fragmentActivity, long weekNumber) {
        super(fragmentActivity);

        this.weekNumber = weekNumber;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new DayFragment(position, weekNumber);
    }

    @Override
    public int getItemCount() {
        return GlobalVariables.getNumberOfDays();
    }
}
