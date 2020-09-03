package com.timetable.activities.addSubjectActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.subjects.ClassInterval;
import com.timetable.utils.Constants;

import java.util.ArrayList;

public class ClassIntervalItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class ClassIntervalItemViewHolder extends RecyclerView.ViewHolder {
        private TextView weekDay;
        private TextView location;
        private TextView startHour;
        private TextView endHour;
        private TextView frequency;

        public ClassIntervalItemViewHolder(@NonNull View itemView) {
            super(itemView);

            weekDay = itemView.findViewById(R.id.weekDay_item_class_interval);
            location = itemView.findViewById(R.id.location_item_class_interval);
            startHour = itemView.findViewById(R.id.startingHour_item_class_interval);
            endHour = itemView.findViewById(R.id.endingHour_item_class_interval);
            frequency = itemView.findViewById(R.id.frequency_item_class_interval);
        }

        public void setWeekDay(String text) {
            weekDay.setText(text);
        }
        public void setLocation(String text) {
            location.setText(text);
        }
        public void setStartHour(String text) {
            startHour.setText(text);
        }
        public void setEndHour(String text) {
            endHour.setText(text);
        }
        public void setFrequency(String text) {
            frequency.setText(text);
        }
    }

    private Context context;
    private ArrayList<ClassInterval> intervals;

    public ClassIntervalItemAdapter(Context context, ArrayList<ClassInterval> intervals) {
        super();

        this.context = context;
        this.intervals = intervals;
    }

    @NonNull
    @Override
    public ClassIntervalItemAdapter.ClassIntervalItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassIntervalItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_interval, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ClassInterval classInterval = intervals.get(position);

        ((ClassIntervalItemViewHolder) holder).setWeekDay(Constants.getWeekDay(context, classInterval.getDay()));
        ((ClassIntervalItemViewHolder) holder).setLocation(context.getResources().getString(R.string.location_item_class_interval, classInterval.getLocation()));
        ((ClassIntervalItemViewHolder) holder).setStartHour(context.getResources().getString(R.string.starting_hour_item_class_interval, classInterval.getStartingHour()));
        ((ClassIntervalItemViewHolder) holder).setEndHour(context.getResources().getString(R.string.ending_hour_item_class_interval, classInterval.getEndingHour()));
        ((ClassIntervalItemViewHolder) holder).setFrequency(Constants.getFrequency(context, classInterval.getFrequency()));
    }

    @Override
    public int getItemCount() {
        return intervals.size();
    }
}
