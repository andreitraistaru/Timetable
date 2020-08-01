package com.timetable.activities.viewTimetableActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;

public class TimetableItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class TimetableItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public TextView getTextView() {
            return textView;
        }

        public TimetableItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }

    @NonNull
    @Override
    public TimetableItemAdapter.TimetableItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimetableItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TimetableItemViewHolder) holder).getTextView().setText("Hello world!");
    }

    @Override
    public int getItemCount() {
        return 12;
    }
}
