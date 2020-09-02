package com.timetable.activities.yearStructureActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.timetable.R;
import com.timetable.database.subjects.Subject;

import java.util.List;

public class SubjectItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class SubjectItemViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectName;

        public SubjectItemViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.subjectName_item_subject);
        }

        public void setSubjectName(String name) {
            subjectName.setText(name);
        }
    }

    private List<Subject> subjects;

    public SubjectItemAdapter() {
        this.subjects = null;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Subject subject = subjects.get(position);

        ((SubjectItemViewHolder) holder).setSubjectName(subject.getName());
    }

    @Override
    public int getItemCount() {
        if (subjects == null) {
            return 0;
        }

        return subjects.size();
    }
}
