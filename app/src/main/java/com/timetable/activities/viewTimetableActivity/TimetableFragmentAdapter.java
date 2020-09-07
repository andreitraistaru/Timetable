package com.timetable.activities.viewTimetableActivity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.timetable.utils.GlobalVariables;

import java.util.ArrayList;

public class TimetableFragmentAdapter extends FragmentStateAdapter {
    private ArrayList<DayFragment> fragments;
    private Context context;

    public TimetableFragmentAdapter(@NonNull FragmentActivity fragmentActivity, long weekNumber, Context context) {
        super(fragmentActivity);

        this.fragments = new ArrayList<>(GlobalVariables.getNumberOfDays());
        this.context = context;

        for (int i = 0; i < GlobalVariables.getNumberOfDays(); i++) {
            fragments.add(new DayFragment(i, weekNumber));
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public void setWeekNumber(long weekNumber) {
        for (DayFragment fragment : fragments) {
            fragment.setWeekNumber(weekNumber, context);
        }

        notifyDataSetChanged();
    }
}
