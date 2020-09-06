package com.timetable.activities.viewTimetableActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.timetable.R;
import com.timetable.utils.Constants;
import com.timetable.utils.GlobalVariables;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class ViewTimetableActivity extends AppCompatActivity {
    private long weekNumber;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.getSharedPreferenceName(), MODE_PRIVATE);
        Calendar semesterStart = Calendar.getInstance();
        Date currentDate = new Date();
        Date semesterStartDate;

        semesterStart.set(sharedPreferences.getInt("semester_start_year", Constants.getSemesterStartDefault(2)),
                sharedPreferences.getInt("semester_start_month", Constants.getSemesterStartDefault(1)),
                sharedPreferences.getInt("semester_start_day", Constants.getSemesterStartDefault(0)));
        semesterStartDate = semesterStart.getTime();

        weekNumber = 1 + ((DAYS.between(semesterStartDate.toInstant(), currentDate.toInstant())) / GlobalVariables.getNumberOfDays());

        if (getSupportActionBar() != null) {
            setTitle(getResources().getString(R.string.view_timetable_activity_title, weekNumber));
        }

        tabLayout = findViewById(R.id.tabLayout_view_timetable_activity);
        viewPager = findViewById(R.id.viewPager_view_timetable_activity);
        viewPager.setAdapter(new TimetableFragmentAdapter(this, weekNumber));

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(Constants.getWeekDay(tabLayout.getContext(), position));
            }
        }).attach();
    }
}