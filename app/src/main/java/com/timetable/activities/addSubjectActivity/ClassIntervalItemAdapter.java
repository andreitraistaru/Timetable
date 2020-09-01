package com.timetable.activities.addSubjectActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.subjects.ClassInterval;

import java.util.ArrayList;

public class ClassIntervalItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class ClassIntervalItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ClassIntervalItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    private ArrayList<ClassInterval> intervals;

    public ClassIntervalItemAdapter(ArrayList<ClassInterval> intervals) {
        super();

        this.intervals = intervals;
    }

    @NonNull
    @Override
    public ClassIntervalItemAdapter.ClassIntervalItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassIntervalItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_interval, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ClassIntervalItemViewHolder) holder).getTextView().setText("" + intervals.get(position).getStartingHour() + " -> " + intervals.get(position).getEndingHour());
    }

    @Override
    public int getItemCount() {
        return intervals.size();
    }
}
