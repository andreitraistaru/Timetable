package com.timetable.activities.viewTimetableActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.activities.yearStructureActivity.YearStructureActivity;

import java.util.ArrayList;

public class TimetableItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class TimetableItemViewHolder extends RecyclerView.ViewHolder {
        private TextView start;
        private TextView name;
        private TextView type;
        private TextView location;
        private TextView end;
        private CardView cardView;

        public TimetableItemViewHolder(@NonNull View itemView) {
            super(itemView);

            start = itemView.findViewById(R.id.classStartTime_item_timetable);
            name = itemView.findViewById(R.id.className_item_timetable);
            type = itemView.findViewById(R.id.classType_item_timetable);
            location = itemView.findViewById(R.id.classLocation_item_timetable);
            end = itemView.findViewById(R.id.classEndTime_item_timetable);
            cardView = itemView.findViewById(R.id.cardView_item_timetable);
        }

        public CardView getCardView() {
            return cardView;
        }

        public void setStart(String text) {
            start.setText(text);
        }
        public void setName(String text) {
            name.setText(text);
        }
        public void setType(String text) {
            type.setText(text);
        }
        public void setLocation(String text) {
            location.setText(text);
        }
        public void setEnd(String text) {
            end.setText(text);
        }
        public void setColor(int color) {
            cardView.setCardBackgroundColor(color);
        }
        public void setDuration(int duration) {
            cardView.getLayoutParams().height = (int) ((duration * cardView.getContext().getResources().getDimension(R.dimen.item_timetable_hour_height)) +
                    ((duration - 1) * cardView.getContext().getResources().getDimension(R.dimen.item_timetable_hour_padding)));
        }
    }

    private ArrayList<TimetableEntry> timetable;

    public TimetableItemAdapter() {
        this.timetable = null;
    }

    @NonNull
    @Override
    public TimetableItemAdapter.TimetableItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimetableItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final TimetableEntry entry = timetable.get(position);

        ((TimetableItemViewHolder) holder).setStart(entry.getStartHour());
        ((TimetableItemViewHolder) holder).setName(entry.getName());
        ((TimetableItemViewHolder) holder).setType(entry.getType());
        ((TimetableItemViewHolder) holder).setLocation(entry.getLocation());
        ((TimetableItemViewHolder) holder).setEnd(entry.getEndHour());
        ((TimetableItemViewHolder) holder).setColor(entry.getColor());
        ((TimetableItemViewHolder) holder).setDuration(entry.getDuration());

        if (!entry.isBreakTime() || !entry.isHolidayTime()) {
            ((TimetableItemViewHolder) holder).getCardView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    ViewGroup viewGroup = view.findViewById(android.R.id.content);
                    final View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_view_class_interval, viewGroup, false);

                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    ((ConstraintLayout) dialogView.findViewById(R.id.constraintLayout_dialog_view_class_interval)).setBackgroundColor(entry.getColor());
                    ((TextView) dialogView.findViewById(R.id.subject_dialog_view_class_interval)).setText(entry.getName());
                    ((TextView) dialogView.findViewById(R.id.activityType_dialog_view_class_interval)).setText(view.getResources().getString(R.string.type_timetable_entry_dialog, entry.getType()));
                    ((TextView) dialogView.findViewById(R.id.subjectDescription_dialog_view_class_interval)).setText(view.getResources().getString(R.string.subject_description_timetable_entry_dialog, entry.getSubjectDescription()));
                    ((TextView) dialogView.findViewById(R.id.teacher_dialog_view_class_interval)).setText(view.getResources().getString(R.string.teacher_timetable_entry_dialog, entry.getTeacher()));
                    ((TextView) dialogView.findViewById(R.id.activityDescription_dialog_view_class_interval)).setText(view.getResources().getString(R.string.activity_description_timetable_entry_dialog, entry.getActivityDescription()));
                    ((TextView) dialogView.findViewById(R.id.location_dialog_view_class_interval)).setText(view.getResources().getString(R.string.location_timetable_entry_dialog, entry.getLocation()));
                    ((TextView) dialogView.findViewById(R.id.day_dialog_view_class_interval)).setText(view.getResources().getString(R.string.day_timetable_entry_dialog, entry.getDay()));
                    ((TextView) dialogView.findViewById(R.id.startHour_dialog_view_class_interval)).setText(view.getResources().getString(R.string.start_timetable_entry_dialog, entry.getStart()));
                    ((TextView) dialogView.findViewById(R.id.endHour_dialog_view_class_interval)).setText(view.getResources().getString(R.string.end_timetable_entry_dialog, entry.getEnd()));
                    ((TextView) dialogView.findViewById(R.id.frequency_dialog_view_class_interval)).setText(view.getResources().getString(R.string.frequency_timetable_entry_dialog, entry.getFrequency()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (timetable == null) {
            return 0;
        }

        return timetable.size();
    }

    public void setTimetable(ArrayList<TimetableEntry> timetable) {
        this.timetable = timetable;
        notifyDataSetChanged();
    }
}
