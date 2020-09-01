package com.timetable.activities.viewTimetableActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.timetable.R;
import com.timetable.utils.Constants;

public class ViewTimetableActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tabLayout = findViewById(R.id.tabLayout_view_timetable_activity);
        viewPager = findViewById(R.id.viewPager_view_timetable_activity);
        viewPager.setAdapter(new TimetableFragmentAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(Constants.getWeekDay(tabLayout.getContext(), position));
            }
        }).attach();
    }
}