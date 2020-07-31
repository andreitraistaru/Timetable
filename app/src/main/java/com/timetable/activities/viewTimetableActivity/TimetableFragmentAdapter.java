package com.timetable.activities.viewTimetableActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.timetable.utils.GlobalVariables;

public class TimetableFragmentAdapter extends FragmentStateAdapter {

    public TimetableFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new DayFragment();
    }

    @Override
    public int getItemCount() {
        return GlobalVariables.numberOfDays;
    }
}
