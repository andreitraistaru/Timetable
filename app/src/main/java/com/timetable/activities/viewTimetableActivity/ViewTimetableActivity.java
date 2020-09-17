package com.timetable.activities.viewTimetableActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.timetable.R;
import com.timetable.activities.yearStructureActivity.YearStructureActivity;
import com.timetable.database.holidays.Holiday;
import com.timetable.database.holidays.HolidaysDatabase;
import com.timetable.utils.Constants;
import com.timetable.utils.GlobalVariables;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ViewTimetableActivity extends AppCompatActivity {
    private final String weekNumberBundleKey = "weekNumber";
    private boolean deepUpdateOnResume = true;  // this is used to auto-update timetable after year structure
                                                // is changed and ViewTimetableActivity is resumed
    private long weekNumber;
    private TabLayout tabLayout;
    private TimetableFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        if (savedInstanceState != null) {
            weekNumber = savedInstanceState.getLong(weekNumberBundleKey);
            deepUpdateOnResume = false;
        } else {
            weekNumber = Constants.ODD_WEEK;
            deepUpdateOnResume = true;
        }

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
    protected void onResume() {
        super.onResume();

        updateWeekNumber(deepUpdateOnResume);
        deepUpdateOnResume = true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(weekNumberBundleKey, weekNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_timetable_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.choose_timetable_view_timetable_activity:
                final Context popupContext = new ContextThemeWrapper(this, R.style.popupMenu);
                PopupMenu popupMenu = new PopupMenu(popupContext, findViewById(R.id.choose_timetable_view_timetable_activity));
                popupMenu.getMenuInflater().inflate(R.menu.popup_timetable_displayed, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.current_week_option:
                                updateWeekNumber(true);
                                break;
                            case R.id.even_week_option:
                                weekNumber = Constants.EVEN_WEEK;
                                updateWeekNumber(false);
                                break;
                            case R.id.odd_week_option:
                                weekNumber = Constants.ODD_WEEK;
                                updateWeekNumber(false);
                                break;
                        }

                        return true;
                    }
                });

                popupMenu.show();

                break;
            case R.id.year_structure_view_timetable_activity:
                startActivity(new Intent(this, YearStructureActivity.class));

                break;
            default:
        }

        return true;
    }

    private void updateWeekNumber(boolean deepUpdate) {
        if (deepUpdate) {
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.getSharedPreferenceName(), MODE_PRIVATE);
            final Calendar semesterStart = Calendar.getInstance();
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

            HolidaysDatabase.getDatabase(this).getHolidayDao().getAllHolidays().observe(this, new Observer<List<Holiday>>() {
                @Override
                public void onChanged(List<Holiday> data) {
                    Calendar now = Calendar.getInstance();
                    now.setTime(new Date());

                    for (Holiday holiday : data) {
                        if (holiday.isHolidayAtDate(now)) {
                            weekNumber = Constants.HOLIDAY_WEEK;
                            break;
                        }

                        if (!holiday.isWorkingWeek() && holiday.isPastHoliday(now)) {
                            weekNumber-= holiday.getDurationInWeeksAfterDate(semesterStart);
                        }
                    }

                    setTitle();
                    adapter.setWeekNumber(weekNumber);
                }
            });
        } else {
            setTitle();
            adapter.setWeekNumber(weekNumber);
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
        } else if (weekNumber == Constants.HOLIDAY_WEEK) {
            setTitle(getResources().getString(R.string.view_timetable_activity_title_holiday_week));
        } else {
            setTitle(getResources().getString(R.string.view_timetable_activity_title_current_week, weekNumber));
        }
    }
}