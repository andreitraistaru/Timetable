package com.timetable.activities.yearStructureActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.holidays.Holiday;

import java.util.Calendar;
import java.util.List;

public class HolidayItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class HolidayItemViewHolder extends RecyclerView.ViewHolder {
        private TextView workingWeek;
        private TextView startDate;
        private TextView endDate;

        public HolidayItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.workingWeek = itemView.findViewById(R.id.workingWeek_item_holiday);
            this.startDate = itemView.findViewById(R.id.startingDate_item_holiday);
            this.endDate = itemView.findViewById(R.id.endingDate_item_holiday);
        }

        public void setWorkingWeek(String text) {
            workingWeek.setText(text);
        }
        public void setStartDate(String text) {
            startDate.setText(text);
        }
        public void setEndDate(String text) {
            endDate.setText(text);
        }
    }

    private Context context;
    private List<Holiday> holidays;

    public HolidayItemAdapter(Context context) {
        this.context = context;
        this.holidays = null;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolidayItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holiday, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holiday holiday = holidays.get(position);
        Calendar calendar = Calendar.getInstance();

        if (holiday.isWorkingWeek()) {
            ((HolidayItemViewHolder) holder).setWorkingWeek(context.getResources().getString(R.string.working_week_item_holiday));
        } else {
            ((HolidayItemViewHolder) holder).setWorkingWeek(context.getResources().getString(R.string.holiday_week_item_holiday));
        }

        calendar.setTime(holiday.getFirstDay());
        ((HolidayItemViewHolder) holder).setStartDate(context.getResources().getString(R.string.start_date_item_holiday, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));

        calendar.setTime(holiday.getLastDay());
        ((HolidayItemViewHolder) holder).setEndDate(context.getResources().getString(R.string.end_date_item_holiday, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)));
    }

    @Override
    public int getItemCount() {
        if (holidays == null) {
            return 0;
        }

        return holidays.size();
    }
}
