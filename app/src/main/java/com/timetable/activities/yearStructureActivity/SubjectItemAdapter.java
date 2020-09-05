package com.timetable.activities.yearStructureActivity;

import android.content.Context;
import android.content.Intent;
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
import com.timetable.activities.addSubjectActivity.AddSubjectActivity;
import com.timetable.database.subjects.Subject;
import com.timetable.database.subjects.SubjectsDatabase;
import com.timetable.utils.GlobalVariables;

import java.util.List;

public class SubjectItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class SubjectItemViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectName;
        private CardView cardView;

        public SubjectItemViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.subjectName_item_subject);
            cardView = itemView.findViewById(R.id.cardView_item_subject);
        }

        public CardView getCardView() {
            return cardView;
        }

        public void setSubjectName(String name) {
            subjectName.setText(name);
        }
    }

    private Context context;
    private List<Subject> subjects;

    public SubjectItemAdapter(Context context) {
        this.context = context;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Subject subject = subjects.get(position);

        ((SubjectItemViewHolder) holder).setSubjectName(subject.getName());

        ((SubjectItemViewHolder) holder).getCardView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Context popupContext = new ContextThemeWrapper(context, R.style.popupMenu);
                PopupMenu popupMenu = new PopupMenu(popupContext, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_subjects_list_year_structure, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.edit_popup_subjects_option:
                                Intent intent = new Intent(context, AddSubjectActivity.class);
                                intent.putExtra(GlobalVariables.getSubjectIdExtra(), String.valueOf(subject.getId()));

                                context.startActivity(intent);

                                break;
                            case R.id.delete_popup_subjects_option:
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SubjectsDatabase.getDatabase(context).getSubjectDao().deleteSubject(subject);
                                    }
                                }).start();

                                subjects.remove(position);
                                notifyDataSetChanged();

                                break;
                            default:
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
        if (subjects == null) {
            return 0;
        }

        return subjects.size();
    }
}
