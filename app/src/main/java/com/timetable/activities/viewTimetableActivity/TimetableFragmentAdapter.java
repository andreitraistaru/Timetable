package com.timetable.activities.viewTimetableActivity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.timetable.utils.GlobalVariables;

public class TimetableFragmentAdapter extends FragmentStateAdapter {
    private Context context;
    private long weekNumber;

    public TimetableFragmentAdapter(@NonNull FragmentActivity fragmentActivity, long weekNumber, Context context) {
        super(fragmentActivity);

        this.weekNumber = weekNumber;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return DayFragment.getInstance(position, weekNumber);
    }

    @Override
    public int getItemCount() {
        return GlobalVariables.getNumberOfDays();
    }

    public void setWeekNumber(long weekNumber) {
        this.weekNumber = weekNumber;

        DayFragment.setWeekNumber(weekNumber, context);
    }
}
