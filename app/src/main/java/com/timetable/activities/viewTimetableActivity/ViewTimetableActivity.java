package com.timetable.activities.viewTimetableActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.timetable.R;
import com.timetable.activities.addSubjectActivity.AddSubjectActivity;
import com.timetable.database.holidays.HolidaysDatabase;
import com.timetable.utils.Constants;
import com.timetable.utils.GlobalVariables;

import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class ViewTimetableActivity extends AppCompatActivity {
    private long weekNumber;
    private TabLayout tabLayout;
    private TimetableFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        getWeekNumber();
        setTitle();

        tabLayout = findViewById(R.id.tabLayout_view_timetable_activity);
        ViewPager2 viewPager = findViewById(R.id.viewPager_view_timetable_activity);
        adapter = new TimetableFragmentAdapter(this, weekNumber, this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(Constants.getWeekDay(tabLayout.getContext(), position));
            }
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_timetable_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Context popupContext = new ContextThemeWrapper(this, R.style.popupMenu);
        PopupMenu popupMenu = new PopupMenu(popupContext, findViewById(R.id.choose_timetable_view_timetable_activity));
        popupMenu.getMenuInflater().inflate(R.menu.popup_timetable_displayed, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.current_week_option:
                        getWeekNumber();
                        break;
                    case R.id.even_week_option:
                        weekNumber = Constants.EVEN_WEEK;
                        break;
                    case R.id.odd_week_option:
                        weekNumber = Constants.ODD_WEEK;
                        break;
                }

                setTitle();
                adapter.setWeekNumber(weekNumber);

                return true;
            }
        });

        popupMenu.show();

        return true;
    }

    private void getWeekNumber() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.getSharedPreferenceName(), MODE_PRIVATE);
        Calendar semesterStart = Calendar.getInstance();
        Date currentDate = new Date();
        Date semesterStartDate;

        semesterStart.set(sharedPreferences.getInt("semester_start_year", Constants.getSemesterStartDefault(2)),
                sharedPreferences.getInt("semester_start_month", Constants.getSemesterStartDefault(1)),
                sharedPreferences.getInt("semester_start_day", Constants.getSemesterStartDefault(0)), 0, 0, 0);
        semesterStartDate = semesterStart.getTime();

        weekNumber = 1 + ((DAYS.between(semesterStartDate.toInstant(), currentDate.toInstant())) / GlobalVariables.getNumberOfDays());

        if (weekNumber == 1 && currentDate.before(semesterStartDate)) {
            weekNumber = Constants.ODD_WEEK;
        } else if (weekNumber <= 0) {
            weekNumber *= -1;

            if (weekNumber % 2 == 0) {
                weekNumber = Constants.EVEN_WEEK;
            } else {
                weekNumber = Constants.ODD_WEEK;
            }
        }
    }

    private void setTitle() {
        if (getSupportActionBar() == null) {
            return;
        }

        if (weekNumber == Constants.EVEN_WEEK) {
            setTitle(getResources().getString(R.string.view_timetable_activity_title_even_week));
        } else if (weekNumber == Constants.ODD_WEEK) {
            setTitle(getResources().getString(R.string.view_timetable_activity_title_odd_week));
        } else {
            setTitle(getResources().getString(R.string.view_timetable_activity_title_current_week, weekNumber));
        }
    }
}