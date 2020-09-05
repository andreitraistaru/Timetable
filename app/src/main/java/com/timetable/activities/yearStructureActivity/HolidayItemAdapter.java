package com.timetable.activities.yearStructureActivity;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.holidays.Holiday;
import com.timetable.database.holidays.HolidaysDatabase;

import java.util.Calendar;
import java.util.List;

public class HolidayItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class HolidayItemViewHolder extends RecyclerView.ViewHolder {
        private TextView workingWeek;
        private TextView startDate;
        private TextView endDate;
        private CardView cardView;

        public HolidayItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.workingWeek = itemView.findViewById(R.id.workingWeek_item_holiday);
            this.startDate = itemView.findViewById(R.id.startingDate_item_holiday);
            this.endDate = itemView.findViewById(R.id.endingDate_item_holiday);
            this.cardView = itemView.findViewById(R.id.cardView_item_holiday);
        }

        public CardView getCardView() {
            return cardView;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Holiday holiday = holidays.get(position);
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

        ((HolidayItemViewHolder) holder).getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Context popupContext = new ContextThemeWrapper(context, R.style.popupMenu);
                PopupMenu popupMenu = new PopupMenu(popupContext, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_holidays_list_year_structure, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.delete_popup_holidays_option) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    HolidaysDatabase.getDatabase(context).getHolidayDao().deleteHoliday(holiday);
                                }
                            }).start();

                            holidays.remove(position);
                            notifyDataSetChanged();
                        }

                        return true;
                    }
                });

                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (holidays == null) {
            return 0;
        }

        return holidays.size();
    }
}
